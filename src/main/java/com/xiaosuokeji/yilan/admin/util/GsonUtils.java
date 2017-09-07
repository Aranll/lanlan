package com.xiaosuokeji.yilan.admin.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.admin.model.base.BaseModel;
import com.xiaosuokeji.yilan.admin.model.security.SecStaff;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GustinLau on 2017-04-16.
 */
public class GsonUtils {

    private static Gson gson = new Gson();

    /**
     * Json字符串转对象
     *
     * @param json     Json字符串
     * @param classOfT 对象Class
     * @param <T>      泛型
     * @return 对象
     */
    public static <T> T objectFromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Json字符串转对象
     *
     * @param json    Json字符串
     * @param typeOfT 对象类型
     * @param <T>     泛型
     * @return 对象
     */
    public static <T> T objectFromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    /**
     * Json字符串转对象列表
     * @param json Json字符串
     * @param classOfT 列表内部对象对象类型
     * @param <T> 泛型
     * @return 对象列表
     */
    public static <T> List<T> listFromJson(String json, Class<T> classOfT) {
        return objectFromJson(json, new TypeToken<List<T>>() {}.getType());
    }

    /**
     * 对象转Json字符串
     *
     * @param src 源
     * @return Json字符串
     */
    public static String toJson(Object src) {
        if (src instanceof BaseModel) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !authentication.getPrincipal().equals("anonymousUser"))
                ((BaseModel) src).setUserId(((SecStaff) authentication.getPrincipal()).getId());
        }
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }


}




