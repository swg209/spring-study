package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;

/**
 * Interface to be implemented by beans that wish to be aware of their
 * owning {@link BeanFactory}.
 * 实现这个接口，就可以感知到所属到BeanFactory.
 *
 * @Author: suwg
 * @Date: 2024/10/16
 * 公众号： 趣研
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
