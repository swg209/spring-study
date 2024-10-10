package cn.suwg.springframework.beans.factory.config;

import java.util.Objects;

/**
 * 单例注册表.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
