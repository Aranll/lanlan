package com.xiaosuokeji.yilan.admin;

import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import org.junit.Test;

public class MyTest {

    @Test
    public void test(){
        String url = "http://shuttle-yilan.oss-cn-shenzhen.aliyuncs.com/book/1503639938000_%E5%8C%BB%E7%96%97%E5%9B%BE%E4%B9%A62.jpg/watermark";
        url=url.substring(url.indexOf(".com/")+4);
        if(url.endsWith("/watermark")){
            url = url.replace("/watermark","");
        }
        System.out.println(CodingUtils.urlDecode(url));
    }

}
