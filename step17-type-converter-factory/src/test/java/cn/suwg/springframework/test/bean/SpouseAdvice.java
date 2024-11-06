package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: suwg
 * @Date: 2024/11/5
 */
public class SpouseAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("关怀小两口(切面)：" + method);
    }
}
