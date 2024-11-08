package cn.suwg.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;
import cn.suwg.springframework.jdbc.CannotGetJdbcConnectionException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Helper class that provides static methods for obtaining JDBC {@code Connection}s
 * * from a {@link javax.sql.DataSource}. Includes special support for Spring-managed
 * * transactional {@code Connection}s, for example, managed by DataSourceTransactionManager
 * * or @link org.springframework.transaction.jta.JtaTransactionManager.
 *
 * @Description: 数据源工具抽象类
 * @Author: suwg
 * @Date: 2024/11/8
 * 公众号：趣研
 */
public abstract class DataSourceUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceUtils.class);

    /**
     * 获取连接.
     *
     * @param dataSource
     * @return
     */

    public static Connection getConnection(DataSource dataSource) {
        try {
            return doGetConnection(dataSource);
        } catch (SQLException e) {
            throw new CannotGetJdbcConnectionException("Failed to obtain JDBC Connection", e);
        }
    }

    public static Connection doGetConnection(DataSource dataSource) throws SQLException {
        Assert.notNull(dataSource, "No DataSource specified");
        Connection connection = fetchConnection(dataSource);
        return connection;
    }

    private static Connection fetchConnection(DataSource dataSource) throws SQLException {
        Connection con = dataSource.getConnection();
        if (null == con) {
            throw new IllegalArgumentException("DataSource return null from getConnection():" + dataSource);
        }
        return con;
    }


    /**
     * 释放连接.
     */
    public static void releaseConnection(Connection con, DataSource dataSource) {
        try {
            doReleaseConnection(con, dataSource);
        } catch (SQLException ex) {
            logger.debug("Could not close JDBC Connection", ex);
        } catch (Throwable ex) {
            logger.debug("Unexpected exception on closing JDBC Connection", ex);
        }
    }

    private static void doReleaseConnection(Connection con, DataSource dataSource) throws SQLException {
        if (null == con) {
            return;
        }
        doCloseConnection(con, dataSource);
    }

    private static void doCloseConnection(Connection con, DataSource dataSource) throws SQLException {
        con.close();
    }


}
