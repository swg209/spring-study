package cn.suwg.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Description: 代理支持
 * @Author: suwg
 * @Date: 2024/10/22
 * 公众号: 趣研
 */
public class AdvisedSupport {

    // 被代理对对象.
    private TargetSource targetSource;

    // 方法拦截器.
    private MethodInterceptor methodInterceptor;

    // 方法匹配器(检查目标方法是否符合通知条件).
    private MethodMatcher methodMatcher;

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }
}
