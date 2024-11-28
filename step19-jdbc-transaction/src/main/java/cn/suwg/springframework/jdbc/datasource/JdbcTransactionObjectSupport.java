package cn.suwg.springframework.jdbc.datasource;

/**
 * @description Convenient base class for JDBC-aware transaction objects.
 * @date 2022/3/16
 * /CodeDesignTutorials
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
