package cn.suwg.springframework.context;

/**
 * @Author: suwg
 * @Date: 2024/10/20
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
