package org.rockyang.mybatis.test.spring;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;
import org.rockyang.mybatis.plus.MybatisConfiguration;
import org.rockyang.mybatis.boot.demo.test.AbstractMybatis;
import org.rockyang.mybatis.test.support.mapper.UserMapper;
import org.rockyang.mybatis.test.support.model.User;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * mybatis-spring 原生 API 测试
 * @author chenzhaoju
 * @author yangjian
 */
public class MybatisSpringApiTest extends AbstractMybatis {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void buildSessionFactory(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        Configuration configuration = new MybatisConfiguration();

        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        configuration.setEnvironment(environment);

        configuration.setLazyLoadingEnabled(true);
        configuration.setCacheEnabled(false);// 该配置直接开启缓存 ,默认是开启的
        /**
         * 配置默认的执行器。SIMPLE 就是普通的执行器；
         * REUSE 执行器会重用预处理语句（prepared statements）；
         * BATCH 执行器将重用语句并执行批量更新;
         */
        //configuration.setDefaultExecutorType(ExecutorType.REUSE) ;
        configuration.setDefaultExecutorType(ExecutorType.SIMPLE) ;

        configuration.getTypeAliasRegistry().registerAlias(User.class);
        configuration.addMapper(UserMapper.class);

        sqlSessionFactory = builder.build(configuration);
    }

    private SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    private UserMapper getUserMapper() {
        SqlSession sqlSession = getSqlSession();
        assertNotNull("sqlSession 不能为空",sqlSession);
        return sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSessionFactory()
    {
        SqlSession sqlSession = getSqlSession();
        _Logger.info("sqlSession, {}", sqlSession);
    }

    @Test
    public void testUserMapper()
    {
        UserMapper userMapper = getUserMapper();
        assertNotNull("userMapper 不能为空",userMapper);
    }


    @Test
    public void testSearchUser()
    {
        UserMapper userMapper = getUserMapper();
        List<User> userList = userMapper.search();
        for (User user : userList) {
            _Logger.info("结果：{}",user);
        }
    }

    @Test
    public void testGetUser()
    {
        UserMapper userMapper = getUserMapper();
        User user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
    }

    @Test
    public void testGetUserMuti()
    {
        UserMapper userMapper = getUserMapper();
        User user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
        user = userMapper.get("0123456-02");
        _Logger.info("结果：{}",user);
        user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
    }
}
