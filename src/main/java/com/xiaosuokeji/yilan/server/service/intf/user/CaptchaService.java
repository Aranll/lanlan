package com.xiaosuokeji.yilan.server.service.intf.user;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.yilan.server.model.user.Captcha;

/**
 * 验证码Service<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
public interface CaptchaService {

	/**
	 * 保存验证码
	 * @param captcha 必填：mobile，type
	 * @throws Exception 验证码未过期，验证码发送失败，用户已存在，用户不存在
	 */
	void save(Captcha captcha) throws Exception;

	/**
	 * 清除超过有效期的验证码
	 */
	void removeExpire();

	/**
	 * 校验验证码
	 * @param captcha 必填：mobile，type，content
	 * @param del 校验通过后是否删除验证码
	 * @throws BusinessException 验证码无效
	 */
	void verify(Captcha captcha, boolean del) throws BusinessException;
}