package cn.suwg.springframework.context.event;

import cn.suwg.springframework.context.ApplicationContext;
import cn.suwg.springframework.context.ApplicationEvent;

/**
 * 应用上下文事件.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }


    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
