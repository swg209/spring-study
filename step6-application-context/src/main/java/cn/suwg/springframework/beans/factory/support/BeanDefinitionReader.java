package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.core.io.Resource;
import cn.suwg.springframework.core.io.ResourceLoader;

/**
 * BeanDefinitionReader接口，定义了Bean定义的读取规范。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface BeanDefinitionReader {

    /**
     * 获取Bean定义注册表。
     *
     * @return Bean定义注册表。
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器。
     *
     * @return 资源加载器。
     */
    ResourceLoader getResourceLoader();

    /**
     * 根据给定的资源，加载Bean定义。
     *
     * @param resource 要加载Bean定义的资源。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 根据给定的资源数组，加载Bean定义。
     *
     * @param resources 要加载Bean定义的资源数组。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 根据给定的位置字符串，加载Bean定义。
     *
     * @param location 资源的位置，可以是文件路径或者URL等。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    void loadBeanDefinitions(String location) throws BeansException;


    /**
     * 根据给定的位置字符串数组，加载Bean定义。
     *
     * @param locations 资源的位置，可以是文件路径或者URL等。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}