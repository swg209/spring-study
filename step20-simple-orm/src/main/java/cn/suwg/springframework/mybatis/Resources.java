package cn.suwg.springframework.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 资源读取解析工具类.
 *
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class Resources {

    /**
     * 获取资源文件.
     *
     * @param resource
     * @return
     */
    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));

    }

    private static InputStream getResourceAsStream(String resource) throws IOException {
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (null != inputStream) {
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }

    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()};
    }
}
