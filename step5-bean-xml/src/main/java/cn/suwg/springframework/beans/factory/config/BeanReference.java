package cn.suwg.springframework.beans.factory.config;

/**
 * bean的引用.
 * @Author: suwg
 * @Date: 2024/10/11
 * 公众号： 趣研
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
