package com.xiaosuokeji.yilan.server.service.impl.user;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.util.RandomUtil;
import com.xiaosuokeji.framework.xsjframework.util.TimeUtil;
import com.xiaosuokeji.yilan.server.constant.base.SmsConsts;
import com.xiaosuokeji.yilan.server.constant.user.CaptchaConsts;
import com.xiaosuokeji.yilan.server.constant.user.UserConsts;
import com.xiaosuokeji.yilan.server.dao.user.CaptchaDao;
import com.xiaosuokeji.yilan.server.dao.user.UserDao;
import com.xiaosuokeji.yilan.server.manager.intf.SmsService;
import com.xiaosuokeji.yilan.server.model.user.Captcha;
import com.xiaosuokeji.yilan.server.model.user.User;
import com.xiaosuokeji.yilan.server.service.intf.user.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验证码ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

	private static final Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);

	@Autowired
	private CaptchaDao captchaDao;

	@Autowired
	private SmsService smsService;

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void save(Captcha captcha) throws Exception {
		verifyMobile(captcha);
		/*
		  根据手机号和验证码类型获取验证码列表
		  验证码120秒内有效，但60秒后即可重发，因此同一手机号同时可能会存在多条有效验证码
		 */
		List<Captcha> captchas = captchaDao.list(captcha);
		for (Captcha item : captchas) {
			//验证码未过期
			Integer restLifeTime = null;
			if ((restLifeTime = TimeUtil.timeToLive(item.getCreateTime(), 60)) > 0) {
				throw new BusinessException(CaptchaConsts.CAPTCHA_NOT_EXPIRE.get(String.valueOf(restLifeTime)));
			}
		}
		//生成6位验证码
		String captchaStr = String.valueOf(RandomUtil.generate(100000, 999999));
		captcha.setContent(captchaStr);
		captchaDao.save(captcha);
		//发送验证码
		Map<String, String> params = new HashMap<>();
		params.put("userMobile", captcha.getMobile());
		params.put("captcha", captcha.getContent());
		smsService.sendBySms(captcha.getMobile(), SmsConsts.SMS_TEMPLATE_LOGIN, params);
	}

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void removeExpire() {
		//使用定时任务在每天0点钟清除超过有效期的验证码
		Long st = System.currentTimeMillis();
		logger.info("执行定时任务：清除超过有效期的验证码...");
		try {
			captchaDao.removeExpire(new Date());
			logger.info("执行定时任务：清除超过有效期的验证码，成功！执行时间：" + String.valueOf(System.currentTimeMillis() - st) + "毫秒");
		} catch (Exception e) {
			logger.error("执行定时任务：清除超过有效期的验证码，失败！");
		}
	}

	@Override
	@Transactional
	public void verify(Captcha captcha, boolean del) throws BusinessException {
		/*
		  根据手机号、验证码类型和验证码获取验证码列表
		  由于随机验证码可能会重复，因此同一手机号同时可能存在多条相同的验证码
		 */
		List<Captcha> captchas = captchaDao.list(captcha);
		if (captchas.size() == 0) {
			throw new BusinessException(CaptchaConsts.CAPTCHA_INVALID);
		}
		for (Captcha item : captchas) {
			//验证码未过期
			if (TimeUtil.timeToLive(item.getCreateTime(), 120) > 0) {
				if (del) {
					//删除验证码
					captchaDao.remove(captcha);
				}
				return;
			}
		}
		throw new BusinessException(CaptchaConsts.CAPTCHA_INVALID);
	}

	private void verifyMobile(Captcha captcha) throws BusinessException {
		User user = new User();
		user.setMobile(captcha.getMobile());
		user = userDao.getByMobile(user);
		if (captcha.getType().equals(0)) {
			if (user != null) throw new BusinessException(UserConsts.USER_EXIST);
		}
		else {
			if (user == null) throw new BusinessException(UserConsts.USER_NOT_EXIST);
		}
	}
}