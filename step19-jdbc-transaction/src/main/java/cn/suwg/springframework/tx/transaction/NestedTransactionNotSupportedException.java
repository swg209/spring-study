package cn.suwg.springframework.tx.transaction;

/**
 * @description Exception thrown when attempting to work with a nested transaction
 * but nested transactions are not supported by the underlying backend.
 * 中文描述：尝试使用嵌套事务时抛出的异常，但底层后端不支持嵌套事务。
 */
public class NestedTransactionNotSupportedException extends CannotCreateTransactionException {

    public NestedTransactionNotSupportedException(String message) {
        super(message);
    }

    public NestedTransactionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
