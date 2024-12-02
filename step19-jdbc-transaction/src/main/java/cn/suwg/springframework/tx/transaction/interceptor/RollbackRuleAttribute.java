package cn.suwg.springframework.tx.transaction.interceptor;

import java.io.Serializable;

/**
 * 事务回滚规则.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class RollbackRuleAttribute implements Serializable {

    private final String exceptionName;

    public RollbackRuleAttribute(Class<?> clazz) {
        this.exceptionName = clazz.getName();
    }
}
