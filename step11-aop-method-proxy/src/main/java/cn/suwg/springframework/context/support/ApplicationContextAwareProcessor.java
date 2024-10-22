package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.config.BeanPostProcessor;
import cn.suwg.springframework.context.ApplicationContext;
import cn.suwg.springframework.context.ApplicationContextAware;

/**
 * 它负责处理实现了 ApplicationContextAware 接口的 bean。
 * 这个处理器是 Spring 容器内部使用的一部分，用于在 bean 初始化过程中注入 ApplicationContext（应用程序上下文）的引用。
 *
 * @Author: suwg
 * @Date: 2024/10/16
 * 公众号： 趣研
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 在 bean 初始化之前，将 ApplicationContext 注入到实现了 ApplicationContextAware 接口的 bean 中。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
