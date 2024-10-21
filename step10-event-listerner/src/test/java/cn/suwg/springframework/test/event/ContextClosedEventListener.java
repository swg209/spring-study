package cn.suwg.springframework.test.event;

import cn.suwg.springframework.context.ApplicationListener;
import cn.suwg.springframework.context.event.ContextClosedEvent;

/**
 * @Author: suwg
 * @Date: 2024/10/21
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
