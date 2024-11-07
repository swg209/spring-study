package cn.suwg.springframework.core.convert;

/**
 * 类型转换接口服务.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 */
public interface ConversionService {

    /**
     * 是否可以转换.
     *
     * @param sourceType
     * @param targetType
     * @return
     */
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * 转换.
     *
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convert(Object source, Class<T> targetType);
}
