package cn.suwg.springframework.test;

import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.suwg.springframework.test.bean.UserService;
import jdk.internal.vm.annotation.ReservedStackAccess;
import org.junit.Test;

/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 *  公众号： 趣研
 */
public class ApiTest {


    @Test
    public void testBeanFactory(){
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、注入bean对象
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        //3、获取bean对象
        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.queryUserInfo();

        //4、再次获取bean对象，验证单例.
        UserService userServiceSingleton = (UserService) beanFactory.getSingleton("UserService");
        userServiceSingleton.queryUserInfo();
    }

}
