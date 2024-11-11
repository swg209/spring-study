package cn.suwg.springframework.beans.factory;

/**
 * Interface to be implemented by objects used within a  BeanFactory
 * which are themselves factories. If a bean implements this interface,
 * it is used as a factory for an object to expose, not directly as a bean
 * instance that will be exposed itself.
 * <p>
 * 由BeanFactory中使用的对象实现的接口,它们本身是工厂。
 * 如果一个bean实现了这个接口,它将被用作一个工厂来暴露一个对象,而不是直接作为一个将被暴露的bean实例。
 *
 * @Author: suwg
 * @Date: 2024/10/17
 * 公众号： 趣研
 */
public interface FactoryBean<T> {


    /**
     * 获取对象
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否是单例
     *
     * @return
     */
    boolean isSingleton();


}
