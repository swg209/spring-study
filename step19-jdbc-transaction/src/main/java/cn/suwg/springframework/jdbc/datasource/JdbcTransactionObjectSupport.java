package cn.suwg.springframework.jdbc.datasource;

/**
 * @Author: suwg
 * @Date: 2024/11/25
 */
public abstract class JdbcTransactionObjectSupport {

    private ConnectionHolder connectionHolder;

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public boolean hasConnectionHolder() {
        return null != this.connectionHolder;
    }
}
