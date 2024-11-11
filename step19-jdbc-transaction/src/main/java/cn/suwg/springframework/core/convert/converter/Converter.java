package cn.suwg.springframework.core.convert.converter;

/**
 * 定义类型转换接口.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 * 公众号： 趣研
 */
public interface Converter<S, T> {

    /**
     * 将source从 S类型转换为T类型.
     */
    T convert(S source);
}
