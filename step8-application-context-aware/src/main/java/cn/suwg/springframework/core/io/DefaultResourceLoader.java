package cn.suwg.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DefaultResourceLoader类，实现了ResourceLoader接口，用于加载资源。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * 根据给定的位置字符串，获取对应的资源。
     * 如果位置字符串以"classpath:"开头，那么返回一个ClassPathResource对象。
     * 否则，尝试将位置字符串转换为URL，如果成功，返回一个UrlResource对象。
     * 如果转换失败（抛出MalformedURLException），那么返回一个FileSystemResource对象。
     *
     * @param location 资源的位置，可以是文件路径或者URL等。
     * @return 对应的资源对象。
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
