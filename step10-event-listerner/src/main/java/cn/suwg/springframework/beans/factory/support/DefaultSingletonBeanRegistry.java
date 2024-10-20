package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.DisposableBean;
import cn.suwg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例bean注册表.
 *
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();


    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>();

    /**
     * 获取单例对象.
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }


    /**
     * 添加单例对象.
     *
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    /**
     * 注册一个DisposableBean的实现.
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /**
     * 销毁所有单例bean.
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
