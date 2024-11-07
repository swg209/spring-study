package cn.suwg.springframework.core.convert.converter;

/**
 * 类型转换注册接口.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 * 公众号： 趣研
 */
public interface ConverterRegistry {


    void addConverter(Converter<?, ?> converter);


    void addConverter(GenericConverter converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);


}
