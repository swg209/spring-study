package cn.suwg.springframework.test.bean;

import cn.suwg.springframework.jdbc.UncategorizedSQLException;
import cn.suwg.springframework.jdbc.core.JdbcTemplate;
import cn.suwg.springframework.tx.transaction.annotation.Transactional;

/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/11/25
 */
public class JdbcService {


    /**
     * 使用注解事务.
     */
    @Transactional(rollbackFor = UncategorizedSQLException.class)
    public void saveData(JdbcTemplate jdbcTemplate) {
        System.out.println("保存数据,带事务处理");
        jdbcTemplate.execute("insert into user (id, username) values (4, '小苏1')");
        jdbcTemplate.execute("insert into user (id, username) values (4, '小苏2')");
    }

    public void saveDataWithoutTx(JdbcTemplate jdbcTemplate) {
        System.out.println("保存数据,不带事务");
        jdbcTemplate.execute("insert into user (id, username) values (4, '小苏1')");
        jdbcTemplate.execute("insert into user (id, username) values (4, '小苏2')");
    }
}
