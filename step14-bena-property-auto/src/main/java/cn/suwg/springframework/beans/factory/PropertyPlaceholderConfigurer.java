package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.suwg.springframework.core.io.DefaultResourceLoader;
import cn.suwg.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * Allows for configuration of individual bean property values from a property resource,
 * i.e. a properties file. Useful for custom config files targeted at system
 * administrators that override bean properties configured in the application context.
 *
 * @Description: 属性占位符解析器:从属性资源加载单个bean属性值的配置，即属性文件。 用于针对系统管理员的自定义配置文件，这些文件覆盖了应用程序上下文中配置的bean属性。
 * @Author: suwg
 * @Date: 2024/10/29
 * 公众号：趣研
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 占位符前缀.
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * 占位符后缀.
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    /**
     * 文件路径.
     */
    private String location;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        try {
            //加载属性文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            //遍历所有bean定义
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuffer buffer = new StringBuffer(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propKey = strVal.substring(startIndex + 2, stopIndex);
                        String propValue = properties.getProperty(propKey);
                        buffer.replace(startIndex, stopIndex + 1, propValue);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }


        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }

    }


    public void setLocation(String location) {
        this.location = location;
    }
}
