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
     * 作用域.
     */
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


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
     * 作用域.
     */
    private String scope;

    /**
     * 是否单例.
     */
    private boolean singleton = true;

    /**
     * 是否原型.
     */
    private boolean prototype = false;


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


    /**
     * 设置作用域.
     *
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    /**
     * 是否单例.
     *
     * @return
     */
    public boolean isSingleton() {
        return singleton;
    }

    /**
     * 是否原型.
     *
     * @return
     */
    public boolean isPrototype() {
        return prototype;
    }

}
