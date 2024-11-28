package cn.suwg.springframework.tx.transaction.support;

/**
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class DefaultTransactionStatus extends AbstractTransactionStatus {
    private final Object transaction;

    private final boolean newTransaction;

    public DefaultTransactionStatus(Object transaction, boolean newTransaction) {
        this.transaction = transaction;
        this.newTransaction = newTransaction;
    }

    public Object getTransaction() {
        return transaction;
    }

    public boolean hasTransaction() {
        return this.transaction != null;
    }

    @Override
    public boolean isNewTransaction() {
        return hasTransaction() && this.newTransaction;
    }
}
