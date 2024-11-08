package cn.suwg.springframework.jdbc.core;

import java.sql.SQLException;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public interface PreparedStatementSetter {

    void setValues(java.sql.PreparedStatement ps) throws SQLException;
}
