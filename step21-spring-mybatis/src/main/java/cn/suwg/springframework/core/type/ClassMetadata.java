package cn.suwg.springframework.core.type;

/**
 * 类元数据接口.
 *
 * @Author: suwg
 * @Date: 2024/11/18
 */
public interface ClassMetadata {

    /**
     * 获取类名.
     *
     * @return 类名
     */
    String getClassName();

    /**
     * 是否是接口.
     *
     * @return 是否是接口
     */
    boolean isInterface();

    /**
     * 是否是注解.
     *
     * @return 是否是注解
     */
    boolean isAnnotation();

    /**
     * 是否是抽象类.
     *
     * @return 是否是抽象类
     */
    boolean isAbstract();

    /**
     * 是否是具体类.
     *
     * @return 是否是具体类
     */
    boolean isConcrete();

    /**
     * 是否是final类.
     *
     * @return 是否是final类
     */
    boolean isFinal();

    /**
     * 是否是独立类.
     *
     * @return 是否是独立类
     */
    boolean isIndependent();

    /**
     * 是否有父类.
     *
     * @return 是否有父类
     */
    boolean hasSuperClass();

}

