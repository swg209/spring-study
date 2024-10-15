package cn.suwg.springframework.beans.factory.config;

import cn.suwg.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 这个接口扩展了HierarchicalBeanFactory和SingletonBeanRegistry，
 * 它用于配置bean工厂，允许将bean的作用域设置为单例或原型。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例作用域.
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域.
     */
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
