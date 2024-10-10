package cn.suwg.springframework.beans.factory.config;

/**
 * bean的定义.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class BeanDefinition {

    /**
     * bean的类.
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
