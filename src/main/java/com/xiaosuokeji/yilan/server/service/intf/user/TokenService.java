package com.xiaosuokeji.yilan.server.service.intf.user;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.yilan.server.model.user.Token;

/**
 * 令牌Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface TokenService {

    /**
     * 清除超过有效期的令牌
     */
    void removeExpire();

    /**
     * 校验管理平台接口令牌
     * @param token 必填：content
     * @throws BusinessException 令牌无效
     */
    void verifyAdminToken(Token token) throws BusinessException;

    /**
     * 校验应用接口令牌，并换取令牌所属用户的id
     * @param userToken 必填：content
     * @return 用户id
     * @throws BusinessException 令牌无效
     */
    String verifyUserToken(Token userToken) throws BusinessException;
}
