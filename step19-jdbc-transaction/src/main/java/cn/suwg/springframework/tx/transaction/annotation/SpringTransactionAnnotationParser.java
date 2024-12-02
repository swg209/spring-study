package cn.suwg.springframework.tx.transaction.annotation;

import cn.suwg.springframework.core.annotation.AnnotatedElementUtils;
import cn.suwg.springframework.core.annotation.AnnotationAttributes;
import cn.suwg.springframework.tx.transaction.interceptor.RollbackRuleAttribute;
import cn.suwg.springframework.tx.transaction.interceptor.RuleBasedTransactionAttribute;
import cn.suwg.springframework.tx.transaction.interceptor.TransactionAttribute;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析事务spring注解方法.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class SpringTransactionAnnotationParser implements TransactionAnnotationParser, Serializable {
    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement element) {
        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
                element, Transactional.class, false, false);
        if (null != attributes) {
            return parseTransactionAnnotation(attributes);
        } else {
            return null;
        }
    }


    protected TransactionAttribute parseTransactionAnnotation(AnnotationAttributes attributes) {
        RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();

        List<RollbackRuleAttribute> rollbackRuleAttributeList = new ArrayList<>();
        for (Class<?> rbRule : attributes.getClassArray("rollbackFor")) {
            rollbackRuleAttributeList.add(new RollbackRuleAttribute(rbRule));
        }

        ruleBasedTransactionAttribute.setRollbackRules(rollbackRuleAttributeList);
        return ruleBasedTransactionAttribute;

    }
}
