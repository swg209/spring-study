package cn.suwg.springframework.context.event;

import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.context.ApplicationEvent;
import cn.suwg.springframework.context.ApplicationListener;

/**
 * Simple implementation of the  ApplicationEventMulticaster interface.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
