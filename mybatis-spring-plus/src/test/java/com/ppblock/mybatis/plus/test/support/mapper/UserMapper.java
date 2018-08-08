package com.ppblock.mybatis.plus.test.support.mapper;


import com.ppblock.mybatis.plus.test.support.model.User;
import com.ppblock.mybatis.spring.plus.plugins.page.Page;
import com.ppblock.mybatis.spring.plus.support.BaseMapper;
import com.ppblock.mybatis.spring.plus.support.MathOptVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * user mapper
 * @author yangjian
 */
public interface UserMapper extends BaseMapper<User> {

	public Map getUserMap();

	public List<User> getUserByName(String name);

	public Page<User> searchUsers(Page<User> page, @Param("name")String name, @Param("status")Integer status);

	public List<User> searchUsers(@Param("name")String name,@Param("status")Integer status);

	Integer mathOpt(MathOptVo vo);
}
