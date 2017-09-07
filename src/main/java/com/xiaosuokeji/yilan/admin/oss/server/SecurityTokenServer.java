package com.xiaosuokeji.yilan.admin.oss.server;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.google.gson.Gson;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityTokenServerConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by gustinlau on 24/07/2017.
 */
public class SecurityTokenServer {
    private static final Gson gson = new Gson();
    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    private static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    private static final String STS_API_VERSION = "2015-04-01";

    private SecurityTokenServerConfig config;
    private Long updateTime = null;
    private SecurityToken token = new SecurityToken();

    private String configFileName;

    @PostConstruct
    public void SecurityTokenServer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(configFileName)));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        this.config = gson.fromJson(builder.toString(), SecurityTokenServerConfig.class);
    }

    public SecurityToken getToken() {
        if (invalid()) {
            requestSecurityToken();
        }
        return token;
    }

    /**
     * Token是否无效
     *
     * @return
     */
    private boolean invalid() {
        if (updateTime == null) {
            return true;
        } else {
            Long currentTime = System.currentTimeMillis();
            //提前5分钟过期
            return (currentTime - updateTime) / 1000 > config.getTokenExpireTime() - 300;
        }
    }

    /**
     * 请求Token
     */
    private void requestSecurityToken()  {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
            // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
            // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, config.getAccessKeyID(), config.getAccessKeySecret());
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            //必须为 HTTPS
            request.setProtocol(ProtocolType.HTTPS);
            // RoleArn 需要在 RAM 控制台上获取
            request.setRoleArn(config.getRoleArn());
            // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
            // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
            // 具体规则请参考API文档中的格式要求
            request.setRoleSessionName("xs");
            request.setPolicy(gson.toJson(config.getPolicy()));
            request.setDurationSeconds(config.getTokenExpireTime());

            // 发起请求，并得到response
            AssumeRoleResponse stsResponse = client.getAcsResponse(request);

            token.setStatus("200");
            token.setAccessKeyId(stsResponse.getCredentials().getAccessKeyId());
            token.setAccessKeySecret(stsResponse.getCredentials().getAccessKeySecret());
            token.setSecurityToken(stsResponse.getCredentials().getSecurityToken());
            token.setExpiration(stsResponse.getCredentials().getExpiration());

            updateTime = System.currentTimeMillis();


        } catch (ClientException e) {
            token.setStatus(e.getErrCode());
            token.setAccessKeyId("");
            token.setAccessKeySecret("");
            token.setSecurityToken("");
            token.setExpiration("");

            updateTime = null;
        }
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }
}
