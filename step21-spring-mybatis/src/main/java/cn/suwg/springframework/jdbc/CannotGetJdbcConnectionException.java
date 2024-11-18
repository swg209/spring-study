package cn.suwg.springframework.jdbc;

import java.sql.SQLException;

/**
 * 无法获取JDBC连接异常.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class CannotGetJdbcConnectionException extends RuntimeException {

    public CannotGetJdbcConnectionException(String message) {
        super(message);
    }

    public CannotGetJdbcConnectionException(String message, SQLException ex) {
        super(message, ex);
    }

}
