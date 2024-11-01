package cn.suwg.springframework.beans;

/**
 * 定义Bean异常.
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研
 */
public class BeansException extends RuntimeException{

    /**
     * 构造函数.
     * @param msg
     */
    public BeansException(String msg) {
        super(msg);
    }


    /**
     * 构造函数.
     * @param msg
     * @param cause
     */
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
