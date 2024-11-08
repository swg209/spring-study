package cn.suwg.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public interface ResultSetExtractor<T> {
    T extractData(ResultSet rs) throws SQLException;
}
