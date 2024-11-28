package cn.suwg.springframework.tx.transaction;

import java.io.Flushable;
import java.io.IOException;

/**
 * 事务状态
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public interface TransactionStatus extends SavepointManager, TransactionExecution, Flushable {

    boolean hasSavepoint();


    @Override
    void flush() throws IOException;
}
