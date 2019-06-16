package org.rockyang.mybatis.test.plus;

import org.rockyang.mybatis.test.support.model.Order;
import org.rockyang.mybatis.test.support.model.User;
import org.rockyang.mybatis.plus.mapper.Table;
import org.junit.Test;

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
