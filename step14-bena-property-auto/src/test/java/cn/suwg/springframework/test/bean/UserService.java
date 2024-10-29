package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: suwg
 * @Date: 2024/10/22
 */
@Component("userService")
public class UserService implements IUserService {

    private String token;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小苏，111111，广州";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户: " + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = {" + token + "}";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
