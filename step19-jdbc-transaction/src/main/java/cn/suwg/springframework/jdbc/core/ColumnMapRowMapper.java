package cn.suwg.springframework.jdbc.core;

import cn.suwg.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class ColumnMapRowMapper implements RowMapper<Map<String, Object>> {


    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Object> mapOfColumnValues = createColumnMap(rs.getMetaData().getColumnCount());
        for (int i = 1; i <= columnCount; i++) {
            String columnName = JdbcUtils.lookupColumnName(metaData, i);
            mapOfColumnValues.putIfAbsent(getColumnKey(columnName), getColumnValue(rs, i));
        }
        return mapOfColumnValues;
    }

    protected String getColumnKey(String columnName) {
        return columnName;
    }

    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }

    private Map<String, Object> createColumnMap(int columnCount) {
        return new LinkedHashMap<>(columnCount);
    }
}
