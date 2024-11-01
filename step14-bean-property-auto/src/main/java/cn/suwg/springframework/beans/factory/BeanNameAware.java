package cn.suwg.springframework.beans.factory;

/**
 * Interface to be implemented by beans that want to be aware of their
 * bean name in a bean factory. Note that it is not usually recommended
 * that an object depend on its bean name, as this represents a potentially
 * brittle dependence on external configuration, as well as a possibly
 * unnecessary dependence on a Spring API.
 * <p>
 * <p>
 * 允许一个 bean 在被 Spring 容器创建并初始化时，
 * 能够感知到自己在 Spring 容器中的名称（bean name）。
 * 这个接口通常用于那些需要以某种方式根据它们在容器中的名称来配置或执行特定操作的 bean。
 *
 * @Author: suwg
 * @Date: 2024/10/16
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
