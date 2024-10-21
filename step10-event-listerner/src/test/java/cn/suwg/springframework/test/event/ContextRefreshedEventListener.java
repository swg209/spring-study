package cn.suwg.springframework.test.event;

import cn.suwg.springframework.context.ApplicationListener;
import cn.suwg.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: suwg
 * @Date: 2024/10/21
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
