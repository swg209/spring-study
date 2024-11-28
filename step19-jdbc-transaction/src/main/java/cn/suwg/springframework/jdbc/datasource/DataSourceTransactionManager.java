package cn.suwg.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;
import cn.suwg.springframework.beans.BeansException;
import cn.suwg.springframework.beans.factory.InitializingBean;
import cn.suwg.springframework.tx.transaction.CannotCreateTransactionException;
import cn.suwg.springframework.tx.transaction.TransactionDefinition;
import cn.suwg.springframework.tx.transaction.TransactionException;
import cn.suwg.springframework.tx.transaction.support.AbstractPlatformTransactionManager;
import cn.suwg.springframework.tx.transaction.support.DefaultTransactionStatus;
import cn.suwg.springframework.tx.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据源事务管理器.
 *
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager implements InitializingBean {


    private DataSource dataSource;

    public DataSourceTransactionManager() {
    }

    public DataSourceTransactionManager(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.notNull(dataSource, "No DataSource set");
        return dataSource;
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(obtainDataSource());
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            con.commit();
        } catch (SQLException e) {
            throw new TransactionException("Could not commit JDBC transaction", e);
        }
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Could not roll back JDBC transaction", e);
        }
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
        Connection con = null;
        try {
            Connection newCon = obtainDataSource().getConnection();
            txObject.setConnectionHolder(new ConnectionHolder(newCon), true);

            con = txObject.getConnectionHolder().getConnection();
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prepareTransactionalConnection(con, definition);

            TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());

        } catch (SQLException e) {
            try {
                assert con != null;
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            txObject.setConnectionHolder(null, false);
            throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", e);
        }
    }

    protected void prepareTransactionalConnection(Connection con, TransactionDefinition definition) throws SQLException {
        if (definition.isReadOnly()) {
            try (Statement stmt = con.createStatement()) {
                stmt.execute("set transaction read only");
            }
        }
    }

    public static class DataSourceTransactionObject extends JdbcTransactionObjectSupport {
        private boolean newConnectionHolder;
        private boolean mustRestoreAutoCommit;

        public void setConnectionHolder(ConnectionHolder connectionHolder, boolean newConnectionHolder) {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }
    }
}
