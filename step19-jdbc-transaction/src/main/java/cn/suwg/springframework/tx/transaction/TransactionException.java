package cn.suwg.springframework.tx.transaction;

/**
 * 事务异常.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
