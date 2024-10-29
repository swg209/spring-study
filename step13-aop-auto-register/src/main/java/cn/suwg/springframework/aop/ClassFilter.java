package cn.suwg.springframework.aop;

/**
 * Filter that restricts matching of a pointcut or introduction to
 * a given set of target classes.
 *
 * @Description: 类过滤器
 * @Author: suwg
 * @Date: 2024/10/22
 * 公众号: 趣研
 */
public interface ClassFilter {


    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     *              * @return whether the advice should apply to the given target class
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);

}
