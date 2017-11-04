package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class NullExpression implements Criterion {

    private final String propertyName;

    protected NullExpression(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append( this.propertyName);
        fragment.append(" is null ");
        return fragment.toString();
    }

    @Override
    public String toString() {
        return propertyName + " is null";
    }
}
