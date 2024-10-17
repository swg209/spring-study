package cn.suwg.springframework.beans.factory.config;

import cn.suwg.springframework.beans.PropertyValues;

/**
 * bean的定义.
 *
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class BeanDefinition {

    /**
     * bean的类.
     */
    private Class beanClass;

    /**
     * bean的属性.
     *
     * @param beanClass
     */
    private PropertyValues propertyValues;

    /**
     * 初始化方法名.
     */
    private String initMethodName;

    /**
     * 销毁方法名.
     */
    private String destroyMethodName;


    /**
     * 不带属性的构造方法.
     *
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    /**
     * 带属性的构造方法.
     *
     * @param beanClass
     * @param propertyValues
     */
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }


    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
