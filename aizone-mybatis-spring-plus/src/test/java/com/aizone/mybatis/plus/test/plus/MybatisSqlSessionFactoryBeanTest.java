package com.aizone.mybatis.plus.test.plus;

import com.aizone.mybatis.plus.test.AbstractMybatisTest;
import com.aizone.mybatis.plus.test.support.mapper.UserMapper;
import com.aizone.mybatis.plus.test.support.model.User;
import com.aizone.mybatis.spring.plus.MybatisConfiguration;
import com.aizone.mybatis.spring.plus.MybatisSqlSessionFactoryBean;
import com.aizone.mybatis.spring.plus.plugins.page.Page;
import com.aizone.mybatis.spring.plus.plugins.page.PaginationInterceptor;
import com.aizone.mybatis.spring.plus.support.Conditions;
import com.aizone.mybatis.spring.plus.support.Order;
import com.aizone.mybatis.spring.plus.support.ext.Restrictions;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * 全局配置测试
 * </p>
 *
 * @author yangjian
 * @Date 2016-12-22
 */
public class MybatisSqlSessionFactoryBeanTest extends AbstractMybatisTest {

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
		builder.setPlugins(new Interceptor[]{new PaginationInterceptor()});

		builder.setDataSource(dataSource);
		builder.setConfiguration(configuration);
		builder.afterPropertiesSet();
		sqlSessionFactory = builder.getObject();
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
		_Logger.info("结果：{}", user);
		user = userMapper.get("0123456-02");
		_Logger.info("结果：{}", user);
		user = userMapper.get("0123456-01");
		_Logger.info("结果：{}",user);
	}

	@Test
	public void testAdd() throws Exception {
		UserMapper userMapper = getUserMapper();

		User user = new User("0123456-11", "测试添加", 12, 1);

		userMapper.add(user);

		List<User> userList = userMapper.search();
		for (User u : userList) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testUpdate() throws Exception {
		UserMapper userMapper = getUserMapper();
		User user = userMapper.get("0123456-01");
		_Logger.info("结果：{}", user);
		user.setName("更新名称");
		userMapper.update(user);

		user = userMapper.get("0123456-01");
		_Logger.info("结果：{}", user);
	}

	@Test
	public void testSearchByMap() throws Exception {
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("name","two name");
		map.put("sex",2);
		map.put("__order", "id desc");

		List<User> users = userMapper.searchByMap(map);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchPage() throws Exception {
		UserMapper userMapper = getUserMapper();
		Page<User> page = new Page<User>();
		page.setPageSize(2);
		List<User> list = userMapper.search(page);

		for (User user : list) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testSearchByMapPage() throws Exception {
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "two name");
		map.put("sex",22);

		Page<User> page = new Page<>();
		List<User> list = userMapper.searchByMap(page, map);

		for (User user : list) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testSearchOrderBy() throws Exception {
		UserMapper userMapper = getUserMapper();
		List<User> userList = userMapper.searchOrderBy("id DESC");
		for (User user : userList) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testUserOrderBy() throws Exception {
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		conditions.add(Restrictions.gt("age", 15));
		conditions.addOrder(Order.desc("age"));
		conditions.addOrder(Order.asc("id"));
		conditions.addOrder(Order.asc("name"));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testUserPageOrderBy() throws Exception {
		Page<User> page = new Page<User>();
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		conditions.add(Restrictions.gt("age", 15));
		conditions.addOrder(Order.desc("age"));
		conditions.addOrder(Order.asc("id"));
		conditions.addOrder(Order.asc("name"));
		page = userMapper.searchByConditions(page,conditions);
		for (User u : page.getResults()) {
			_Logger.info("结果：{}",u);
		}
	}

	/**
	 * test delete items by conditions
	 */
	@Test
	public void testDeleteByConditions() {
		UserMapper userMapper = getUserMapper();
		Conditions conditions = new Conditions();
		Integer[] age = {221,222};
		conditions.add(Restrictions.in("age", age));
		//conditions.add(Restrictions.eq("name", "xxxx"));
		userMapper.deleteByConditions(conditions);

		List<User> list = userMapper.searchByConditions(conditions);
		for (User u : list) {
			_Logger.info("结果：{}",u);
		}

	}

	@Test
	public void testGetCount() {
		UserMapper userMapper = getUserMapper();
		long count = userMapper.getCount();
		System.out.println(count);

	}

	/**
	 * test get count by map
	 */
	@Test
	public void testGetCountByMap() {
		UserMapper userMapper = getUserMapper();
		HashMap<String, Object> map = new HashMap<>(16);
		map.put("sex", 1);
		long count = userMapper.getCountByMap(map);
		System.out.println(count);

	}

	/**
	 * test get count by conditions
	 */
	@Test
	public void testGetCountByConditions() {
		UserMapper userMapper = getUserMapper();
		Conditions conditions = new Conditions();
		Integer[] age = {13,50};
		conditions.add(Restrictions.in("age", age));
		long count = userMapper.getCountByConditions(conditions);
		System.out.println(count);

	}

	@Test
	public void testSelectByusername() {
		UserMapper userMapper = getUserMapper();
		userMapper.getUserByName("first name");
	}

	@Test
	public void testGetByCondition() {

		UserMapper userMapper = getUserMapper();
		Conditions conditions = new Conditions();
		conditions.addOrder(Order.desc("id"));
		User user = userMapper.getByConditions(conditions);
		System.out.println(user);
	}
}
