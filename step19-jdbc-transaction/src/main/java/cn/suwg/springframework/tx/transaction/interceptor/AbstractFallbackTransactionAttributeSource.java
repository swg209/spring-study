package cn.suwg.springframework.tx.transaction.interceptor;

import cn.suwg.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: suwg
 * @Date: 2024/11/25
 */
public abstract class AbstractFallbackTransactionAttributeSource implements TransactionAttributeSource {
    private static final TransactionAttribute NULL_TRANSACTION_ATTRIBUTE = new DefaultTransactionAttribute() {
        @Override
        public String toString() {
            return "null";
        }
    };
    private final Map<Object, TransactionAttribute> attributeCache = new ConcurrentHashMap<>(1024);

    @Override
    public TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class) {
            return null;
        }
        Object cacheKey = getCacheKey(method, targetClass);
        TransactionAttribute cached = this.attributeCache.get(cacheKey);
        if (null != cached) {
            if (cached == NULL_TRANSACTION_ATTRIBUTE) {
                return null;
            } else {
                return cached;
            }
        } else {
            TransactionAttribute txAttr = computeTransactionAttribute(method, targetClass);
            if (null == txAttr) {
                this.attributeCache.put(cacheKey, NULL_TRANSACTION_ATTRIBUTE);
            } else {
                this.attributeCache.put(cacheKey, txAttr);
            }
            return txAttr;
        }
    }

    protected Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }

    protected TransactionAttribute computeTransactionAttribute(Method method, Class<?> targetClass) {
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        TransactionAttribute txAttr = findTransactionAttribute(method);
        if (null != txAttr) {
            return txAttr;
        }
        return findTransactionAttribute(method.getDeclaringClass());
    }

    /**
     * 在方法上查找事务的相关属性
     */
    protected abstract TransactionAttribute findTransactionAttribute(Method method);

    protected abstract TransactionAttribute findTransactionAttribute(Class<?> clazz);
}
