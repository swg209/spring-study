package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;

/**
 * Interface to be implemented by beans that need to react once all their
 * properties have been set by a BeanFactory: for example, to perform custom
 * initialization, or merely to check that all mandatory properties have been set.
 * <p>
 * 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理，如：执行自定义初始化，或者仅仅检查是否设置了所有强制属性。
 *
 * @Author: suwg
 * @Date: 2024/10/15
 * 公众号： 趣研
 */
public interface InitializingBean {

    /**
     * 初始化方法, 在Bean处理完属性填充后调用.
     */
    void afterPropertiesSet() throws BeansException;


}
