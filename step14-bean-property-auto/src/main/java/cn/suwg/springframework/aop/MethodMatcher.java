package cn.suwg.springframework.aop;

import java.lang.reflect.Method;

/**
 * Part of a Pointcut: Checks whether the target method is eligible for advice.
 *
 * @Author: suwg
 * @Date: 2024/10/22
 */
public interface MethodMatcher {


    /**
     * Perform static checking whether the given method matches. If this
     *
     * @param method
     * @param targetClass
     * @return whether or not this method matches statically
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
