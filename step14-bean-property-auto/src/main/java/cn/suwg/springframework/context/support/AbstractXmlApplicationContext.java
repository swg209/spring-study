package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.suwg.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 抽象的应用上下文类，它从包含由XmlBeanDefinitionReader理解的bean定义的XML文档中提取配置。
 * 这是一个方便的基类，用于实现应用上下文。
 * <p>
 * Convenient base class for ApplicationContext
 * implementations, drawing configuration from XML documents containing bean definitions
 * understood by an XmlBeanDefinitionReader.
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }


    protected abstract String[] getConfigLocations();

}
