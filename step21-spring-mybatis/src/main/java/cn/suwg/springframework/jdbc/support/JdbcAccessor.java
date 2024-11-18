package cn.suwg.springframework.jdbc.support;

import cn.suwg.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;

/**
 * JDBC访问器.
 *
 * @Author: suwg
 * @Date: 2024/11/8
 */
public abstract class JdbcAccessor implements InitializingBean {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource obtainDataSource() {
        return getDataSource();
    }

    @Override
    public void afterPropertiesSet() {
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }
}
