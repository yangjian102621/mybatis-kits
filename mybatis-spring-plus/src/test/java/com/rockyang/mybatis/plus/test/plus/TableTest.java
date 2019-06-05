package com.rockyang.mybatis.plus.test.plus;

import com.rockyang.mybatis.plus.test.support.model.Order;
import com.rockyang.mybatis.plus.test.support.model.User;
import com.rockyang.mybatis.spring.plus.mapper.Table;
import org.junit.Test;

/**
 *
 * @author chenzhaoju
 */
public class TableTest {

    @Test
    public void testTable() throws Exception {

        Table tableUser = Table.valueOf(User.class, "com.fiidee.mybatis.support.model.User");
        System.out.println(tableUser);
        Table tableOrder = Table.valueOf(Order.class, "com.fiidee.mybatis.support.model.Order");
        System.out.println(tableOrder);
    }

    @Test
    public void testColumnSql() throws Exception {
        Table tableUser = Table.valueOf(User.class, "com.fiidee.mybatis.support.model.User");
        Table tableOrder = Table.valueOf(Order.class, "com.fiidee.mybatis.support.model.Order");

        System.out.println(tableUser.getColumnsSqlAs());
        System.out.println(tableOrder.getColumnsSqlAs());

    }
}
