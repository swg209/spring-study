package cn.suwg.springframework.context.support;

import cn.suwg.springframework.beans.BeansException;

/**
 * XML文件应用上下文.
 * <p>
 * Standalone XML application context, taking the context definition files
 * from the class path, interpreting plain paths as class path resource names
 * that include the package path (e.g. "mypackage/myresource.txt"). Useful for
 * test harnesses as well as for application contexts embedded within JARs.
 *
 * @Author: suwg
 * @Date: 2024/10/14
 * 公众号：趣研
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }


    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文.
     *
     * @param configLocation
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }


    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }


    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
