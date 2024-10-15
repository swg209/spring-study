package cn.suwg.springframework.test.common;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.BeanPostProcessor;
import cn.suwg.springframework.test.bean.UserService;

/**
 * @Author: suwg
 * @Date: 2024/10/14
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为广州");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
