package cn.suwg.springframework.test.converter;

import cn.suwg.springframework.core.convert.converter.Converter;

/**
 * @Author: suwg
 * @Date: 2024/11/7
 */
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
