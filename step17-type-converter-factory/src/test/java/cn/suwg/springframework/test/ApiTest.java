package cn.suwg.springframework.test;

import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.test.bean.Husband;
import cn.suwg.springframework.test.converter.StringToIntegerConverter;
import cn.suwg.springframework.test.converter.StringToLocalDateConverter;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class ApiTest {


    @Test
    public void testConvert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果: " + husband);
    }


    //返回转换好的日期. 年-月-日
    @Test
    public void testStringToLocalDateConverter() {
        StringToLocalDateConverter converter = new StringToLocalDateConverter("yyyy-MM-dd hh:mm:ss");
        LocalDate convert = converter.convert("2024-11-07 10:00:11");
        LocalDate expect = LocalDate.of(2024, 11, 7);
        Assert.assertEquals(expect, convert);
        System.out.println(convert);
    }


    // 测试字符串转换为整数.
    @Test
    public void testStringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer convert = converter.convert("12345");
        Assert.assertEquals(Integer.valueOf(12345), convert);
        System.out.println(convert);
    }
}
