package cn.suwg.springframework.test;

import cn.suwg.springframework.aop.AdvisedSupport;
import cn.suwg.springframework.aop.TargetSource;
import cn.suwg.springframework.aop.aspectj.AspectJExpressionPointcut;
import cn.suwg.springframework.aop.framework.Cglib2AopProxy;
import cn.suwg.springframework.context.support.ClassPathXmlApplicationContext;
import cn.suwg.springframework.jdbc.datasource.DataSourceTransactionManager;
import cn.suwg.springframework.jdbc.support.JdbcTemplate;
import cn.suwg.springframework.test.bean.JdbcService;
import cn.suwg.springframework.tx.transaction.annotation.AnnotationTransactionAttributeSource;
import cn.suwg.springframework.tx.transaction.interceptor.TransactionInterceptor;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: 测试类
 * @Author: suwg
 * @Date: 2024/10/10
 * 公众号： 趣研x
 */
public class ApiTest {
    private JdbcTemplate jdbcTemplate;

    private JdbcService jdbcService;

    private DataSource dataSource;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        dataSource = applicationContext.getBean(DataSource.class);
        jdbcService = applicationContext.getBean(JdbcService.class);
    }


    @Test
    public void testTransaction() throws SQLException {
        // 事务属性源
        AnnotationTransactionAttributeSource transactionAttributeSource = new AnnotationTransactionAttributeSource();
        // 获取事务属性
        transactionAttributeSource.findTransactionAttribute(jdbcService.getClass());

        // 事务管理器
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, transactionAttributeSource);

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(jdbcService));
        advisedSupport.setMethodInterceptor(interceptor);
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.suwg.springframework.test.bean.JdbcService.*(..))"));

        // 代理对象(Cglib2AopProxy)
        JdbcService proxy_cglib = (JdbcService) new Cglib2AopProxy(advisedSupport).getProxy();

        // 测试调用，有事务【不能同时提交2条有主键冲突的数据】
        proxy_cglib.saveData(jdbcTemplate);

        // 测试调用，无事务【提交2条有主键冲突的数据成功一条】
        //proxy_cglib.saveDataWithoutTx(jdbcTemplate);

    }

}
