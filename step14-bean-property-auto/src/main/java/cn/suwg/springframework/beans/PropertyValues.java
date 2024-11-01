package cn.suwg.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * bean多属性信息.
 * @Author: suwg
 * @Date: 2024/10/11
 * 公众号： 趣研
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 添加属性信息.
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 获取属性信息.
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }


    /**
     * 获取指定属性信息.
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
