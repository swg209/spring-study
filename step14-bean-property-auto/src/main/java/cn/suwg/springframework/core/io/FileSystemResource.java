package cn.suwg.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileSystemResource类，实现了Resource接口，用于处理文件系统中的资源文件。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class FileSystemResource implements Resource {

    // 表示资源的文件
    private final File file;

    // 表示资源的文件路径
    private final String path;

    /**
     * 构造函数，传入一个File对象
     *
     * @param file 表示资源的文件
     */
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    /**
     * 构造函数，传入一个路径字符串
     *
     * @param path 表示资源的文件路径
     */
    public FileSystemResource(String path) {
        this.file = new File((path));
        this.path = path;
    }

    /**
     * 获取输入流
     *
     * @return 输入流
     * @throws Exception 如果文件不存在，抛出异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    /**
     * 获取文件路径
     *
     * @return 文件路径
     */
    public final String getPath() {
        return this.path;
    }
}
