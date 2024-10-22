package cn.suwg.springframework.context;

/**
 * 应用上下文扩展接口.
 * <p>
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods
 *
 * @Author: suwg
 * @Date: 2024/10/14
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器.
     *
     * @throws Exception
     */
    void refresh() throws Exception;

    /**
     * 注册调用关闭钩子.
     */
    void registerShutdownHook();

    /**
     * 关闭容器.
     */
    void close();
}
