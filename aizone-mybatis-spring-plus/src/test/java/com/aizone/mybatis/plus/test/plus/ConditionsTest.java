package com.aizone.mybatis.plus.test.plus;

import com.aizone.mybatis.spring.plus.support.Conditions;
import com.aizone.mybatis.spring.plus.support.ext.Restrictions;
import org.junit.Test;

/**
 * @author chenzhaoju
 */
public class ConditionsTest {

    @Test
    public void testSimpleExpression() throws Exception {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("count", 2));
        conditions.add(Restrictions.eq("name", "名字"));
        conditions.add(Restrictions.ge("age", 18));
        conditions.add(Restrictions.lt("age", 30));
        conditions.add(Restrictions.ge("num",8));
        conditions.add(Restrictions.le("num", 130));
        conditions.add(Restrictions.ne("address", "这个地址"));
        System.out.println(conditions.toSqlString());
    }

    @Test
    public void testBetweenExpression() throws Exception {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.eq("count", 2));
        conditions.add(Restrictions.between("age",20,30));
        System.out.println(conditions.toSqlString());
    }

    @Test
    public void testConjunction() throws Exception {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.and(Restrictions.eq("age",18),Restrictions.eq("name","李四")));
        conditions.add(Restrictions.or(Restrictions.eq("count",18),Restrictions.eq("count",29)));
        System.out.println(conditions.toSqlString());
    }

    @Test
    public void testOneConjunction() throws Exception {
        Conditions conditions = new Conditions();
        conditions.add(Restrictions.or(Restrictions.eq("count", 18), Restrictions.eq("count", 29)));
        System.out.println(conditions.toSqlString());
    }

}
