package com.aizone.mybatis.plus.test.support.mapper;


import com.aizone.mybatis.plus.test.support.model.User;
import com.aizone.mybatis.spring.plus.plugins.page.Page;
import com.aizone.mybatis.spring.plus.support.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

	public Map getUserMap();

	public List<User> getUserByName(String name);

	public Page<User> searchUsers(Page<User> page, @Param("name")String name, @Param("status")Integer status);

	public List<User> searchUsers(@Param("name")String name,@Param("status")Integer status);
}
