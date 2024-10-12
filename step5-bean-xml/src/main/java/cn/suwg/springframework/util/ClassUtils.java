package cn.suwg.springframework.util;

import java.util.Objects;

/**
 * 类加载工具类.
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class ClassUtils {

    /**
     * 获取默认的类加载器。
     * 首先尝试获取当前线程的上下文类加载器。
     * 如果无法获取或者获取到的是null，那么就使用这个类的类加载器。
     *
     * @return 默认的类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            // 尝试获取当前线程的上下文类加载器
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // 无法获取线程上下文类加载器，回退到使用这个类的类加载器
        }
        if (Objects.isNull(cl)) {
            // 如果线程上下文类加载器是null，使用这个类的类加载器
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}