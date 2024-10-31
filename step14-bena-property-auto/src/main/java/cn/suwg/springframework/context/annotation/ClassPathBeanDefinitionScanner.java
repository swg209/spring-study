package cn.suwg.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import cn.suwg.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.suwg.springframework.stereotype.Component;

import java.util.Set;

/**
 * A bean definition scanner that detects bean candidates on the classpath,
 * * registering corresponding bean definitions with a given registry BeanFactory
 * * or ApplicationContext).
 *
 * @Description: 用于检测类路径上的bean候选项的bean定义扫描器，使用给定的注册表（BeanFactory或ApplicationContext）注册相应的bean定义。
 * @Author: suwg
 * @Date: 2024/10/29
 * 公众号： 趣研
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            // 扫描基础包下的Component注解的类
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                // 注册 Bean 定义
                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

        // 注册处理 注解的BeanPostProcessor (@Autowired、@Value)
        beanDefinitionRegistry.registerBeanDefinition("cn.suwg.springframework.context.annotation.internalAutowiredAnnotationProcessor",
                new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 确定 Bean 的名称.
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    /**
     * 解析 Bean 的作用域 singleton、prototype
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getBeanClass();
        Scope scope = clazz.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }


}
