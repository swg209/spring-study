package cn.suwg.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户查询Dao.
 *
 * @Author: suwg
 * @Date: 2024/10/11
 * 公众号： 趣研
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();


    public void initDataMethod() {
        System.out.println("执行自定义初始化方法 init-method");
        hashMap.put("10001", "小苏");
        hashMap.put("10002", "小刘");
        hashMap.put("10003", "小小苏");
    }

    public void destroyDataMethod() {
        System.out.println("执行自定义销毁方法 destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
