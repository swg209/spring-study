package cn.suwg.springframework.jdbc.support;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.suwg.springframework.jdbc.UncategorizedSQLException;
import cn.suwg.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import cn.suwg.springframework.jdbc.core.ColumnMapRowMapper;
import cn.suwg.springframework.jdbc.core.JdbcOperations;
import cn.suwg.springframework.jdbc.core.PreparedStatementCallback;
import cn.suwg.springframework.jdbc.core.PreparedStatementCreator;
import cn.suwg.springframework.jdbc.core.PreparedStatementSetter;
import cn.suwg.springframework.jdbc.core.ResultSetExtractor;
import cn.suwg.springframework.jdbc.core.RowMapper;
import cn.suwg.springframework.jdbc.core.RowMapperResultSetExtractor;
import cn.suwg.springframework.jdbc.core.SingleColumnRowMapper;
import cn.suwg.springframework.jdbc.core.SqlProvider;
import cn.suwg.springframework.jdbc.core.StatementCallback;
import cn.suwg.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class JdbcTemplate extends JdbcAccessor implements JdbcOperations {

    private int fetchSize = 1;

    private int maxRows = 1;

    private int queryTimeout = -1;

    public JdbcTemplate() {
    }

    public JdbcTemplate(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    private static <T> T result(T result) {
        Assert.state(null != result, "No result");
        return result;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    @Override
    public <T> T execute(StatementCallback<T> action) {
        return execute(action, true);
    }

    @Override
    public void execute(String sql) {

        class ExecuteStatementCallback implements StatementCallback<Object>, SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public Object doInStatement(Statement statement) throws SQLException {
                statement.execute(sql);
                return null;
            }
        }
        execute(new ExecuteStatementCallback(), true);

    }

    public <T> T execute(StatementCallback<T> action, boolean closeResources) {
        Connection con = DataSourceUtils.getConnection(obtainDataSource());

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            applyStatementSettings(stmt);

            return action.doInStatement(stmt);

        } catch (SQLException e) {
            String sql = getSql(action);
            JdbcUtils.closeStatement(stmt);
            stmt = null;
            throw new UncategorizedSQLException("ConnectionCallback", sql, e);
        } finally {
            if (closeResources) {
                JdbcUtils.closeStatement(stmt);
            }
        }
    }

    protected UncategorizedSQLException translateException(String connectionCallback, String sql, SQLException e) {
        return new UncategorizedSQLException(connectionCallback, sql, e);
    }

    protected void applyStatementSettings(Statement stat) throws SQLException {
        int fetchSize = getFetchSize();
        if (fetchSize != -1) {
            stat.setFetchSize(fetchSize);
        }
        int maxRows = getMaxRows();
        if (maxRows != -1) {
            stat.setMaxRows(maxRows);
        }
    }

    private String getSql(Object sqlProvider) {
        if (sqlProvider instanceof SqlProvider) {
            return ((SqlProvider) sqlProvider).getSql();
        }
        return null;
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> rse) {
        Assert.notNull(sql, "SQL must not be null");
        Assert.notNull(rse, "ResultSetExtractor must not be null");

        class QueryStatementCallback implements StatementCallback<T>, SqlProvider {

            @Override
            public T doInStatement(Statement statement) throws SQLException {
                ResultSet resultSet = statement.executeQuery(sql);
                return rse.extractData(resultSet);
            }

            @Override
            public String getSql() {
                return sql;
            }
        }

        return execute(new QueryStatementCallback(), true);
    }

    @Override
    public <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse) {
        return query(sql, newArgumentPreparedStatementSetter(args), rse);
    }

    @Override
    public <T> T query(String sql, PreparedStatementSetter pss, ResultSetExtractor<T> rse) {
        return query(new SimplePreparedStatementCreator(sql), pss, rse);
    }

    public <T> T query(
            PreparedStatementCreator psc, final PreparedStatementSetter pss, final ResultSetExtractor<T> rse) {

        Assert.notNull(rse, "ResultSetExtractor must not be null");

        return execute(psc, new PreparedStatementCallback<T>() {
            @Override
            public T doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ResultSet rs = null;
                try {
                    if (pss != null) {
                        pss.setValues(ps);
                    }
                    rs = ps.executeQuery();
                    return rse.extractData(rs);
                } finally {
                    JdbcUtils.closeResultSet(rs);
                }
            }
        }, true);
    }

    private PreparedStatementSetter newArgumentPreparedStatementSetter(Object[] args) {
        return new ArgumentPreparedStatementSetter(args);
    }

    private <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action, boolean closeResources) {

        Assert.notNull(psc, "PreparedStatementCreator must not be null");
        Assert.notNull(action, "Callback object must not be null");


        Connection con = DataSourceUtils.getConnection(obtainDataSource());
        PreparedStatement ps = null;
        try {
            ps = psc.createPreparedStatement(con);
            applyStatementSettings(ps);
            T result = action.doInPreparedStatement(ps);
            return result;
        } catch (SQLException ex) {

            String sql = getSql(psc);
            psc = null;
            JdbcUtils.closeStatement(ps);
            ps = null;
            DataSourceUtils.releaseConnection(con, getDataSource());
            con = null;
            throw translateException("PreparedStatementCallback", sql, ex);
        } finally {
            if (closeResources) {

                JdbcUtils.closeStatement(ps);
                DataSourceUtils.releaseConnection(con, getDataSource());
            }
        }
    }


    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return result(query(sql, new RowMapperResultSetExtractor<>(rowMapper)));
    }

    @Override
    public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        return result(query(sql, args, new RowMapperResultSetExtractor<>(rowMapper)));
    }


    @Override
    public List<Map<String, Object>> queryForList(String sql) {
        return query(sql, getColumnMapRowMapper());
    }

    protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
        return new ColumnMapRowMapper();
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType) {
        return query(sql, getSingleColumnRowMapper(elementType));
    }

    private <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requireType) {
        return new SingleColumnRowMapper<>(requireType);
    }

    @Override
    public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args) {
        return query(sql, args, getSingleColumnRowMapper(elementType));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return query(sql, args, getColumnMapRowMapper());
    }


    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
        List<T> result = query(sql, rowMapper);

        if (CollUtil.isEmpty(result)) {
            throw new RuntimeException("Incorrect result size: expected 1 actual 0");
        }
        if (result.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1 actual " + result.size());
        }

        return result.iterator().next();
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        List<T> result = query(sql, args, new RowMapperResultSetExtractor<>(rowMapper, 1));

        if (CollUtil.isEmpty(result)) {
            throw new RuntimeException("Incorrect result size: expected 1 actual 0");
        }
        if (result.size() > 1) {
            throw new RuntimeException("Incorrect result size: expected 1 actual " + result.size());
        }

        return result.iterator().next();
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType) {
        return queryForObject(sql, getSingleColumnRowMapper(requiredType));
    }

    @Override
    public Map<String, Object> queryForMap(String sql) {
        return result(queryForObject(sql, getColumnMapRowMapper()));
    }

    @Override
    public Map<String, Object> queryForMap(String sql, Object... args) {
        return result(queryForObject(sql, args, getColumnMapRowMapper()));
    }

    private static class SimplePreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

        private final String sql;

        public SimplePreparedStatementCreator(String sql) {
            Assert.notNull(sql, "SQL must not be null");
            this.sql = sql;
        }

        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            return con.prepareStatement(this.sql);
        }

        @Override
        public String getSql() {
            return this.sql;
        }
    }
}
