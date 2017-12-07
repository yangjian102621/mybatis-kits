package com.dayi.mybatis.autoconfigure;

import com.dayi.mybatis.autoconfigure.mapper.UserMapper;
import com.dayi.mybatis.autoconfigure.model.User;
import com.dayi.mybatis.spring.plus.plugins.page.Page;
import com.dayi.mybatis.spring.plus.support.Conditions;
import com.dayi.mybatis.spring.plus.support.ext.MatchMode;
import com.dayi.mybatis.spring.plus.support.ext.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**s
 * <p>
 * 全局配置测试
 * </p>
 *
 * @author  chenzhaoju
 * @Date 2016-12-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMapperTest {
	public static final Logger _Logger = LoggerFactory.getLogger(MybatisMapperTest.class);

	@Resource
	private UserMapper userMapper;


	private UserMapper getUserMapper() {
		return userMapper;
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
	public void testGetByMap() throws Exception {
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id","0123456-01");

		User user = userMapper.getByMap(map);
		_Logger.info("结果：{}",user);
	}

	@Test
	public void testGetByConditions() throws Exception {
		UserMapper userMapper = getUserMapper();
		Conditions conditions = new Conditions();
		conditions.add(Restrictions.eq("id", "0123456-02"));
		User user = userMapper.getByConditions(conditions);
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

		User user = new User("0123456-12", "测试添加2", 12, 1);
		userMapper.add(user);

		List<User> userList = userMapper.search();
		for (User u : userList) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testUpdate() throws Exception {
		UserMapper userMapper = getUserMapper();
		User user = userMapper.get("0123456-10");
		_Logger.info("结果：{}", user);
		user.setName("更新名称"+System.currentTimeMillis());
		user.setAge((int) (Math.random()*100));
		userMapper.update(user);
		user = userMapper.get("0123456-10");
		_Logger.info("结果：{}", user);
	}

	@Test
	public void testSearchByMap() throws Exception {
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
	public void testSearchPage() throws Exception {
		UserMapper userMapper = getUserMapper();
		Page<User> page = new Page<User>();
		page.setPageSize(5);
		page = userMapper.search(page);

		_Logger.info("分页结果：{}",page);
		for (User user : page) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testSearchByMapPage() throws Exception {
		UserMapper userMapper = getUserMapper();

		HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("name","two name");
		 map.put("sex",2);

		Page<User> page = new Page<User>();
		page = userMapper.searchByMap(page, map);
		_Logger.info("分页结果：{}",page);
		for (User user : page) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testSearchByConditions() throws Exception {
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		//conditions.add(Restrictions.eq("name", "two name"));
		conditions.add(Restrictions.eq("sex", 2));
		//conditions.add(Restrictions.gt("age", 15));
		//conditions.add(Restrictions.lt("age", 25));
		conditions.add(Restrictions.and(Restrictions.gt("age", 15),Restrictions.lt("age", 20)));
		conditions.add(Restrictions.or(Restrictions.eq("name", "two name"),Restrictions.eq("name", "four name")));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchByConditionsIn() throws Exception {
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		conditions.add(Restrictions.eq("sex", 2));
		conditions.add(Restrictions.in("age", Arrays.asList(15,25,17,19)));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchByConditionsBetween() throws Exception {
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		conditions.add(Restrictions.eq("sex", 2));
		conditions.add(Restrictions.between("age", 15, 25));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchByConditionsLike() throws Exception {
		UserMapper userMapper = getUserMapper();

		Conditions conditions = new Conditions();
		conditions.add(Restrictions.eq("sex", 2));
		conditions.add(Restrictions.like("name", "name", MatchMode.END));
		List<User> users = userMapper.searchByConditions(conditions);
		for (User u : users) {
			_Logger.info("结果：{}",u);
		}
	}

	@Test
	public void testSearchByConditionsPage() throws Exception {
		UserMapper userMapper = getUserMapper();

		Page<User> page = new Page<User>();

		Conditions conditions = new Conditions();
		// conditions.add(Restrictions.eq("name", "two name"));
		conditions.add(Restrictions.eq("sex", 2));
		conditions.add(Restrictions.gt("age", 19));
		page = userMapper.searchByConditions(page,conditions);
		_Logger.info("分页结果：{}",page);
		for (User user : page) {
			_Logger.info("结果：{}",user);
		}
	}

	@Test
	public void testDeleteUser() throws Exception {
		UserMapper userMapper = getUserMapper();
		User user = userMapper.get("0123456-12");
		_Logger.info("结果：{}",user);
		userMapper.delete(user);
		user = userMapper.get("0123456-12");
		_Logger.info("结果：{}",user);
	}

	@Test
	public void testGetNewId() throws Exception {
		for (int i = 0; i < 1000; i++) {
			UserMapper userMapper = getUserMapper();
			String id = userMapper.getNewId();
			_Logger.info("结果：{},{}",userMapper,id);
		}
	}
}
