package cn.suwg.springframework.mybatis.test.po;

import java.util.Date;

/**
 * 用户信息.
 *
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class User {
    private Long id;
    private String userId;          // 用户ID
    private String userName;    // 昵称
    private String userHead;        // 头像
    private String userPassword;    // 密码
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNickName) {
        this.userName = userNickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
