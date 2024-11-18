package cn.suwg.springframework.core.io;

import java.io.IOException;

/**
 * 资源模式解析器.
 *
 * @Author: suwg
 * @Date: 2024/11/15
 */
public interface ResourcePatternResolver extends ResourceLoader {

    String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    Resource[] getResources(String locationPattern) throws IOException;
}


import org.springframework.core.type.AnnotationMetadata;
        import org.springframework.core.type.ClassMetadata;
        import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
        import org.springframework.core.type.classreading.MetadataReader;

