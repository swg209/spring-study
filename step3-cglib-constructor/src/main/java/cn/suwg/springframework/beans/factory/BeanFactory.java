package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;

/**
 * Bean工厂接口.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public interface BeanFactory {

    /**
     * 根据名称获取bean.
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     *带参数构造函数的bean的获取.
     */
    Object getBean(String name, Object... args) throws BeansException;

}
