package cn.suwg.springframework.beans.factory.support;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.DisposableBean;
import cn.suwg.springframework.beans.factory.ObjectFactory;
import cn.suwg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    /**
     * 二级缓存，提前暴露对象，没有完全实例化的对象
     */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>();
    /**
     * 一级缓存，普通对象
     * Cache of singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * 三级缓存， 存放代理对象.
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();


    /**
     * Cache of disposable bean instances: bean name --> disposable instance
     */
    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    /**
     * 获取单例对象.
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        //查看一级缓存.
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            //查看二级缓存.
            singletonObject = earlySingletonObjects.get(beanName);
            if (null == singletonObject) {
                //查看三级缓存.
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (null != singletonFactory) {
                    singletonObject = singletonFactory.getObject();
                    //将三级缓存中的代理对象获取出来，放入二级缓存中.
                    earlySingletonObjects.put(beanName, singletonObject);
                    //从三级缓存中移除.
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
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
