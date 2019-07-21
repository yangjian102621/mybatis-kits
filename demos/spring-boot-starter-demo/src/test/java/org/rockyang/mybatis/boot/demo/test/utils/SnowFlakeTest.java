package org.rockyang.mybatis.boot.demo.test.utils;

import org.junit.Assert;
import org.junit.Test;
import org.rockyang.mybatis.plus.util.Snowflake;

import java.util.HashSet;

/**
 * @author yangjian
 */
public class SnowFlakeTest {

	@Test
	public void snowflakeTest1(){
		Snowflake idWorker = new Snowflake(1, 2);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			System.out.println(id);
		}
	}

	@Test
	public void snowflakeTest2() {
		HashSet<Long> hashSet = new HashSet<>();

		Snowflake idWorker = Snowflake.getInstance();
		for (int i = 0; i < 1000; i++) {
			Long id = idWorker.nextId();
			System.out.println(idWorker.nextIdStr());
			hashSet.add(id);
		}
		Assert.assertEquals(1000L, hashSet.size());
	}

	@Test
	public void snowflakeTest3(){
		Snowflake idWorker = new Snowflake(1, 2);
		for (int i = 0; i < 1000; i++) {
			System.out.println(idWorker.nextIdHex());
		}
	}
}
