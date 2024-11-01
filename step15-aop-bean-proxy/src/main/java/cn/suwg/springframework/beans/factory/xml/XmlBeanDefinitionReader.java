package cn.suwg.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanReference;
import cn.suwg.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.suwg.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.suwg.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import cn.suwg.springframework.core.io.Resource;
import cn.suwg.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * XmlBeanDefinitionReader类，继承自AbstractBeanDefinitionReader类。
 * * 主要用于从XML文件中读取Bean的定义，并将其注册到Bean定义注册表中。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 构造方法，接收一个Bean定义注册表作为参数。
     *
     * @param registry Bean定义注册表
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 构造方法，接收一个Bean定义注册表和一个资源加载器作为参数。
     *
     * @param registry       Bean定义注册表
     * @param resourceLoader 资源加载器
     */
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    /**
     * 从给定的资源中加载Bean定义。
     *
     * @param resource 要加载Bean定义的资源。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 从给定的资源数组中加载Bean定义。
     *
     * @param resources 要加载Bean定义的资源数组。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 从给定的位置字符串中加载Bean定义。
     *
     * @param location 资源的位置，可以是文件路径或者URL等。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }


    /**
     * 从给定的位置字符串数组中加载Bean定义。
     *
     * @param locations 资源的位置，可以是文件路径或者URL等。
     * @throws BeansException 如果在加载Bean定义过程中发生错误，抛出BeansException。
     */
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }


    /**
     * 从输入流中加载Bean定义。
     *
     * @param is 输入流
     * @throws ClassNotFoundException 如果在加载Bean定义过程中发生错误，抛出ClassNotFoundException。
     */
    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();

        // 解析 context:component-scan 标签，扫描包中的类并提取相关信息，用于组装 BeanDefinition
        Element componentScan = root.element("component-scan");
        if (null != componentScan) {
            String scanPath = componentScan.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The Value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");


            // 获取Class, 方便获取类型.
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);

            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            //处理属性
            List<Element> propertyList = bean.elements("property");
            //读取属性并填充
            for (Element property : propertyList) {
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值： 引入对象、值对象.
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }

            // 注册bean
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }


    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}