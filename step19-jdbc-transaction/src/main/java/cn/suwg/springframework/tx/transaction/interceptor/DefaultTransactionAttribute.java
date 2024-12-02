package cn.suwg.springframework.tx.transaction.interceptor;

import cn.suwg.springframework.tx.transaction.support.DefaultTransactionDefinition;

/**
 * 默认的事务属性.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class DefaultTransactionAttribute extends DefaultTransactionDefinition implements TransactionAttribute {


    public DefaultTransactionAttribute() {
        super();
    }

    @Override
    public boolean rollbackOn(Throwable ex) {
        return (ex instanceof RuntimeException || ex instanceof Error);
    }

    @Override
    public String toString() {
        return "DefaultTransactionAttribute{}";
    }
}
