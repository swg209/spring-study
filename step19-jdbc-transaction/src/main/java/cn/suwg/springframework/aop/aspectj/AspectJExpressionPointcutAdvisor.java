package cn.suwg.springframework.aop.aspectj;

import cn.suwg.springframework.aop.Pointcut;
import cn.suwg.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 *
 * @Author: suwg
 * @Date: 2024/10/23
 * 公众号： 趣研
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法
    private Advice advice;

    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
