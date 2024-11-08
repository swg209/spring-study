package cn.suwg.springframework.jdbc.core;

import cn.suwg.springframework.jdbc.IncorrectResultSetColumnCountException;
import cn.suwg.springframework.jdbc.support.JdbcUtils;
import cn.suwg.springframework.util.NumberUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 单列行转换.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */

public class SingleColumnRowMapper<T> implements RowMapper<T> {

    private Class<?> requireType;


    public SingleColumnRowMapper() {

    }

    public SingleColumnRowMapper(Class<?> requireType) {
        this.requireType = requireType;
    }


    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (columnCount != 1) {
            throw new IncorrectResultSetColumnCountException(1, columnCount);
        }
        Object result = getColumnValue(rs, 1, this.requireType);
        if (result != null && this.requireType != null && !this.requireType.isInstance(result)) {
            // Extracted value does not match already: try to convert it.
            try {
                return (T) convertValueToRequiredType(result, this.requireType);
            } catch (IllegalArgumentException ex) {

            }
        }

        return (T) result;
    }

    protected Object getColumnValue(ResultSet rs, int index, Class<?> requireType) throws SQLException {
        if (null != requireType) {
            return JdbcUtils.getResultSetValue(rs, index, requireType);
        } else {
            return getColumnValue(rs, index);
        }
    }

    private Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }

    private Object convertValueToRequiredType(Object value, Class<?> requiredType) {
        if (String.class == requiredType) {
            return value.toString();
        } else if (Number.class.isAssignableFrom(requiredType)) {
            if (value instanceof Number) {
                // Convert original Number to target Number class.
                return NumberUtils.convertNumberToTargetClass(((Number) value), (Class<Number>) requiredType);
            } else {
                // Convert stringified value to target Number class.
                return NumberUtils.parseNumber(value.toString(), (Class<Number>) requiredType);
            }
        }
        //这里暂时不添加spring-core里的类型转换处理
//        else if (this.conversionService != null && this.conversionService.canConvert(value.getClass(), requiredType)) {
//            return this.conversionService.convert(value, requiredType);
//        }
        else {
            throw new IllegalArgumentException(
                    "Value [" + value + "] is of type [" + value.getClass().getName() +
                            "] and cannot be converted to required type [" + requiredType.getName() + "]");
        }
    }


}
