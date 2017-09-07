package com.xiaosuokeji.yilan.server.dao.user;

import com.xiaosuokeji.yilan.server.model.user.User;

import java.util.Date;
import java.util.List;

/**
 * 用户Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface UserDao {

    /**
     * 保存用户
     * @param user 必填：id，mobile，password，email
     * @return 受影响行数
     */
    int save(User user);

    /**
     * 删除用户
     * @param user 必填：id
     * @return 受影响行数
     */
    int remove(User user);

    /**
     * 更新用户
     * @param user 必填：id，可选更新字段：email，lastLoginTime
     * @return 受影响行数
     */
    int update(User user);

    /**
     * 更新用户密码
     * @param user 必填：id，password
     * @return 受影响行数
     */
    int updatePassword(User user);

    /**
     * 根据手机号更新用户密码
     * @param user 必填：mobile，password
     * @return 受影响行数
     */
    int updatePasswordByMobile(User user);

    /**
     * 更新用户状态
     * @param user 必填：id，status
     * @return 受影响行数
     */
    int updateStatus(User user);

    /**
     * 更新用户会员信息
     * @param user 必填：id，vipLevel，vipExpire
     * @return 受影响行数
     */
    int updateVip(User user);

    /**
     * 更新超过有效期的会员身份
     * @param expireDate 超时日期
     * @return 受影响行数
     */
    int updateVipExpire(Date expireDate);

    /**
     * 获取单个用户
     * @param user 必填：id
     * @return 用户，字段：所有
     */
    User get(User user);

    /**
     * 根据手机号获取单个用户
     * @param user 必填：mobile
     * @return 用户，字段：所有
     */
    User getByMobile(User user);

    /**
     * 获取多个用户
     * @param user 可选条件：id（支持模糊），mobile（支持模糊），status，vipLevel
     * @return 用户列表，字段：所有
     */
    List<User> list(User user);

    /**
     * 统计用户数量
     * @param user 可选条件：id（支持模糊），mobile（支持模糊），status，vipLevel
     * @return 用户数量
     */
    Long count(User user);
}
