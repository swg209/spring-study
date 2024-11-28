package cn.suwg.springframework.core.annotation;

/**
 * Thrown by {@link AnnotationUtils} and <em>synthesized annotations</em>
 * * if an annotation is improperly configured.
 * 中文描述：如果注解配置不正确，则由{@link AnnotationUtils}和<em>合成注解</em>抛出.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class AnnotationConfigurationException extends RuntimeException {

    public AnnotationConfigurationException(String message) {
        super(message);
    }

    public AnnotationConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
