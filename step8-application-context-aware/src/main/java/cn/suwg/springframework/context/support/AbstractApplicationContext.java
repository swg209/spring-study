package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.suwg.springframework.beans.factory.config.BeanPostProcessor;
import cn.suwg.springframework.context.ConfigurableApplicationContext;
import cn.suwg.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 应用上下文抽象类实现.
 * Abstract implementation of the ApplicationContext interface.
 * Doesn't mandate the type of storage used for configuration; simply
 * implements common context functionality. Uses the Template Method design pattern,
 * requiring concrete subclasses to implement abstract methods.
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * 刷新容器.
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {

        // 1、创建 BeanFactory, 并加载 BeanDefinition
        refreshBeanFactory();

        // 2、获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3、添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 3、在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4、BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5、提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

    }

    /**
     * 创建 BeanFactory, 并加载 BeanDefinition.
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取 BeanFactory.
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制.
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

    }

    /**
     * BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作.
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 注册关闭的钩子.
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }


}
