package cn.suwg.springframework.core.convert.converter;

/**
 * 类型转换工厂接口.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 * 公众号： 趣研
 */
public interface ConverterFactory<S, R> {

    /**
     * 获取转换器，将source从S类型转换为T类型，其中T也是R的实例。
     *
     * @param targetType
     * @param <T>
     * @return
     */

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
