package com.xiaosuokeji.yilan.admin.util;


import com.xiaosuokeji.yilan.admin.constant.CommonConstants;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient封装，若需要带cookie，请解除注释
 * <p>
 * Created by GustinLau on 2017-04-04.
 */
public class WebUtils {

    private static CloseableHttpClient httpClient = null;

    //private static CookieStore cookieStore = null;

    private static final ResponseHandler<String> responseHandler = new BasicResponseHandler();

    static {
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
        phccm.setMaxTotal(256);
        phccm.setDefaultMaxPerRoute(256);

        //cookieStore = new BasicCookieStore();
        RequestConfig rc = RequestConfig.custom()
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
                .build();

        httpClient = HttpClients.custom()
                //.setDefaultCookieStore(cookieStore)
                .disableAuthCaching()
                .setConnectionManager(phccm)
                .setDefaultRequestConfig(rc)
                .build();
    }


    /**
     * 一般表单请求
     *
     * @param url    请求地址
     * @param method 请求方法
     * @param params 请求参数
     * @return 请求结果Json对象
     */
    public static JSONObject doHttpRequest(String url, HttpMethod method, Map<String, ?> params) {
        if (url.startsWith("/"))
            url = CommonConstants.ADMIN_DOMAIN + url;
        HttpRequestBase httpRequest = makeHttpRequest(url, method, params);
        String resultStr = execute(httpRequest);
        if (resultStr != null)
            return new JSONObject(resultStr);
        else {
            return new JSONObject("{\"status\": false,\"code\": -1,\"msg\": \"请求失败\"}");
        }
    }

    /**
     * 一般表单请求
     *
     * @param url    请求地址
     * @param method 请求方法
     * @return 请求结果Json对象
     */
    public static JSONObject doHttpRequest(String url, HttpMethod method) {
        return doHttpRequest(url, method, null);
    }

    /**
     * 一般表单请求
     *
     * @param api    接口模型
     * @param params 请求参数
     * @return 请求结果Json对象
     */
    public static JSONObject doHttpRequest(API api, Map<String, ?> params) {
        return doHttpRequest(api.url(), api.method(), params);
    }

    /**
     * 一般表单请求
     *
     * @param api 接口模型
     * @return 请求结果Json对象
     */
    public static JSONObject doHttpRequest(API api) {
        return doHttpRequest(api.url(), api.method(), null);
    }

    /**
     * Raw类型请求
     *
     * @param api  接口模型
     * @param json 请求json
     * @return 请求结果Json对象
     */
    public static JSONObject doRawRequest(API api, String json) {
        try {
            String url = api.url();
            if (url.startsWith("/"))
                url = CommonConstants.ADMIN_DOMAIN + url;
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(json, "utf-8");
            post.setEntity(entity);
            post.setHeader("Content-type", "application/json;charset=UTF-8");
            String resultStr = httpClient.execute(post, responseHandler);
            if (resultStr != null)
                return new JSONObject(resultStr);
            else
                return new JSONObject("{\"status\": false,\"code\": -1,\"msg\": \"请求失败\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject("{\"status\": false,\"code\": -1,\"msg\": \"请求失败\"}");
        }
    }

    /**
     * Raw类型请求
     *
     * @param api 接口模型
     * @param body 参数模型
     * @return 请求结果Json对象
     */
    public static JSONObject doRawRequest(API api, Object body) {
        String s = GsonUtils.toJson(body);
        return doRawRequest(api, GsonUtils.toJson(body));
    }

    /**
     * 文件类型请求
     *
     * @param api  接口模型
     * @param body 请求参数
     * @return 请求结果Json对象
     */
    public static JSONObject doMultipartRequest(API api, Map<String, ?> body) {
        try {
            String url = api.url();
            if (url.startsWith("/"))
                url = CommonConstants.ADMIN_DOMAIN + url;
            HttpPost httppost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, ?> entry : body.entrySet()) {
                if (entry.getValue() instanceof File)
                    builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
                else
                    builder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()));
            }
            HttpEntity entity = builder.build();
            httppost.setEntity(entity);
            String resultStr = httpClient.execute(httppost, responseHandler);
            if (resultStr != null)
                return new JSONObject(resultStr);
            else
                return new JSONObject("{\"status\": false,\"code\": -1,\"msg\": \"请求失败\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject("{\"status\": false,\"code\": -1,\"msg\": \"请求失败\"}");
        }
    }

    private static HttpRequestBase makeHttpRequest(String url, HttpMethod method, Map<String, ?> params) {
        HttpRequestBase httpRequest;
        switch (method) {
            case POST:
                httpRequest = getPostRequest(url, params);
                break;
            case GET:
                httpRequest = getGetRequest(url, params);
                break;
            default:
                httpRequest = getPostRequest(url, params);
        }
        return httpRequest;
    }

    private static String execute(HttpRequestBase httpRequest) {
        try {
            return httpClient.execute(httpRequest, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpGet getGetRequest(String url, Map<String, ?> params) {
        List<NameValuePair> newParams = constructParams(params);
        String urlParams = URLEncodedUtils.format(newParams, "UTF-8");
        String newUrl = url;
        if (!urlParams.isEmpty()) {
            if (url.contains("?")) {
                if (url.endsWith("?")) newUrl += urlParams;
                else newUrl += "&" + urlParams;
            } else newUrl += "?" + urlParams;
        }
        return new HttpGet(newUrl);
    }

    private static HttpPost getPostRequest(String url, Map<String, ?> params) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> newParams = constructParams(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(newParams, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return httpPost;
    }

    private static List<NameValuePair> constructParams(Map<String, ?>... params) {
        List<NameValuePair> newParams = new ArrayList<>();
        if (params != null && params.length > 0) {
            for (Map<String, ?> param : params) {
                if (param != null) {
                    for (String key : param.keySet()) {
                        if (param.get(key) instanceof String[]) {
                            String[] vals = (String[]) param.get(key);
                            for (String val : vals) newParams.add(new BasicNameValuePair(key, val));
                        } else newParams.add(new BasicNameValuePair(key, String.valueOf(param.get(key))));
                    }
                }
            }
        }
        return newParams;
    }
}
