package cn.suwg.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * 连接处理实现类.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class SimpleConnectionHandler implements ConnectionHandler {

    private final Connection connection;

    public SimpleConnectionHandler(Connection connection) {
        Assert.notNull(connection, "Connection must not be null");
        this.connection = connection;
    }


    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
