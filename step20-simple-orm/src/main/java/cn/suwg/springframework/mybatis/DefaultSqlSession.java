package cn.suwg.springframework.mybatis;

import org.apache.commons.lang.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认SQL会话.
 *
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class DefaultSqlSession implements SqlSession {

    //连接信息.
    private Connection connection;

    //配置信息.
    private Map<String, XNode> mapperElement;

    public DefaultSqlSession(Connection connection, Map<String, XNode> mapperElement) {
        this.connection = connection;
        this.mapperElement = mapperElement;
    }


    @Override
    public <T> T selectOne(String statement) {
        try {
            XNode xNode = mapperElement.get(statement);
            PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //每次遍历一行值
            while (resultSet.next()) {
                //创建对象
                T obj = (T) clazz.newInstance();
                //遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名
                    String columnName = metaData.getColumnName(i);
                    //驼峰命名
                    columnName = WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");

                    //获取列值
                    Object value = resultSet.getObject(i);
                    //获取set方法
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);//调用set方法
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());//调用set方法
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        XNode xNode = mapperElement.get(statement);
        Map<Integer, String> parameterMap = xNode.getParameter();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
            buildParameter(preparedStatement, parameter, parameterMap);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void buildParameter(PreparedStatement preparedStatement,
                                Object parameter,
                                Map<Integer, String> parameterMap) throws SQLException, IllegalAccessException {
        int size = parameterMap.size();
        // 单个参数
        if (parameter instanceof Long) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setLong(i, Long.parseLong(parameter.toString()));
            }
            return;
        }

        if (parameter instanceof Integer) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setInt(i, Integer.parseInt(parameter.toString()));
            }
            return;
        }

        if (parameter instanceof String) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setString(i, parameter.toString());
            }
            return;
        }

        Map<String, Object> fieldMap = new HashMap<>();
        // 对象参数
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            field.setAccessible(true);
            Object obj = field.get(parameter);
            field.setAccessible(false);
            fieldMap.put(name, obj);
        }

        for (int i = 1; i <= size; i++) {
            String parameterDefine = parameterMap.get(i);
            Object obj = fieldMap.get(parameterDefine);

            if (obj instanceof Short) {
                preparedStatement.setShort(i, Short.parseShort(obj.toString()));
                continue;
            }

            if (obj instanceof Integer) {
                preparedStatement.setInt(i, Integer.parseInt(obj.toString()));
                continue;
            }

            if (obj instanceof Long) {
                preparedStatement.setLong(i, Long.parseLong(obj.toString()));
                continue;
            }

            if (obj instanceof String) {
                preparedStatement.setString(i, obj.toString());
                continue;
            }

            if (obj instanceof Date) {
                preparedStatement.setDate(i, (java.sql.Date) obj);
            }

        }
    }


    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        try {
            XNode xNode = mapperElement.get(statement);
            PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
            buildParameter(preparedStatement, parameter, xNode.getParameter());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statement) {
        try {
            XNode xNode = mapperElement.get(statement);
            PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
