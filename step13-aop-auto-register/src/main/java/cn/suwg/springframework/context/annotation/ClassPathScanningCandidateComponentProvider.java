package cn.suwg.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A component provider that scans the classpath from a base package. It then
 * applies exclude and include filters to the resulting classes to find candidates.
 *
 * @Description: 用于扫描基础包的组件提供程序。然后将排除和包含过滤器应用于结果类以查找候选项。
 * @Author: suwg
 * @Date: 2024/10/29
 * 公众号： 趣研
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描基础包下的Component注解的类.
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
