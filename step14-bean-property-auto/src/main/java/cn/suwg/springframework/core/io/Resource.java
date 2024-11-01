package cn.suwg.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载接口.
 *
 * @Author: suwg
 * @Date: 2024/10/12
 * 公众号：趣研
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}