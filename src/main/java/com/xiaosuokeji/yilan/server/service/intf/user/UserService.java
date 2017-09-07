package com.xiaosuokeji.yilan.server.service.intf.user;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.goods.Goods;
import com.xiaosuokeji.yilan.server.model.user.User;

/**
 * 用户Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface UserService {

    /**
     * 保存用户
     * @param user 必填：mobile，password，email，captcha.content
     * @return 用户，字段：所有
     * @throws Exception 验证码无效，用户已存在
     */
    User save(User user) throws Exception;

    /**
     * 删除用户
     * @param user 必填：id
     */
    void remove(User user);

    /**
     * 更新用户
     * @param user 必填：id，可选更新字段：email，lastLoginTime
     */
    void update(User user);

    /**
     * 更新用户密码
     * @param user 必填：id，oldPassword，password
     * @throws Exception 密码错误
     */
    void updatePassword(User user) throws Exception;

    /**
     * 根据手机号更新用户密码
     * @param user 必填：mobile，captcha.content，password
     * @throws Exception 验证码无效
     */
    void updatePasswordByMobile(User user) throws Exception;

    /**
     * 更新用户状态
     * @param user 必填：id，status
     */
    void updateStatus(User user);

    /**
     * 购买会员
     * @param user 必填：id
     * @param goods 必填：id
     * @throws BusinessException 用户不存在，商品不存在
     */
    void buyVip(User user, Goods goods) throws BusinessException;

    /**
     * 更新超过有效期的会员身份
     */
    void updateVipExpire();

    /**
     * 获取单个用户
     * @param user 必填：id
     * @return 用户，字段：所有
     * @throws BusinessException 用户不存在
     */
    User get(User user) throws BusinessException;

    /**
     * 根据手机号获取单个用户
     * @param user 必填：mobile
     * @return 用户，字段：所有
     * @throws BusinessException 用户不存在
     */
    User getByMobile(User user) throws BusinessException;

    /**
     * 用户登录
     * @param user 必填：mobile，password
     * @return 用户，字段：所有
     * @throws Exception 用户不存在，密码错误
     */
    User login(User user) throws Exception;

    /**
     * 获取单个用户的会员信息
     * @param user 必填：id
     * @return 用户，字段：id，vipLevel，vipExpire
     * @throws BusinessException 用户不存在
     */
    User getVip(User user) throws BusinessException;

    /**
     * 获取和统计多个用户
     * @param user 可选条件：id（支持模糊），mobile（支持模糊），status，vipLevel
     * @return 用户列表和总数，字段：所有
     */
    PageModel<User> listAndCount(User user);
}
