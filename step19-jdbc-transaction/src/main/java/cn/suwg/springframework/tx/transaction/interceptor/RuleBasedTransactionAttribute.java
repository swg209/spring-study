package cn.suwg.springframework.tx.transaction.interceptor;

import java.io.Serializable;
import java.util.List;

/**
 * 基于规则的事务属性接口，判断是否事务回滚的规则.
 * TransactionAttribute implementation that works out whether a given exception
 * * should cause transaction rollback by applying a number of rollback rules,
 * * both positive and negative. If no custom rollback rules apply, this attribute
 * * behaves like DefaultTransactionAttribute (rolling back on runtime exceptions).
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class RuleBasedTransactionAttribute extends DefaultTransactionAttribute implements Serializable {


    private List<RollbackRuleAttribute> rollbackRules;

    public RuleBasedTransactionAttribute() {
        super();
    }

    public void setRollbackRules(List<RollbackRuleAttribute> rollbackRules) {
        this.rollbackRules = rollbackRules;
    }

}
