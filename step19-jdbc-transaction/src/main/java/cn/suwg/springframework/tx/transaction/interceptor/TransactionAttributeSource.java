package cn.suwg.springframework.tx.transaction.interceptor;

import java.lang.reflect.Method;

/**
 * Strategy interface used by {@link TransactionInterceptor} for metadata retrieval.
 * 中文注释：事务拦截器用于元数据检索的策略接口。
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface TransactionAttributeSource {

    TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass);
}
