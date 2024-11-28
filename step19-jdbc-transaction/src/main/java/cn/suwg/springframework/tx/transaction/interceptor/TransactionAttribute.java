package cn.suwg.springframework.tx.transaction.interceptor;

import cn.suwg.springframework.tx.transaction.TransactionDefinition;

/**
 * 事务属性接口.
 * 处理异常.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface TransactionAttribute extends TransactionDefinition {

    boolean rollbackOn(Throwable ex);
}
