package cn.suwg.springframework.mybatis;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.FactoryBean;
import cn.suwg.springframework.beans.factory.InitializingBean;
import cn.suwg.springframework.core.io.DefaultResourceLoader;
import cn.suwg.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * 会话工厂Bean.
 *
 * @Author: suwg
 * @Date: 2024/11/18
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {


    // 资源路径
    private String resource;

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return sqlSessionFactory.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(this.resource);

        try (InputStream inputStream = resource.getInputStream()) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
