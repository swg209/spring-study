package cn.suwg.springframework.aop;

import cn.suwg.springframework.util.ClassUtils;

/**
 * 被代理的目标对象.
 *
 * @Author: suwg
 * @Date: 2024/10/22
 * 公众号: 趣研
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }


    /**
     * 获取目标对象类.
     *
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * 获取目标对象.
     */
    public Object getTarget() {
        return this.target;
    }

}
