package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.DisposableBean;
import cn.suwg.springframework.beans.factory.InitializingBean;

/**
 * 用户服务.
 *
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class UserService implements InitializingBean, DisposableBean {

    private UserDao userDao;

    private String uid;

    private String company;

    private String location;


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
    public void destroy() throws Exception {
        System.out.println("执行: UserService.destroy销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("执行: UserService.afterPropertiesSet初始化方法");
    }
}
