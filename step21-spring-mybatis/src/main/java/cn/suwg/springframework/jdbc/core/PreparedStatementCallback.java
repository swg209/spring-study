package cn.suwg.springframework.jdbc.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public interface PreparedStatementCallback<T> {

    T doInPreparedStatement(PreparedStatement ps) throws SQLException;
}
