package cn.suwg.springframework.core.convert.support;

import cn.suwg.springframework.core.convert.converter.Converter;
import cn.suwg.springframework.core.convert.converter.ConverterFactory;
import cn.suwg.springframework.util.NumberUtils;

/**
 * 字符串转数字转换工厂.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 * 公众号： 趣研
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber(targetType);
    }


    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        private StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }


}
