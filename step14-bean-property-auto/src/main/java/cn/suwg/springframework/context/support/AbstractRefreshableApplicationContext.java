package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 应用上下文，支持多次刷新，每次刷新都会创建一个新的内部bean工厂实例，
 * 通常（但不一定）这样的上下文将由一组配置位置驱动，从中加载bean定义。
 * <p>
 * Base class for ApplicationContext
 * implementations which are supposed to support multiple calls to refresh(),
 * creating a new internal bean factory instance every time.
 * Typically (but not necessarily), such a context will be driven by
 * a set of config locations to load bean definitions from.
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


}
