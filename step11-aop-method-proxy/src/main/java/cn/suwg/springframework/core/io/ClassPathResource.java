package cn.suwg.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.suwg.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


/**
 * ClassPathResource类，用于处理类路径下的资源文件。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class ClassPathResource implements Resource {

    // 资源文件的路径
    private final String path;
    // 类加载器
    private ClassLoader classLoader;

    /**
     * 构造函数，只传入路径，类加载器默认为null
     *
     * @param path 资源文件的路径
     */
    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    /**
     * 构造函数，传入路径和类加载器
     *
     * @param path        资源文件的路径
     * @param classLoader 类加载器
     */
    public ClassPathResource(String path, ClassLoader classLoader) {
        // 路径不能为空
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        // 如果类加载器为null，使用默认的类加载器
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    /**
     * 获取输入流
     *
     * @return 输入流
     * @throws Exception 如果文件不存在，抛出FileNotFoundException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        // 通过类加载器和路径获取输入流
        InputStream is = classLoader.getResourceAsStream(path);
        // 如果输入流为null，说明文件不存在，抛出FileNotFoundException
        if (Objects.isNull(is)) {
            throw new FileNotFoundException(path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
