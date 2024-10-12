package cn.suwg.springframework.test.bean;

/**
 * 用户服务.
 *
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class UserService {

    private UserDao userDao;

    private String uid;

    public String queryUserInfo() {
        String result = "成功获取到userService，调用userDao查询用户信息:" + userDao.queryUserName(uid);
        return result;
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


}
