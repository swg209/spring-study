package cn.suwg.springframework.test.common;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: suwg
 * @Date: 2024/10/14
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为字节跳动"));
    }
}
