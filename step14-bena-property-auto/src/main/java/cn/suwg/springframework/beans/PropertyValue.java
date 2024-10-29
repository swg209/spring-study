package cn.suwg.springframework.beans;

/**
 * bean属性信息.
 * @Author: suwg
 * @Date: 2024/10/11
 * 公众号： 趣研
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
