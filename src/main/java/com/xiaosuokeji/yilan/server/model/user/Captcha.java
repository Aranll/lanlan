package com.xiaosuokeji.yilan.server.model.user;

import com.xiaosuokeji.yilan.server.model.base.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 验证码<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class Captcha extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = {Save.class, Verify.class})
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式为11位数字且第一位为1", groups = {Save.class, Verify.class})
    private String mobile;

    /**
     * 类型，0注册，1忘记密码
     */
    private Integer type;

    /**
     * 内容
     */
    @NotNull(message = "验证码不能为空", groups = {User.Save.class, User.ForGetPassword.class, Verify.class})
    @Pattern(regexp = "^[0-9]{6}$", message = "验证码格式为6位数字",
            groups = {User.Save.class, User.ForGetPassword.class, Verify.class})
    private String content;

    public interface Save {}

    public interface Verify {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
