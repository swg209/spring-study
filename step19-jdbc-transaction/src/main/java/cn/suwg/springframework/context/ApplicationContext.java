package cn.suwg.springframework.context;

import cn.suwg.springframework.beans.factory.HierarchicalBeanFactory;
import cn.suwg.springframework.beans.factory.ListableBeanFactory;
import cn.suwg.springframework.core.io.ResourceLoader;

/**
 * 应用上下文.
 * <p>
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
