package com.aizone.mybatis.plus.test.spring;

import com.aizone.mybatis.plus.test.AbstractMybatisTest;
import com.aizone.mybatis.plus.test.support.mapper.UserMapper;
import com.aizone.mybatis.plus.test.support.model.User;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author yangjian
 */
public class MybatisApiTest extends AbstractMybatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void buildSessionFactory(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        Configuration configuration = new Configuration();

        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        configuration.setEnvironment(environment);

        configuration.setLazyLoadingEnabled(true);
        configuration.setCacheEnabled(false);// 该配置直接开启缓存 ,默认是开启的
        configuration.setDefaultExecutorType(ExecutorType.SIMPLE) ; // 配置默认的执行器。SIMPLE 就是普通的执行器；REUSE 执行器会重用预处理语句（prepared statements）； BATCH 执行器将重用语句并执行批量更新;
        // configuration.setDefaultExecutorType(ExecutorType.REUSE) ; // 使用该配置语句会有重用

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
    public void testSessionFactory() throws Exception {
        SqlSession sqlSession = getSqlSession();
    }

    @Test
    public void testUserMapper() throws Exception {
        UserMapper userMapper = getUserMapper();
        assertNotNull("userMapper 不能为空",userMapper);
    }


    @Test
    public void testSearchUser() throws Exception {
        UserMapper userMapper = getUserMapper();
        List<User> userList = userMapper.search();
        for (User user : userList) {
            _Logger.info("结果：{}",user);
        }
    }

    @Test
    public void testGetUser() throws Exception {
        UserMapper userMapper = getUserMapper();
        User user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
    }

    @Test
    public void testGetUserMuti() throws Exception {
        UserMapper userMapper = getUserMapper();
        User user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
        user = userMapper.get("0123456-02");
        _Logger.info("结果：{}",user);
        user = userMapper.get("0123456-01");
        _Logger.info("结果：{}",user);
    }
}
