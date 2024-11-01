package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.core.io.DefaultResourceLoader;
import cn.suwg.springframework.core.io.ResourceLoader;

/**
 * AbstractBeanDefinitionReader抽象类，实现了BeanDefinitionReader接口，用于读取Bean定义。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    // Bean定义注册表
    private final BeanDefinitionRegistry registry;

    // 资源加载器
    private ResourceLoader resourceLoader;

    /**
     * 构造函数，传入一个BeanDefinitionRegistry对象，使用默认的资源加载器。
     *
     * @param registry Bean定义注册表
     */
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    /**
     * 构造函数，传入一个BeanDefinitionRegistry对象和一个ResourceLoader对象。
     *
     * @param registry       Bean定义注册表
     * @param resourceLoader 资源加载器
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取Bean定义注册表。
     *
     * @return Bean定义注册表。
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    /**
     * 获取资源加载器。
     *
     * @return 资源加载器。
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}