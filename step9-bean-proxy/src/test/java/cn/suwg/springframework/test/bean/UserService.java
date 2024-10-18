package cn.suwg.springframework.test.bean;

/**
 * 用户服务.
 *
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class UserService {

    private IUserDao userDao;

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

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
