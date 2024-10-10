package cn.suwg.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean工厂.
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class BeanFactory {

    /**
     *  存储bean的定义.
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 注册bean.
     * @param beanName bean的名称
     * @param beanDefinition bean的定义
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 获取bean.
     * @param beanName bean的名称
     * @return bean
     */
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

}
