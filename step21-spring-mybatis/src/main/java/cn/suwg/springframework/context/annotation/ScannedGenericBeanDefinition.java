package cn.suwg.springframework.context.annotation;

import cn.hutool.core.lang.Assert;
import cn.suwg.springframework.core.type.AnnotationMetadata;
import cn.suwg.springframework.core.type.classreading.MetadataReader;

/**
 * @Author: suwg
 * @Date: 2024/11/18
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(MetadataReader metadataReader) {
        Assert.notNull(metadataReader, "MetadataReader must not be null");
        this.metadata = metadataReader.getAnnotationMetadata();
        setBeanClassName(this.metadata.getClassName());
        setResource(metadataReader.getResource());
    }


}
