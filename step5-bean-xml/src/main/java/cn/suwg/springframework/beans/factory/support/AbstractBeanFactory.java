package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * bean工厂抽象类.
 *
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }


    /**
     * 获取Bean，这里用了T泛型，方便输出对应类的bean.
     *
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (Objects.nonNull(bean)) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

}
