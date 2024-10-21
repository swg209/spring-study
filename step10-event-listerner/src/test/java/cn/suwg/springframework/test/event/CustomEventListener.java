package cn.suwg.springframework.test.event;

import cn.suwg.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Author: suwg
 * @Date: 2024/10/21
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
