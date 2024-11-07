package cn.suwg.springframework.test.bean;

import java.time.LocalDate;

/**
 * @Author: suwg
 * @Date: 2024/11/5
 */
public class Husband {

    private String wifeName;

    private LocalDate marriageDate;

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "wifeName='" + wifeName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }
}
