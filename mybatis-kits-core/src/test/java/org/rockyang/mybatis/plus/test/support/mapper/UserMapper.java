package org.rockyang.mybatis.plus.test.support.mapper;


import org.rockyang.mybatis.plus.test.support.model.User;
import org.rockyang.mybatis.plus.plugins.page.Page;
import org.rockyang.mybatis.plus.support.BaseMapper;
import org.rockyang.mybatis.plus.support.MathOptVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * user mapper
 * @author yangjian
 */
public interface UserMapper extends BaseMapper<User> {

	Map getUserMap();

	List<User> getUserByName(String name);

	Page<User> searchUsers(Page<User> page, @Param("name")String name, @Param("status")Integer status);

	List<User> searchUsers(@Param("name")String name,@Param("status")Integer status);

	Integer mathOpt(MathOptVo vo);

	BigDecimal getAmountSum(Date date);
}
