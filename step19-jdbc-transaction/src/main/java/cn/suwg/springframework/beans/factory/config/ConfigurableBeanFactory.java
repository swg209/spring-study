package cn.suwg.springframework.beans.factory.config;

import cn.suwg.springframework.beans.factory.HierarchicalBeanFactory;
import cn.suwg.springframework.core.convert.ConversionService;
import cn.suwg.springframework.util.StringValueResolver;
import com.sun.istack.internal.Nullable;

/**
 * 这个接口扩展了HierarchicalBeanFactory和SingletonBeanRegistry，
 * 它用于配置bean工厂，允许将bean的作用域设置为单例或原型。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 单例作用域.
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型作用域.
     */
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     *
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     *
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);

    /**
     * Return the associated ConversionService, if any.
     *
     * @since 3.0
     */
    @Nullable
    ConversionService getConversionService();

    /**
     * Specify a Spring 3.0 ConversionService to use for converting
     * property values, as an alternative to JavaBeans PropertyEditors.
     *
     * @since 3.0
     */
    void setConversionService(ConversionService conversionService);
}
