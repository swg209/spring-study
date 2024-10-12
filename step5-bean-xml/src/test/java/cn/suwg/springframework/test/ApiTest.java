package cn.suwg.springframework.test;

import cn.hutool.core.io.IoUtil;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanReference;
import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.suwg.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.suwg.springframework.core.io.DefaultResourceLoader;
import cn.suwg.springframework.core.io.Resource;
import cn.suwg.springframework.test.bean.UserDao;
import cn.suwg.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Test
    public void testBeanFactory() {
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2、UserDao注册.
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        //3、UserService 设置属性 uid、userDao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10003"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        //4、UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("UserService", beanDefinition);

        //5、UserService获取bean对象
        UserService userService = (UserService) beanFactory.getBean("UserService");
        userService.queryUserInfo();

        //4、再次获取UserService bean对象，验证单例.
        UserService userServiceSingleton = (UserService) beanFactory.getSingleton("UserService");
        userServiceSingleton.queryUserInfo();
    }

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
