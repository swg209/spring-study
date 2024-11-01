package cn.suwg.springframework.beans.factory.config;

import cn.suwg.springframework.beans.factory.BeanFactory;

/**
 * 这个接口是一个标记接口，扩展了BeanFactory。
 * 实现这个接口的类具有自动装配bean的能力。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
}