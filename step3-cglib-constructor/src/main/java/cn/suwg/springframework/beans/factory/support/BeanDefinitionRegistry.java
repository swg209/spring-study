package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.factory.config.BeanDefinition;

/**
 * bean的定义注册接口.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册bean定义.
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
