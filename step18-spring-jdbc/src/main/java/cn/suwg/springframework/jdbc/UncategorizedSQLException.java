package cn.suwg.springframework.jdbc;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class UncategorizedSQLException extends RuntimeException {

    public UncategorizedSQLException(String message) {
        super(message);
    }

    public UncategorizedSQLException(String task, String sql, Throwable cause) {
        super(sql, cause);
    }

}
