package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: suwg
 * @Date: 2024/10/17
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            // 添加排除方法.
            if ("toString".equals(method.getName())) {
                return this.toString();
            }

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小苏");
            hashMap.put("10002", "小刘");
            hashMap.put("10003", "小小苏");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0]).toString();
        };

        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
