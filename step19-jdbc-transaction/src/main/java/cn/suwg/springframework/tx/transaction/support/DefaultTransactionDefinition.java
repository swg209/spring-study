package cn.suwg.springframework.tx.transaction.support;

import cn.suwg.springframework.tx.transaction.TransactionDefinition;

import java.io.Serializable;

/**
 * 事务定义默认实现类.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class DefaultTransactionDefinition implements TransactionDefinition, Serializable {


    /**
     * 事务传播方式.
     */
    private int propagationBehavior = PROPAGATION_REQUIRED;

    /**
     * 事务隔离级别.
     */
    private int isolationLevel = ISOLATION_DEFAULT;

    /**
     * 超时时间.
     */
    private int timeout = TIMEOUT_DEFAULT;

    /**
     * 是否只读.
     */
    private boolean readOnly = false;

    /**
     * 事务名称.
     */
    private String name;

    public DefaultTransactionDefinition() {
    }

    public DefaultTransactionDefinition(TransactionDefinition other) {
        this.propagationBehavior = other.getPropagationBehavior();
        this.isolationLevel = other.getIsolationLevel();
        this.timeout = other.getTimeout();
        this.readOnly = other.isReadOnly();
        this.name = other.getName();
    }

    public DefaultTransactionDefinition(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    @Override
    public int getPropagationBehavior() {
        return this.propagationBehavior;
    }

    public void setPropagationBehavior(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    @Override
    public int getIsolationLevel() {
        return this.isolationLevel;
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public boolean isReadOnly() {
        return this.readOnly;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
