package cn.suwg.springframework.test;

import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.test.bean.IUserService;
import org.junit.Test;


/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class ApiTest {

    @Test
    public void testAutoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
