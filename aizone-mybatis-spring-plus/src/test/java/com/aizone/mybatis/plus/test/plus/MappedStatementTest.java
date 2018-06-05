package com.aizone.mybatis.plus.test.plus;

import com.aizone.mybatis.plus.test.AbstractMybatisTest;
import com.aizone.mybatis.plus.test.support.mapper.UserMapper;
import com.aizone.mybatis.plus.test.support.model.User;
import com.aizone.mybatis.spring.plus.MybatisConfiguration;
import com.aizone.mybatis.spring.plus.MybatisSqlSessionFactoryBean;
import com.aizone.mybatis.spring.plus.support.Conditions;
import com.aizone.mybatis.spring.plus.support.ext.Restrictions;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * <p>
 * 全局配置测试
 * </p>
 *
 * @author chenzhaoju
 * @Date 2016-12-22
 */
public class MappedStatementTest extends AbstractMybatisTest {
	private Configuration configuration ;
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void buildSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean builder = new MybatisSqlSessionFactoryBean();

		Configuration configuration = new MybatisConfiguration();
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

		// spring 的步骤
		builder.setDataSource(dataSource);
		builder.setConfiguration(configuration);
		builder.afterPropertiesSet();
		this.configuration = configuration;
		sqlSessionFactory = builder.getObject();
	}

	private SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}

	@Test
	public void testGetMapperStatement() throws Exception {
		Collection<MappedStatement> mappedStatements = configuration.getMappedStatements();
		Collection<String> mappedStatementNames = configuration.getMappedStatementNames();
		MappedStatement mappedStatement = configuration.getMappedStatement("com.aizone.mybatis.plus.test.support.dao.mapper.UserMapper.selectUser");
		System.out.println(mappedStatement);
		BoundSql boundSql = mappedStatement.getBoundSql("34234234");
		String sql = boundSql.getSql();
		System.out.println(sql);
	}

	@Test
	public void testSearchByConditionsMapper() throws Exception {
		Conditions conditions = new Conditions();
		//conditions.add(Restrictions.eq("name", "名字"));
		conditions.add(Restrictions.or(Restrictions.like("name", "名字"),Restrictions.and(Restrictions.eq("count", 1),Restrictions.eq("count", 2))));

		MappedStatement mappedStatement = configuration.getMappedStatement("com.aizone.mybatis.plus.test.support.dao.mapper.UserMapper.searchByConditions");

		BoundSql boundSql = mappedStatement.getBoundSql(conditions);
		String sql = boundSql.getSql();
		System.out.println(sql);
	}
}
