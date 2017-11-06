package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class InExpression  extends AbstractCriterion  implements Criterion {

    private final String propertyName;
    private final Object[] values;

    protected InExpression(String propertyName, Object[] values) {
        this.propertyName = propertyName;
        this.values = values;
    }

    @Override
    public String toSqlString() {
        return "" ;
    }


    @Override
    public String toString() {
        return propertyName + " in (" + values + ')';
    }
}
