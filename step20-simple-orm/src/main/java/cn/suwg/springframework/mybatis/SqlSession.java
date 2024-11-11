package cn.suwg.springframework.mybatis;

import java.util.List;

/**
 * sql会话接口类.
 *
 * @Author: suwg
 * @Date: 2024/3/25
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object parameter);

    <T> T selectOne(String statement);

    <T> List<T> selectList(String statement, Object parameter);

    <T> List<T> selectList(String statement);

    void close();

}
