package cn.suwg.springframework.tx.transaction;

/**
 * Interface that specifies an API to programmatically manage transaction
 * * savepoints in a generic fashion. Extended by TransactionStatus to
 * * expose savepoint management functionality for a specific transaction.
 * 中文注释：指定一个API以通用方式以编程方式管理事务保存点。由TransactionStatus扩展
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface SavepointManager {

    Object createSavepoint() throws TransactionException;

    void rollbackToSavepoint(Object savepoint) throws TransactionException;

    void releaseSavepoint(Object savepoint) throws TransactionException;

}
