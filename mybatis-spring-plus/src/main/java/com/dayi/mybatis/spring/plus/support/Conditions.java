package com.dayi.mybatis.spring.plus.support;

import com.dayi.mybatis.spring.plus.support.ext.Restrictions;

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
    private Map<String,Object> parameterValues;

    public Conditions() {
        this.criterions = new ArrayList<>();
        this.parameterValues = new HashMap<>();
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public Conditions add(Criterion criterion) {
        this.criterions.add(criterion);
        this.parameterValues.putAll(criterion.getParameterValues());
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
}
