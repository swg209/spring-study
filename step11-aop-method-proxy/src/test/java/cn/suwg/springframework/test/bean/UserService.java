package cn.suwg.springframework.test.bean;

import java.util.Random;

/**
 * @Author: suwg
 * @Date: 2024/10/22
 */
public class UserService implements IUserService {
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
}
