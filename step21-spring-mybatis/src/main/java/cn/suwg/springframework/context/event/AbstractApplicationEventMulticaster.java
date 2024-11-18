package cn.suwg.springframework.context.event;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.beans.factory.BeanFactoryAware;
import cn.suwg.springframework.context.ApplicationEvent;
import cn.suwg.springframework.context.ApplicationListener;
import cn.suwg.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 事件广播器抽象类,处理通用逻辑.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    // 存储所有的事件监听器
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    // BeanFactory实例
    private BeanFactory beanFactory;

    // 添加事件监听器
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    // 移除事件监听器
    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    // 设置BeanFactory实例
    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    // 获取支持特定事件的所有监听器
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    // 判断监听器是否支持特定事件
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        // 获取目标类
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 获取实际类型参数
        Type actualTpeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTpeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name" + className);
        }

        // 判断事件类是否与监听器支持的事件类型兼容
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
