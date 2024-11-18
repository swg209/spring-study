package cn.suwg.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * Resource holder wrapping a JDBC Connection.
 * DataSourceTransactionManager binds instances of this class
 * to the thread, for a specific {@link javax.sql.DataSource}.
 * <p>
 * 连接持有者.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 * 公众号：趣研
 */
public class ConnectionHolder {

    private ConnectionHandler connectionHandler;

    private Connection currentConnection;

    public ConnectionHolder(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConnectionHolder(Connection connection) {
        this.connectionHandler = new SimpleConnectionHandler(connection);
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    protected boolean hasConnectionHandler() {
        return this.connectionHandler != null;
    }

    protected Connection getConnection() {
        Assert.notNull(this.connectionHandler, "Active connection is required.");
        if (null == this.currentConnection) {
            this.currentConnection = this.connectionHandler.getConnection();
        }
        return this.currentConnection;
    }

    protected void setConnection(Connection connection) {
        if (null != this.currentConnection) {
            if (null != this.connectionHandler) {
                this.connectionHandler.releaseConnection(this.currentConnection);
            }
            this.currentConnection = null;
        }

        if (null != connection) {
            this.connectionHandler = new SimpleConnectionHandler(connection);
        } else {
            this.connectionHandler = null;
        }

    }

}
