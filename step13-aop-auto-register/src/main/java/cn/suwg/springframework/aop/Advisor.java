package cn.suwg.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * Base interface holding AOP <b>advice</b> (action to take at a joinpoint)
 * * and a filter determining the applicability of the advice (such as
 * * a pointcut). <i>This interface is not for use by Spring users, but to
 * * allow for commonality in support for different types of advice.</i>
 *
 * @Description: 通知.
 * @Author: suwg
 * @Date: 2024/10/23
 * 公众号： 趣研
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     * 返回这个切面（Aspect）的通知部分。通知可能是一个拦截器（Interceptor），前置通知（Before Advice），抛出通知（Throws Advice）等
     *
     * @return the advice that should apply if the pointcut matches.
     */
    Advice getAdvice();
}
