package cn.suwg.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * Base class for AOP proxy configuration managers.
 * These are not themselves AOP proxies, but subclasses of this class are
 * normally factories from which AOP proxy instances are obtained directly.
 *
 * @Description: 代理支持, 切面
 * @Author: suwg
 * @Date: 2024/10/22
 * 公众号: 趣研
 */
public class AdvisedSupport {

    // 是否使用cglib代理.
    private boolean proxyTargetClass = false;

    // 被代理对对象.
    private TargetSource targetSource;

    // 方法拦截器.
    private MethodInterceptor methodInterceptor;

    // 方法匹配器(检查目标方法是否符合通知条件).
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }


    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

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
