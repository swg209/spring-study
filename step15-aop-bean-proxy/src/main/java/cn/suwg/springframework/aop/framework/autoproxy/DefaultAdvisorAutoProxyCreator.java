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
}
