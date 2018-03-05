package com.aizone.mybatis.plus.test.support.mapper;


import com.aizone.mybatis.plus.test.support.model.User;
import com.aizone.mybatis.spring.plus.support.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

	public List<User> getUserByName(String name);
}
