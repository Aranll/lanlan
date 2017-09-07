package com.xiaosuokeji.yilan.admin;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gustinlau on 04/08/2017.
 */
public class TestModel {

    @Test
    public void test(){
        Integer a=127;
        Integer b=127;
        Integer c=128;
        Integer d=128;

        Integer a1=new Integer(a);
        Integer a2=new Integer(b);
        Integer a3=new Integer(c);
        Integer a4=new Integer(d);

        System.out.println(a==b);
        System.out.println(c==d);
    }

}
