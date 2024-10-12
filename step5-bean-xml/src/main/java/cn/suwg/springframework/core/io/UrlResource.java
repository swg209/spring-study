package cn.suwg.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlResource类，实现了Resource接口，用于处理URL资源（远程文件）。
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public class UrlResource implements Resource {

    // URL资源
    private final URL url;

    /**
     * 构造函数，传入一个URL对象
     *
     * @param url URL资源
     */
    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    /**
     * 获取输入流
     *
     * @return 输入流
     * @throws IOException 如果URL无法打开连接，抛出IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            // 如果连接是HttpURLConnection，断开连接
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}