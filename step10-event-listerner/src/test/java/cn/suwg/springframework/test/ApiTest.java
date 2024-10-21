package cn.suwg.springframework.test;

import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.test.event.CustomEvent;
import org.junit.Test;


/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class ApiTest {

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 111111111L, "成功了！"));
        applicationContext.registerShutdownHook();

    }


}
