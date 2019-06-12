package org.rockyang.mybatis.spring.plus.support.ext;

import org.rockyang.mybatis.spring.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class BetweenExpression extends AbstractCriterion implements Criterion {

    private final String propertyName;
    private final Object lo;
    private final Object hi;

    protected BetweenExpression(String propertyName, Object lo, Object hi) {
        this.propertyName = propertyName;
        this.lo = lo;
        this.hi = hi;
        addParameterValue(propertyName+"_lo" ,this.lo);
        addParameterValue(propertyName+"_hi" ,this.hi);
    }

    @Override
    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append( this.propertyName);
        fragment.append( " between #{").append(getPararmeterName(propertyName+"_lo")).append("} ");
        fragment.append(" and #{").append(getPararmeterName(propertyName+"_hi")).append("} ");
        return fragment.toString();
    }

    @Override
    public String toString() {
        return propertyName + " between " + lo + " and " + hi;
    }
}
