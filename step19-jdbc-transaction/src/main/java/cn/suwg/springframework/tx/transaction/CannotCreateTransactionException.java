package cn.suwg.springframework.tx.transaction;

/**
 * @description Exception thrown when a transaction can't be created using an
 * underlying transaction API such as JTA.
 * 中文描述：当使用底层事务API（如JTA）无法创建事务时抛出异常。
 */
public class CannotCreateTransactionException extends TransactionException {

    public CannotCreateTransactionException(String message) {
        super(message);
    }

    public CannotCreateTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

}
