package cn.suwg.springframework.test.bean;

/**
 * 用户服务.
 * @Author: suwg
 * @Date: 2024/10/9
 * 公众号： 趣研
 */
public class UserService {

    private String name;

    public UserService(String name){
        this.name = name;
    }
    public void queryUserInfo(){
        System.out.println("成功获取到userService， 查询用户信息:"+name);
    }


}
