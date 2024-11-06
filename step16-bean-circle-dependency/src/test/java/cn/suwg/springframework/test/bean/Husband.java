package cn.suwg.springframework.test.bean;

/**
 * @Author: suwg
 * @Date: 2024/11/5
 */
public class Husband {

    private Wife wife;


    public String queryWife() {
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}
