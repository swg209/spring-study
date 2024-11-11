package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.FactoryBean;
import cn.suwg.springframework.beans.factory.InitializingBean;
import cn.suwg.springframework.core.convert.ConversionService;
import cn.suwg.springframework.core.convert.converter.Converter;
import cn.suwg.springframework.core.convert.converter.ConverterFactory;
import cn.suwg.springframework.core.convert.converter.ConverterRegistry;
import cn.suwg.springframework.core.convert.converter.GenericConverter;
import cn.suwg.springframework.core.convert.support.DefaultConversionService;
import cn.suwg.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * @Author: suwg
 * @Date: 2024/11/7
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);

    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the "
                            + "Converter, ConverterFactory, or GenericConverter interfaces"
                            + "Unsupported converter type: " + converter.getClass());
                }
            }
        }
    }


    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
