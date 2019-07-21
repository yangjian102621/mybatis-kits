package org.rockyang.mybatis.boot.demo.test.utils;

import org.junit.Test;
import org.rockyang.mybatis.plus.util.IdUtil;

/**
 * IdUtil 测试
 * @author yangjian
 */
public class IdUtilTest {

	@Test
	public void getNewId()
	{
		String id  = IdUtil.getInstance().getNewId();
		System.out.println(id);

		String idWithPrefix = IdUtil.getInstance().getNewId("mybatis");
		System.out.println(idWithPrefix);
	}
}
