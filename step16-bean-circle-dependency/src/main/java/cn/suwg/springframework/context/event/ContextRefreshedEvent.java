package cn.suwg.springframework.context.event;

/**
 * 上下文对象刷新事件.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
