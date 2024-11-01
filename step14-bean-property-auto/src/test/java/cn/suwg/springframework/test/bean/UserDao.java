package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: suwg
 * @Date: 2024/10/31
 */
@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();


    static {
        hashMap.put("10001", "小苏，广东，广州");
        hashMap.put("10002", "小小苏，广东，深圳");
        hashMap.put("10003", "小小小苏，广东，珠海");
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }
}
