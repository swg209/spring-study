package cn.suwg.springframework.aop;

/**
 * @Description: 切点
 * @Author: suwg
 * @Date: 2024/10/21
 * 公众号: 趣研
 */
public interface Pointcut {

    /**
     * 获取类过滤器.
     *
     * @return
     */
    ClassFilter getClassFilter();


    /**
     * 获取方法匹配器.
     *
     * @return
     */
    MethodMatcher getMethodMatcher();


}
