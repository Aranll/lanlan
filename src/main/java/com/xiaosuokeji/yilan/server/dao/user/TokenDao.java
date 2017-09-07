package com.xiaosuokeji.yilan.server.dao.user;

import com.xiaosuokeji.yilan.server.model.user.Token;

import java.util.Date;

/**
 * 令牌Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface TokenDao {

    /**
     * 保存令牌
     * @param token 必填：userId，content
     * @return 受影响行数
     */
    int save(Token token);

    /**
     * 删除令牌
     * @param token 必填：id
     * @return 受影响行数
     */
    int remove(Token token);

    /**
     * 清除超过有效期的令牌
     * @param expireTime 超时时间
     * @return 受影响行数
     */
    int removeExpire(Date expireTime);

    /**
     * 获取单个令牌
     * @param token 必填：id
     * @return 令牌，字段：所有
     */
    Token get(Token token);

    /**
     * 根据内容获取单个令牌
     * @param token 必填：content
     * @return 令牌，字段：所有
     */
    Token getByContent(Token token);
}
