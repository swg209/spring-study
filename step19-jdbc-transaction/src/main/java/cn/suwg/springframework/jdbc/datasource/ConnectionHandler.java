package cn.suwg.springframework.jdbc.datasource;

import java.sql.Connection;

/**
 * @Description: 连接处理器
 * @Author: suwg
 * @Date: 2024/11/8
 * 公众号：趣研
 */
public interface ConnectionHandler {

    /**
     * Fetch the JDBC Connection that this handle refers to.
     */
    Connection getConnection();

    /**
     * Release the JDBC Connection that this handle refers to.
     * <p>The default implementation is empty, assuming that the lifecycle
     * of the connection is managed externally.
     *
     * @param con the JDBC Connection to release
     */
    default void releaseConnection(Connection con) {
    }
}
