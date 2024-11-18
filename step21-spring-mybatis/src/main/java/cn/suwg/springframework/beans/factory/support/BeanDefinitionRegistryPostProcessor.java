package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Bean定义注册预先处理器.
 *
 * @Author: suwg
 * @Date: 2024/11/15
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;
}
