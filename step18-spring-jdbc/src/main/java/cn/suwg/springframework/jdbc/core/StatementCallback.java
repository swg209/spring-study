package cn.suwg.springframework.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 回调接口.
 * Callback 接口及其子类（如 ConnectionCallback、StatementCallback、PreparedStatementCallback 等）：
 * 这些回调接口允许用户在 JdbcTemplate 执行数据库操作时插入自定义代码，从而提供了更灵活的操作方式。
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */
public interface StatementCallback<T> {

    T doInStatement(Statement statement) throws SQLException;
}
