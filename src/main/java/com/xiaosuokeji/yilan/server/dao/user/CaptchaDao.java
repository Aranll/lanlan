package com.xiaosuokeji.yilan.server.dao.user;

import com.xiaosuokeji.yilan.server.model.user.Captcha;

import java.util.Date;
import java.util.List;

/**
 * 验证码Dao<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
public interface CaptchaDao {

    /**
     * 保存验证码
     * @param captcha 必填：mobile，type，content
     * @return 受影响行数
     */
    int save(Captcha captcha);

    /**
     * 删除验证码
     * @param captcha 必填：mobile，type
     * @return 受影响行数
     */
    int remove(Captcha captcha);

    /**
     * 清除超过有效期的验证码
     * @param expireTime 超时时间
     * @return 受影响行数
     */
    int removeExpire(Date expireTime);

    /**
     * 获取多个验证码
     * @param captcha 必填：mobile，type，可选条件：content
     * @return 验证码列表，字段：所有
     */
    List<Captcha> list(Captcha captcha);
}
