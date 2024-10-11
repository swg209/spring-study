package cn.suwg.springframework.test;

import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanReference;
import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.suwg.springframework.test.bean.UserDao;
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

        //2、UserDao注册.
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        //3、UserService 设置属性 uid、userDao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","10003"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //4、UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        //5、UserService获取bean对象
        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.queryUserInfo();

        //4、再次获取UserService bean对象，验证单例.
        UserService userServiceSingleton = (UserService) beanFactory.getSingleton("UserService");
        userServiceSingleton.queryUserInfo();
    }

}
