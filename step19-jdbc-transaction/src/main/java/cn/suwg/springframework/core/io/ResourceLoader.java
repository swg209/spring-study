package cn.suwg.springframework.core.io;

/**
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface ResourceLoader {

    /**
     * 假定类路径: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";


    /**
     * 根据给定的位置字符串，获取对应的资源。
     *
     * @param location 资源的位置，可以是文件路径或者URL等。
     * @return 对应的资源对象。
     */
    Resource getResource(String location);

}