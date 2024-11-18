package cn.suwg.springframework.beans.factory;

/**
 * Interface to be implemented by beans that want to release resources
 * on destruction. A BeanFactory is supposed to invoke the destroy
 * method if it disposes a cached singleton. An application context
 * is supposed to dispose all of its singletons on close.
 *
 * @Author: suwg
 * @Date: 2024/10/15
 * 公众号： 趣研
 */
public interface DisposableBean {

    /**
     * Bean销毁方法.
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
