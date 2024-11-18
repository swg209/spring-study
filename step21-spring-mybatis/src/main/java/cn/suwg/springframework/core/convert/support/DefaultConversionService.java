package cn.suwg.springframework.core.convert.support;

import cn.suwg.springframework.core.convert.converter.ConverterRegistry;

/**
 * 默认的类型转换服务.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂.
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());

    }
}
