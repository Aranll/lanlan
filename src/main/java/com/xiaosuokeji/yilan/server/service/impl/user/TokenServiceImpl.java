package com.xiaosuokeji.yilan.server.service.impl.user;

import com.xiaosuokeji.framework.xsjframework.cache.CacheService;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.json.JsonUtil;
import com.xiaosuokeji.framework.xsjframework.util.TimeUtil;
import com.xiaosuokeji.yilan.server.constant.user.TokenConsts;
import com.xiaosuokeji.yilan.server.dao.user.TokenDao;
import com.xiaosuokeji.yilan.server.model.user.Token;
import com.xiaosuokeji.yilan.server.service.intf.user.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 令牌ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${admin.token}")
    private String adminToken;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    @Qualifier("tokenGuavaCache")
    private CacheService cacheService;

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpire() {
        //使用定时任务在每天0点钟清除超过有效期的令牌
        Long st = System.currentTimeMillis();
        logger.info("执行定时任务：清除超过有效期的令牌...");
        try {
            tokenDao.removeExpire(new Date());
            logger.info("执行定时任务：清除超过有效期的令牌，成功！执行时间：" + String.valueOf(System.currentTimeMillis() - st) + "毫秒");
        } catch (Exception e) {
            logger.error("执行定时任务：清除超过有效期的令牌，失败！");
        }
    }

    @Override
    public void verifyAdminToken(Token token) throws BusinessException {
        //当令牌为空、空字符串或不等于内置的管理平台令牌时返回令牌无效
        if (token == null || StringUtils.isBlank(token.getContent()) || !adminToken.equals(token.getContent())) {
            throw new BusinessException(TokenConsts.TOKEN_INVALID);
        }
    }

    @Override
    public String verifyUserToken(Token userToken) throws BusinessException {
        //当令牌为空或者空字符串时返回令牌无效
        if (userToken == null || StringUtils.isBlank(userToken.getContent())) {
            throw new BusinessException(TokenConsts.TOKEN_INVALID);
        }
        Token token = null;
        /*
          先在内存缓存中查询令牌
          缓存相关的异常需捕获，因为缓存操作失败不影响业务流程
         */
        try {
            String tokenStr = cacheService.get(userToken.getContent());
            if (tokenStr != null) token = JsonUtil.toObject(tokenStr, Token.class);
        } catch (Exception e) {
            logger.warn("warn: ", e);
        }
        //若缓存未命中则查询数据库
        if (token == null) {
            token = tokenDao.getByContent(userToken);
            //当令牌不存在时返回令牌无效
            if (token == null) {
                throw new BusinessException(TokenConsts.TOKEN_INVALID);
            }
        }
        //当令牌超出一个月的有效期时，删除令牌，清除缓存，返回令牌无效
        boolean expire = TimeUtil.timeToLive(token.getUpdateTime(), 30 * 24 * 60 * 60) <= 0;
        if (expire) {
            tokenDao.remove(token);
            try {
                cacheService.del(token.getContent());
            } catch (Exception e) {
                logger.warn("warn: ", e);
            }
            throw new BusinessException(TokenConsts.TOKEN_INVALID);
        }
        try {
            //若令牌有效则存入缓存
            cacheService.set(token.getContent(), JsonUtil.toJsonString(token));
        } catch (Exception e) {
            logger.warn("warn: ", e);
        }
        return token.getUserId();
    }
}
