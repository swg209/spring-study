package cn.suwg.springframework.beans.factory.config;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * Allows for custom modification of an application context's bean definitions,
 * adapting the bean property values of the context's underlying bean factory.
 * <p>
 * 允许自定义修改 BeanDefinition 属性信息
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制.
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
