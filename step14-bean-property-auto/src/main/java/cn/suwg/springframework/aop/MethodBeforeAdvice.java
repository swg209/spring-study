package cn.suwg.springframework.aop;

import java.lang.reflect.Method;

/**
 * Advice invoked before a method is invoked. Such advices cannot
 * prevent the method call proceeding, unless they throw a Throwable.
 *
 * @Author: suwg
 * @Date: 2024/10/23
 */
public interface MethodBeforeAdvice extends BeforeAdvice {


    /**
     * 前置通知.
     * Callback before a given method is invoked.
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
