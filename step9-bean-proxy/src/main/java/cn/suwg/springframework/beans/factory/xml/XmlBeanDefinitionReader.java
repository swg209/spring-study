package cn.suwg.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.PropertyValue;
import cn.suwg.springframework.beans.factory.config.BeanDefinition;
import cn.suwg.springframework.beans.factory.config.BeanReference;
import cn.suwg.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.suwg.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.suwg.springframework.core.io.Resource;
import cn.suwg.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;


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
        } catch (IOException | ClassNotFoundException e) {
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
    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(is);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String initMethod = bean.getAttribute("init-method");
            String destroyMethod = bean.getAttribute("destroy-method");

            // 获取 Class，方便获取类中的名称
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


            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析标签：property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}