package cn.suwg.springframework.beans.factory;

/**
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual subinterfaces, but should typically
 * consist of just one void-returning method that accepts a single
 * argument.
 * <p>
 * 用于标记实现了此接口的类，可以感知所属的 ApplicationContext
 *
 * @Author: suwg
 * @Date: 2024/10/16
 * 公众号： 趣研
 */
public interface Aware {

}
