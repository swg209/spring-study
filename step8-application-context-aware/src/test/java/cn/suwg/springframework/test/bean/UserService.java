package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.BeanClassLoaderAware;
import cn.suwg.springframework.beans.factory.BeanFactory;
import cn.suwg.springframework.beans.factory.BeanFactoryAware;
import cn.suwg.springframework.beans.factory.BeanNameAware;
import cn.suwg.springframework.context.ApplicationContext;
import cn.suwg.springframework.context.ApplicationContextAware;

/**
 * 用户服务.
 *
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private UserDao userDao;

    private String uid;

    private String company;

    private String location;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;


    public String queryUserInfo() {
        String result = "成功获取到userService，调用userDao查询用户信息. 姓名:" + userDao.queryUserName(uid)
                + " 公司：" + company + " 地址：" + location;
        return result;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware: ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("BeanFactoryAware：" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware: Bean Name is：" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContextAware：" + applicationContext);
    }
}
