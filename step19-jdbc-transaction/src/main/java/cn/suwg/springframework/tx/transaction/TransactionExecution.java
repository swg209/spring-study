package cn.suwg.springframework.tx.transaction;

/**
 * Common representation of the current state of a transaction.
 * * Serves as base interface for {@link TransactionStatus} as well as
 * 中文注释：事务的当前状态的通用表示。作为{@link TransactionStatus}的基本接口
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface TransactionExecution {

    /**
     * Return whether the present transaction is new; otherwise participating
     * in an existing transaction, or potentially not running in an actual
     * transaction in the first place.
     */
    boolean isNewTransaction();

    /**
     * Set the transaction rollback-only. This instructs the transaction manager
     * that the only possible outcome of the transaction may be a rollback, as
     * alternative to throwing an exception which would in turn trigger a rollback.
     */
    void setRollbackOnly();

    /**
     * Return whether the transaction has been marked as rollback-only
     * (either by the application or by the transaction infrastructure).
     */
    boolean isRollbackOnly();

    /**
     * Return whether this transaction is completed, that is,
     * whether it has already been committed or rolled back.
     */
    boolean isCompleted();
}
