package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * bean实例化策略.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
