package org.rockyang.mybatis.shardingjdbc.demo.controller;

import org.rockyang.mybatis.shardingjdbc.demo.mapper.UserMapper;
import org.rockyang.mybatis.shardingjdbc.demo.model.User;
import org.rockyang.mybatis.plus.plugins.page.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangjian
 */
@RestController
public class HelloController {

	@Resource
	UserMapper userMapper;

	@GetMapping("/")
	public String index()
	{
		return "Hello Mybatis-Kits.";
	}

	@GetMapping("/newId")
	public Long newId() {
		return userMapper.getNewId();
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	@GetMapping("/users")
	public List<User> searchUsers()
	{
		Page<User> page = new Page<>();
		page.setPageSize(5);
		page = userMapper.search(page);
		return page.getResults();
	}

	@GetMapping("/users/byName")
	public List<User> getUserByName()
	{
		List<User> users = userMapper.getUserByName("测试添加");
		return users;
	}
}
