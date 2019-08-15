package org.rockyang.mybatis.springboot.demo.test.plus;

import org.junit.Test;
import org.rockyang.mybatis.springboot.demo.test.support.model.Order;
import org.rockyang.mybatis.springboot.demo.test.support.model.User;
import org.rockyang.mybatis.plus.mapper.Table;

/**
 *
 * @author chenzhaoju
 * @author yangjian
 */
public class TableTest {

    @Test
    public void testTable()
    {

        Table tableUser = Table.valueOf(User.class, User.class.toString());
        System.out.println(tableUser);
        Table tableOrder = Table.valueOf(Order.class, Order.class.toString());
        System.out.println(tableOrder);
    }

    @Test
    public void testColumnSql()
    {
        Table tableUser = Table.valueOf(User.class, User.class.toString());
        Table tableOrder = Table.valueOf(Order.class, Order.class.toString());

        System.out.println(tableUser.getColumnsSqlAs());
        System.out.println(tableOrder.getColumnsSqlAs());

    }
}
