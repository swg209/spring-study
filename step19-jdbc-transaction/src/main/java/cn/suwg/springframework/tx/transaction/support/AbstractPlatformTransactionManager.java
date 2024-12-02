package cn.suwg.springframework.tx.transaction.support;

import cn.suwg.springframework.tx.transaction.PlatformTransactionManager;
import cn.suwg.springframework.tx.transaction.TransactionDefinition;
import cn.suwg.springframework.tx.transaction.TransactionException;
import cn.suwg.springframework.tx.transaction.TransactionStatus;

import java.io.Serializable;

/**
 * @description Abstract base class that implements Spring's standard transaction workflow,
 * serving as basis for concrete platform transaction managers.
 * 中文描述：实现Spring标准事务工作流程的抽象基类，作为具体平台事务管理器的基础。
 * @Author: suwg
 * @Date: 2024/11/25
 */
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager, Serializable {
    @Override
    public final TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        Object transaction = doGetTransaction();
        if (null == definition) {
            definition = new DefaultTransactionDefinition();
        }
        if (definition.getTimeout() < TransactionDefinition.TIMEOUT_DEFAULT) {
            throw new TransactionException("Invalid transaction timeout " + definition.getTimeout());
        }
        // 暂定事务传播为默认的行为
        DefaultTransactionStatus status = newTransactionStatus(definition, transaction, true);

        // 开始事务
        doBegin(transaction, definition);
        return status;
    }

    protected DefaultTransactionStatus newTransactionStatus(TransactionDefinition definition, Object transaction, boolean newTransaction) {
        return new DefaultTransactionStatus(transaction, newTransaction);
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()) {
            throw new IllegalArgumentException(
                    "Transaction is already completed - do not call or rollback more than once per transaction");
        }
        DefaultTransactionStatus defStatus = (DefaultTransactionStatus) status;
        processCommit(defStatus);
    }

    private void processCommit(DefaultTransactionStatus status) throws TransactionException {
        doCommit(status);
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()) {
            throw new IllegalArgumentException(
                    "Transaction is already completed - do not call commit or rollback more than once per transaction");
        }
        DefaultTransactionStatus defStatus = (DefaultTransactionStatus) status;
        processRollback(defStatus, false);
    }

    private void processRollback(DefaultTransactionStatus status, boolean unexpected) {
        doRollback(status);
    }


    /**
     * 获取事务
     */
    protected abstract Object doGetTransaction() throws TransactionException;

    /**
     * 提交事务
     */
    protected abstract void doCommit(DefaultTransactionStatus status) throws TransactionException;

    /**
     * 事务回滚
     */
    protected abstract void doRollback(DefaultTransactionStatus status) throws TransactionException;

    /**
     * 开始事务
     */
    protected abstract void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException;
}
