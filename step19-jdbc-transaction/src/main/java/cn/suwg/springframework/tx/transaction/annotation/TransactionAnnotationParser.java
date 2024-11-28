package cn.suwg.springframework.tx.transaction.annotation;

import cn.suwg.springframework.tx.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 事务注解解析器.
 * 用于解析已知事务注释类型的策略接口.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface TransactionAnnotationParser {

    TransactionAttribute parseTransactionAnnotation(AnnotatedElement element);
}
