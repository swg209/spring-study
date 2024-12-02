package cn.suwg.springframework.tx.transaction;

/**
 * @description This is the central interface in Spring's imperative transaction infrastructure.
 * * Applications can use this directly, but it is not primarily meant as an API:
 * * Typically, applications will work with either TransactionTemplate or
 * * declarative transaction demarcation through AOP.
 * 中文描述：这是Spring命令式事务基础设施中的中央接口。
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
