package cn.suwg.springframework.test;

import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.jdbc.core.JdbcTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author: suwg
 * @Date: 2024/11/8
 */
public class JdbcTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }


    /**
     * 建表.
     */

    @Test
    public void executeSqlTest() {

        jdbcTemplate.execute("CREATE TABLE user (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
                "  `username` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '用户名',\n" +
                "  PRIMARY KEY (id)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';");
    }

    /**
     * 插入数据.
     */
    @Test
    public void executeInsertSqlTest() {
        //插入语句
        jdbcTemplate.execute("INSERT INTO user (username) values ('小苏');");
    }


    @Test
    public void queryForListTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user");
        for (int i = 0; i < allResult.size(); i++) {
            System.out.printf("第%d行数据", i + 1);
            Map<String, Object> objectMap = allResult.get(i);
            System.out.println(objectMap);
        }
    }
    
}
