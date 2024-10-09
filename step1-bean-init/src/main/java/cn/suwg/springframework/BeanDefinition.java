package cn.suwg.springframework;

/**
 * bean的定义.
 * @Author: suwg
 * @Date: 2024/10/9
 */
public class BeanDefinition {

    /**
     * bean的名称.
     */
    private Object bean;

    public BeanDefinition(Object bean){
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
