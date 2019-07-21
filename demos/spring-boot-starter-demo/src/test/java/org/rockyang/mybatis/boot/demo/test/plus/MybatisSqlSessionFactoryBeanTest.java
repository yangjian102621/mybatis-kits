package org.rockyang.mybatis.boot.demo.test.plus;

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
import org.rockyang.mybatis.boot.demo.test.AbstractMybatis;
import org.rockyang.mybatis.boot.demo.test.support.mapper.OrderMapper;
import org.rockyang.mybatis.boot.demo.test.support.mapper.UserMapper;
import org.rockyang.mybatis.boot.demo.test.support.model.Order;
import org.rockyang.mybatis.boot.demo.test.support.model.User;
import org.rockyang.mybatis.plus.MybatisConfiguration;
import org.rockyang.mybatis.plus.plugins.page.Page;
import org.rockyang.mybatis.plus.plugins.page.PaginationInterceptor;
import org.rockyang.mybatis.plus.support.Conditions;
import org.rockyang.mybatis.plus.support.MathOptVo;
import org.rockyang.mybatis.plus.support.ext.Restrictions;
import org.rockyang.mybatis.plus.util.IdUtil;
import org.rockyang.mybatis.spring.MybatisSqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * 全局配置测试
 * </p>
 *
 * @author chenzhaoju
 * @author yangjian
 */
@ContextConfiguration(locations = "classpath:application.properties")
public class MybatisSqlSessionFactoryBeanTest extends AbstractMybatis {

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
		/**
		 * 该配置直接开启缓存 ,默认是开启的
		 * 特别注意，这里是 Mybatis 的一个 Bug，如果设置为 false 会导致插件注入失效
		 * 直接的结果就是分页插件失效，物理分页不可用
		 */
		configuration.setCacheEnabled(true);
		/**
		 * 配置默认的执行器。SIMPLE 就是普通的执行器；
		 * REUSE 执行器会重用预处理语句（prepared statements）；
		 * BATCH 执行器将重用语句并执行批量更新;
		 */
		//configuration.setDefaultExecutorType(ExecutorType.REUSE) ;
		configuration.setDefaultExecutorType(ExecutorType.SIMPLE) ;

		configuration.getTypeAliasRegistry().registerAlias(User.class);
		configuration.getTypeAliasRegistry().registerAlias(Order.class);
		configuration.addMapper(UserMapper.class);
		configuration.addMapper(OrderMapper.class);

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
	public void testSessionFactory()
	{
		SqlSession sqlSession = getSqlSession();
		_Logger.info("sqlSession, {}", sqlSession);
	}

	@Test
	public void testSearchUser()
	{
		UserMapper userMapper = getUserMapper();
		List<User> userList = userMapper.search();
		for (User user : userList) {
			System.out.println(user);
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
		_Logger.info("结果：{}", user);
		user = userMapper.get("0123456-02");
		_Logger.info("结果：{}", user);
		user = userMapper.get("0123456-01");
		_Logger.info("结果：{}",user);
	}

	@Test
	public void testAdd()
	{
		UserMapper userMapper = getUserMapper();

		User user = new User(IdUtil.getInstance().getNewId(), "测试添加", 12, 1);
		user.setStatus(1);
		int add = userMapper.add(user);
		System.out.println(user);
		System.out.println(add);

		List<User> userList = userMapper.search();
		for (User u : userList) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testUpdate()
	{
		UserMapper userMapper = getUserMapper();
		String userId = "016526f9a2d50001a0";
		User user = userMapper.get(userId);
		_Logger.info("结果：{}", user);
		user.setName("更新名称");
		user.setUpdateTime(new Date());
		userMapper.update(user);

		user = userMapper.get(userId);
		_Logger.info("结果：{}", user);
	}

	@Test
	public void testSearchByMap()
	{
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name","two name");
		map.put("sex",2);

		List<User> users = userMapper.searchByMap(map);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchPage()
	{
		UserMapper userMapper = getUserMapper();
		Page<User> page = new Page<>();
		page.setPageSize(5);
		userMapper.search(page);

		for (User user : page.getResults()) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testSearchByMapPage()
	{
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "two name");
		map.put("sex",2);

		Page<User> page = new Page<User>();
		page = userMapper.searchByMap(page, map);

		for (User user : page.getResults()) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void updateBalance() {

		UserMapper userMapper = getUserMapper();
		String id = "0123456-02";
		// 给用户的余额随机增加 0-10 元
		MathOptVo<String, BigDecimal> balance = new MathOptVo<>(id, "balance", BigDecimal.valueOf(Math.random() * 10
				+ 1), MathOptVo.ADD);
		Integer integer = userMapper.mathOpt(balance);
		System.out.println(integer);

	}

	/**
	 * 返回自增 ID 测试
	 */
	@Test
	public void keyGeneratorTest() {
		SqlSession sqlSession = getSqlSession();
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		Order order = new Order(UUID.randomUUID().toString());
		mapper.add(order);
		System.out.println(order);
	}

	@Test
	public void testGetAmountSum() {
		UserMapper userMapper = getUserMapper();
		System.out.println(userMapper.getAmountSum(new Date()));
	}

	@Test
	public void testSqlRestriction() {
		UserMapper userMapper = getUserMapper();
		Conditions conditions = new Conditions();
		conditions.add(Restrictions.conjunction());
		conditions.add(Restrictions.eq("name","rock"));
		//conditions.add(Restrictions.sqlRestriction("id >= ", 100));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User user : users) {
			_Logger.info("结果：{}",user);
		}
	}

}
