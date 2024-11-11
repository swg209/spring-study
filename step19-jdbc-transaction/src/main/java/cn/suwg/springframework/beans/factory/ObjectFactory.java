package cn.suwg.springframework.beans.factory;

import cn.suwg.springframework.beans.BeansException;

/**
 * 定义一个工厂，当调用时可以返回一个对象实例（可能是共享的或独立的）。
 *
 * @Author: suwg
 * @Date: 2024/11/4
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
