package cn.suwg.springframework.context.event;

/**
 * 上下文对象关闭事件.
 *
 * @Author: suwg
 * @Date: 2024/10/20
 * 公众号：趣研
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
