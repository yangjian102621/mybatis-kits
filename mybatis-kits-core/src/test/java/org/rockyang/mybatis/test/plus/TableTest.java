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

        Table tableUser = Table.valueOf(User.class, "com.fiidee.mybatis.support.model.User");
        System.out.println(tableUser);
        Table tableOrder = Table.valueOf(Order.class, "com.fiidee.mybatis.support.model.Order");
        System.out.println(tableOrder);
    }

    @Test
    public void testColumnSql()
    {
        Table tableUser = Table.valueOf(User.class, "com.fiidee.mybatis.support.model.User");
        Table tableOrder = Table.valueOf(Order.class, "com.fiidee.mybatis.support.model.Order");

        System.out.println(tableUser.getColumnsSqlAs());
        System.out.println(tableOrder.getColumnsSqlAs());

    }
}
