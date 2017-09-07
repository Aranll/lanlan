package com.xiaosuokeji.yilan.web.util;

import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.user.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {


    /*
    * 检验是否会员
    * */
    public static boolean checkUserVip(HttpServletRequest request){
        User user = new User();
        String token = (String)request.getSession().getAttribute("token");
        user.setToken(token);
        JSONObject rawResponse = WebUtils.doRawRequest(API.USER_VIP_GET,user);
        if(rawResponse.getBoolean("status")){
            user = GsonUtils.fromJson(rawResponse.getJSONObject("data").toString(),User.class);
            if(user.getVipLevel()==1)
                return true;
        }
        return false;
    }

    /*
    * 检验是否登录
    * */
    public static boolean checkLogin(HttpServletRequest request){
        String token = (String)request.getSession().getAttribute("token");
        if(token !=null){
            return true;
        }
        return false;
    }

}
