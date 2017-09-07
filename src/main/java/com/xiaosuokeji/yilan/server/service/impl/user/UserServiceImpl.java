package com.xiaosuokeji.yilan.server.service.impl.user;

import com.xiaosuokeji.framework.xsjframework.cache.CacheService;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.json.JsonUtil;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.framework.xsjframework.util.Md5Util;
import com.xiaosuokeji.framework.xsjframework.util.UuidUtil;
import com.xiaosuokeji.yilan.server.constant.user.CaptchaConsts;
import com.xiaosuokeji.yilan.server.constant.user.UserConsts;
import com.xiaosuokeji.yilan.server.dao.user.TokenDao;
import com.xiaosuokeji.yilan.server.dao.user.UserDao;
import com.xiaosuokeji.yilan.server.model.goods.Goods;
import com.xiaosuokeji.yilan.server.model.user.Captcha;
import com.xiaosuokeji.yilan.server.model.user.Token;
import com.xiaosuokeji.yilan.server.model.user.User;
import com.xiaosuokeji.yilan.server.service.intf.goods.GoodsService;
import com.xiaosuokeji.yilan.server.service.intf.user.CaptchaService;
import com.xiaosuokeji.yilan.server.service.intf.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * 用户ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    @Qualifier("tokenGuavaCache")
    private CacheService cacheService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional
    public User save(User user) throws Exception {
        Captcha captcha = user.getCaptcha();
        captcha.setMobile(user.getMobile());
        captcha.setType(CaptchaConsts.REGISTER);
        captchaService.verify(captcha, true);

        user.setId(UuidUtil.generate());
        user.setPassword(Md5Util.encode(user.getPassword()));
        try {
            userDao.save(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(UserConsts.USER_EXIST);
        }
        User existent = userDao.get(user);

        Token token = new Token(UuidUtil.generate());
        token.setUserId(existent.getId());
        tokenDao.save(token);
        token = tokenDao.get(token);
        //缓存相关的异常需捕获，因为缓存操作失败不影响业务流程
        try {
            cacheService.set(token.getContent(), JsonUtil.toJsonString(token));
        } catch (Exception e) {
            logger.warn("warn: ", e);
        }

        existent.setToken(token.getContent());
        existent.setId(null);
        existent.setPassword(null);
        return existent;
    }

    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Override
    public void update(User user) {
        //通过应用端接口访问时，令牌换取的用户id被设置在userId字段
        if (user.getId() == null) {
            user.setId(user.getUserId());
        }
        userDao.update(user);
    }

    @Override
    public void updatePassword(User user) throws Exception {
        //通过应用端接口访问时，令牌换取的用户id被设置在userId字段
        if (user.getId() == null) {
            user.setId(user.getUserId());
        }
        User existent = userDao.get(user);
        if (!existent.getPassword().equals(Md5Util.encode(user.getOldPassword())))
            throw new BusinessException(UserConsts.PASSWORD_ERROR);
        user.setPassword(Md5Util.encode(user.getPassword()));
        userDao.updatePassword(user);
    }

    @Override
    public void updatePasswordByMobile(User user) throws Exception {
        Captcha captcha = user.getCaptcha();
        captcha.setMobile(user.getMobile());
        captcha.setType(CaptchaConsts.FORGET_PASSWORD);
        captchaService.verify(captcha, true);

        user.setPassword(Md5Util.encode(user.getPassword()));
        userDao.updatePasswordByMobile(user);
    }

    @Override
    public void updateStatus(User user) {
        userDao.updateStatus(user);
    }

    @Override
    public void buyVip(User user, Goods goods) throws BusinessException {
        User existUser = get(user);
        Goods existGoods = goodsService.get(goods);
        if (existUser.getVipExpire() == null) {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.DATE, existGoods.getDuration() - 1);
            existUser.setVipLevel(1);
            existUser.setVipExpire(now.getTime());
        }
        else {
            Calendar expireTime = Calendar.getInstance();
            expireTime.setTime(existUser.getVipExpire());
            expireTime.add(Calendar.DATE, existGoods.getDuration() - 1);
            existUser.setVipLevel(1);
            existUser.setVipExpire(expireTime.getTime());
        }
        userDao.updateVip(existUser);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateVipExpire() {
        //使用定时任务在每天0点钟更新超过有效期的会员身份
        Long st = System.currentTimeMillis();
        logger.info("执行定时任务：更新超过有效期的会员身份...");
        try {
            userDao.updateVipExpire(new Date());
            logger.info("执行定时任务：更新超过有效期的会员身份，成功！执行时间：" + String.valueOf(System.currentTimeMillis() - st) + "毫秒");
        } catch (Exception e) {
            logger.error("执行定时任务：更新超过有效期的会员身份，失败！");
        }
    }

    @Override
    public User get(User user) throws BusinessException {
        //通过应用端接口访问时，令牌换取的用户id被设置在userId字段
        if (user.getId() == null) {
            user.setId(user.getUserId());
        }
        User existent =  userDao.get(user);
        if (existent == null) {
            throw new BusinessException(UserConsts.USER_NOT_EXIST);
        }
        existent.setPassword(null);
        return existent;
    }

    @Override
    public User getByMobile(User user) throws BusinessException {
        User existent =  userDao.getByMobile(user);
        if (existent == null) {
            throw new BusinessException(UserConsts.USER_NOT_EXIST);
        }
        existent.setPassword(null);
        return existent;
    }

    @Override
    @Transactional
    public User login(User user) throws Exception {
        User existent = userDao.getByMobile(user);
        if (existent == null) {
            throw new BusinessException(UserConsts.USER_NOT_EXIST);
        }
        if (!existent.getPassword().equals(Md5Util.encode(user.getPassword()))) {
            throw new BusinessException(UserConsts.PASSWORD_ERROR);
        }
        if (existent.getStatus().equals(0)) {
            throw new BusinessException(UserConsts.USER_NOT_ENABLE);
        }

        Token token = new Token(UuidUtil.generate());
        token.setUserId(existent.getId());
        tokenDao.save(token);
        token = tokenDao.get(token);
        //缓存相关的异常需捕获，因为缓存操作失败不影响业务流程
        try {
            cacheService.set(token.getContent(), JsonUtil.toJsonString(token));
        } catch (Exception e) {
            logger.warn("warn: ", e);
        }

        existent.setToken(token.getContent());
        existent.setId(null);
        existent.setPassword(null);
        return existent;
    }

    @Override
    public User getVip(User user) throws BusinessException {
        User existent = get(new User(user.getUserId()));
        User vip = new User(existent.getId());
        vip.setVipLevel(existent.getVipLevel());
        vip.setVipExpire(existent.getVipExpire());
        return vip;
    }

    @Override
    public PageModel<User> listAndCount(User user) {
        return PageModel.build(userDao.list(user), userDao.count(user));
    }
}
