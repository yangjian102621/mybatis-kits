package com.dayi.mybatis.spring.plus.support;

import com.dayi.mybatis.spring.plus.support.ext.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 查询条件
 *
 * @author chenzhaoju
 */
public class Conditions implements Criterion,Serializable {

    private List<Criterion> criterions;

    public Conditions() {
        this.criterions = new ArrayList<>();
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public Conditions add(Criterion criterion) {
        this.criterions.add(criterion);
        return this;
    }

    @Override
    public String toSqlString() {
        StringBuilder sql = new StringBuilder();
        sql.append(Restrictions.and(this.criterions).toSqlString());
        return sql.toString();
    }

    public String getSql(){
        return toSqlString();
    }
}
