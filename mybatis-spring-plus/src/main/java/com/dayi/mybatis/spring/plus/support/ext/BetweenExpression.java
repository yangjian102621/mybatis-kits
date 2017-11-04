package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class BetweenExpression implements Criterion {

    private final String propertyName;
    private final Object lo;
    private final Object hi;

    protected BetweenExpression(String propertyName, Object lo, Object hi) {
        this.propertyName = propertyName;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append( this.propertyName);
        fragment.append( " between ? and ? " );
        return fragment.toString();
    }

    @Override
    public String toString() {
        return propertyName + " between " + lo + " and " + hi;
    }
}
