package cn.suwg.springframework.mybatis;

import cn.suwg.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Mapper工厂Bean.
 *
 * @Author: suwg
 * @Date: 2024/11/18
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<T> mapperInterface, SqlSessionFactory sqlSessionFactory) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionFactory = sqlSessionFactory;
    }


    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = ((proxy, method, args) -> {
            // 排除 Object 方法；toString、hashCode
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
            try {
                System.out.println("你被代理了，执行SQL操作！" + method.getName());
                return sqlSessionFactory.openSession().selectOne(mapperInterface.getName() + "." + method.getName(), args[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return method.getReturnType().newInstance();
        });


        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterface}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
