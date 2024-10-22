package cn.suwg.springframework.context.event;

import cn.suwg.springframework.context.ApplicationEvent;
import cn.suwg.springframework.context.ApplicationListener;

/**
 * 事件广播器.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public interface ApplicationEventMulticaster {


    /**
     * Add a listener to be notified of all events.
     *
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     *
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
