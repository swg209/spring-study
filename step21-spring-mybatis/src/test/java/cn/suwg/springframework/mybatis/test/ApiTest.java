package cn.suwg.springframework.mybatis.test;

import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.mybatis.test.dao.IUserDao;
import cn.suwg.springframework.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class ApiTest {

    @Test
    public void testClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserDao userDao = beanFactory.getBean("IUserDao", IUserDao.class);
        User user = userDao.queryUserInfoById(1L);
        System.out.println("测试结果：" + JSON.toJSONString(user));

    }
}
