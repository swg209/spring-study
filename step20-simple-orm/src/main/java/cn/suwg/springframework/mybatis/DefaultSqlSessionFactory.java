package cn.suwg.springframework.mybatis;

/**
 * @Author: suwg
 * @Date: 2024/3/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        // 创建会话
        return new DefaultSqlSession(configuration.connection, configuration.mapperElement);
    }
}
