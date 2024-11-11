package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;

import java.util.Map;

/**
 * 这个接口扩展了{@link BeanFactory}接口，需要由能枚举所有bean实例的bean工厂来实现，
 * 而不是按照客户端的请求一个一个地尝试通过名称查找bean。预加载所有bean定义的BeanFactory实现
 * （如基于XML的工厂）可能会实现这个接口。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
