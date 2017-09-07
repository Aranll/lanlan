package com.xiaosuokeji.yilan.common;

import com.xiaosuokeji.yilan.common.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gustinlau on 31/08/2017.
 */
public class SessionUtils {

    public static String getUserToken(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("token");
    }

    private static void setUserToken(HttpServletRequest request, String token) {
        request.getSession().setAttribute("token", token);
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
        setUserToken(request,user.getToken());
    }

    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


}
