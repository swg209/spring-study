package cn.suwg.springframework.aop.framework.autoproxy;

import cn.suwg.springframework.aop.AdvisedSupport;
import cn.suwg.springframework.aop.Advisor;
import cn.suwg.springframework.aop.ClassFilter;
import cn.suwg.springframework.aop.Pointcut;
import cn.suwg.springframework.aop.TargetSource;
import cn.suwg.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import cn.suwg.springframework.aop.framework.ProxyFactory;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValues;
import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.beans.factory.BeanFactoryAware;
import cn.suwg.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import cn.suwg.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * BeanPostProcessor implementation that creates AOP proxies based on all candidate
 * Advisors in the current BeanFactory. This class is completely generic; it contains
 * no special code to handle any particular aspects, such as pooling aspects.
 * 默认的Advisor自动代理创建器
 *
 * @Author: suwg
 * @Date: 2024/10/23
 * 公众号： 趣研
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<Object>());
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            //过滤匹配类
            if (!classFilter.matches(bean.getClass())) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(new TargetSource(bean));
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setProxyTargetClass(false);

            //返回代理对象.
            return new ProxyFactory(advisedSupport).getProxy();
        }


        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }


    /**
     * 判断当前类是否为基础设施类: Advice/Pointcut/Advisor
     *
     * @param beanClass
     * @return
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }


    /**
     * 获取早期引用，半成品.
     *
     * @param bean
     * @param beanName
     * @return
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    protected Object wrapIfNecessary(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            //过滤匹配类
            if (!classFilter.matches(bean.getClass())) {
                continue;
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            //这里需要修改为true， 不然proxy代理的对象是没有实现接口的.
            //这导致在不使用目标类的情况下创建代理。如果目标类没有实现任何接口，这可能会导致问题，因为代理将无法正确委托方法调用。
            advisedSupport.setProxyTargetClass(true);

            //返回代理对象.
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }
}
