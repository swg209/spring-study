package cn.suwg.springframework.test.bean;

/**
 * @Author: suwg
 * @Date: 2024/11/5
 */
public class Wife {

    private Husband husband;

    private IMother mother; // 婆婆

    public String queryHusband() {
        return "Wife.husband.Mother.takeCare：" + mother.takeCare();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}
