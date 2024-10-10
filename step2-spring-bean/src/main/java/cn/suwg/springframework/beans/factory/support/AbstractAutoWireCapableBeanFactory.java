package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;

/**
 * 自动创建bean工厂抽象类.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory{

        @Override
        public Object createBean(String beanName, BeanDefinition beanDefinition) {
            Object bean;
            try {
                bean = beanDefinition.getBeanClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new BeansException("Instantiation of bean failed", e);
            }
            addSingleton(beanName, bean);
            return bean;
        }
}
