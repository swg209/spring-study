package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanPostProcessor;
import cn.suwg.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * 这个接口扩展了ListableBeanFactory，AutowireCapableBeanFactory和ConfigurableBeanFactory接口。
 * 它用于配置可列出的bean工厂，允许获取bean的定义。
 * <p>
 * Configuration interface to be implemented by most listable bean factories.
 * It provides facilities to analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取指定名称的Bean定义。
     *
     * @param beanName Bean的名称
     * @throws BeansException 如果在获取Bean定义过程中发生错误，抛出BeansException。
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * 预实例化所有剩余的单例.
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 添加BeanPostProcessor.
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}