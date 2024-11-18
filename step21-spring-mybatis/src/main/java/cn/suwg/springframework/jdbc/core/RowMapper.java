package cn.suwg.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * sql行转换.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws SQLException;

}
