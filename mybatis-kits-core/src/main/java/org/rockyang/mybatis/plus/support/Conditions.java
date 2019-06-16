package org.rockyang.mybatis.plus.support;

import org.rockyang.mybatis.plus.support.ext.*;

import java.io.Serializable;
import java.util.*;

/**
 *
 * 查询条件
 *
 * @author chenzhaoju
 * @author yangjian
 */
public class Conditions implements Criterion,Serializable {

    private List<Criterion> criterions;
    private List<Order> orders;
    private Map<String,Object> parameterValues;

    public Conditions() {
        this.criterions = new ArrayList<>();
        this.orders = new ArrayList<>(2);
        this.parameterValues = new HashMap<>();
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public Conditions add(Criterion criterion) {
        if(null != criterion){
            this.criterions.add(criterion);
            this.parameterValues.putAll(criterion.getParameterValues());
        }
        return this;
    }

    public Conditions addOrder(Order order){
        if(null != order){
            this.orders.add(order);
        }
        return this;
    }

    @Override
    public String toSqlString() {
        StringBuilder sql = new StringBuilder();
        sql.append(Restrictions.and(this.criterions).toSqlString());
        return sql.toString();
    }

    @Override
    public Map<String, Object> getParameterValues() {
        return parameterValues;
    }

    public Object getParameterValue(String parameterName){
        return this.parameterValues.get(parameterName);
    }

    public String getSql(){
        return toSqlString();
    }

    /**
     * 获取排序 SQL
     * @return 返回 ORDER BY 字符串
     */
    public String getOrderBySql(){
        if(0 == this.orders.size()){
            return "";
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" ORDER BY ");
        int length = sql.length();
        for (Order order : this.orders) {
            if(length < sql.length()){
                sql.append(" , ");
            }
            sql.append(order.toSqlString());
        }
        return sql.toString();
    }

    /* 以下是便捷条件操作 */

    public Conditions eq(String propertyName, Object value)
    {
        return add(Restrictions.eq(propertyName, value));
    }

    public Conditions ne(String propertyName, Object value)
    {
        return add(Restrictions.ne(propertyName, value));
    }

    public Conditions like(String propertyName, String value)
    {
        return add(Restrictions.like(propertyName, value));
    }

    public Conditions like(String propertyName, String value, MatchMode matchMode)
    {
        return add(Restrictions.like(propertyName, value, matchMode));
    }

    public Conditions gt(String propertyName, Object value)
    {
        return add(Restrictions.gt(propertyName, value));
    }


    public Conditions lt(String propertyName, Object value)
    {
        return add(Restrictions.lt(propertyName, value));
    }


    public Conditions le(String propertyName, Object value)
    {
        return add(Restrictions.le(propertyName, value));
    }

    public Conditions ge(String propertyName, Object value)
    {
        return add(Restrictions.ge(propertyName, value));
    }


    public Conditions between(String propertyName, Object lo, Object hi)
    {
        return add(Restrictions.between(propertyName, lo, hi));
    }


    public Conditions in(String propertyName, Object[] values)
    {
        return add(Restrictions.in(propertyName, values));
    }


    public Conditions in(String propertyName, Collection values)
    {
        return add(Restrictions.in(propertyName, values));
    }

    public Conditions isNull(String propertyName)
    {
        return add(Restrictions.isNull(propertyName));
    }


    public Conditions isNotNull(String propertyName)
    {
        return add(Restrictions.isNotNull(propertyName));
    }

    public Conditions sqlRestriction(String sql, Object value) {
        return add(Restrictions.sqlRestriction(sql, value));
    }

    public Conditions conjunction() {
        return add(Restrictions.conjunction());
    }

    public Conditions disjunction() {
        return add(Restrictions.disjunction());
    }
}
