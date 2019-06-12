package org.rockyang.mybatis.spring.plus.support;

import org.rockyang.mybatis.spring.plus.support.ext.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 查询条件
 *
 * @author chenzhaoju
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
}
