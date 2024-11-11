package cn.suwg.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * 通用转换接口.
 *
 * @Author: suwg
 * @Date: 2024/11/6
 */
public interface GenericConverter {

    /**
     * Return the source and target types that this converter can convert between.
     * 返回此转换器可以在其之间转换的源和目标类型.
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * Convert the source object to the targetType described by the {@code TypeDescriptor}.
     * 将源对象转换为由{@code TypeDescriptor}描述的targetType.
     *
     * @param source
     * @param sourceType
     * @param targetType
     * @return
     */
    Object convert(Object source, Class sourceType, Class targetType);


    /**
     * Holder for a source-to-target class pair.
     * 源类型到目标类型的持有者.
     */
    final class ConvertiblePair {

        private final Class<?> sourceType;

        private final Class<?> targetType;


        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "source type must not be null");
            Assert.notNull(targetType, "target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return this.sourceType;
        }

        public Class<?> getTargetType() {
            return this.targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }
}
