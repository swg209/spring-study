package cn.suwg.springframework.mybatis;

import cn.hutool.core.lang.ClassScanner;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.suwg.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Set;

/**
 * Mapper扫描配置.
 *
 * @Author: suwg
 * @Date: 2024/11/18
 */
public class MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;

    private SqlSessionFactory sqlSessionFactory;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing.
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Set<Class<?>> classes = ClassScanner.scanPackage(basePackage);
            for (Class<?> clazz : classes) {
                // Bean 对象定义
                BeanDefinition beanDefinition = new BeanDefinition(clazz);

                PropertyValues propertyValues = new PropertyValues();
                propertyValues.addPropertyValue(new PropertyValue("mapperInterface", clazz));
                propertyValues.addPropertyValue(new PropertyValue("sqlSessionFactory", sqlSessionFactory));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setBeanClass(MapperFactoryBean.class);

                // Bean 对象注册
                registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
