package cn.suwg.springframework.test;

import cn.suwg.springframework.BeanDefinition;
import cn.suwg.springframework.BeanFactory;
import cn.suwg.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * 单元测试.
 * @Author: suwg
 * @Date: 2024/10/9
 */
public class ApiTest {

    @Test
    public void testBeanFactory(){

        //1、初始化bean工厂
        BeanFactory beanFactory = new BeanFactory();

        //2、注入bean对象
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        //3、获取bean对象
        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.queryUserInfo();

    }






}
