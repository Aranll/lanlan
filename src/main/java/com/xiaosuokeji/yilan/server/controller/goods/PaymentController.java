package com.xiaosuokeji.yilan.server.controller.goods;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.yilan.server.manager.intf.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 支付Controller<br/>
 * Created by xuxiaowei on 2017/8/12.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/v1/payment/callback", method = RequestMethod.POST)
    public void saveOrder(HttpServletRequest request, HttpServletResponse response) {
        String result = "fail";
        String message = getMessage(request);
        if (message != null) {
            result = paymentService.callback(message);
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if(result != null) {
                writer.write(result);
            }
        } catch (IOException e) {
            logger.error("error : ", e);
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    private String getMessage(HttpServletRequest request) {
        String message = null;
        try {
            request.setCharacterEncoding("utf-8");
            StringBuffer json = new StringBuffer();
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            message = json.toString();
        } catch (Exception e) {
            logger.error("error : ", e);
        }
        return message;
    }
}
