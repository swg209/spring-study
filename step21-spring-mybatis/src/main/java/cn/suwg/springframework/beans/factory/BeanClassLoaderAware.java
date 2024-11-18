package cn.suwg.springframework.beans.factory;

/**
 * Callback that allows a bean to be aware of the bean
 * {@link ClassLoader class loader}; that is, the class loader used by the
 * present bean factory to load bean classes.
 * <p>
 * 允许bean感知bean类加载器的回调；也就是说，当前bean工厂用于加载bean类的类加载器。
 *
 * @Author: suwg
 * @Date: 2024/10/16
 * 公众号： 趣研
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
