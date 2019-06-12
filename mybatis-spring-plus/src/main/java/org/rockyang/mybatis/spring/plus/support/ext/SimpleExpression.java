package org.rockyang.mybatis.spring.plus.support.ext;

import org.rockyang.mybatis.spring.plus.support.Criterion;

/**
 *
 * @author chenzhaoju
 */
public class SimpleExpression extends AbstractCriterion implements Criterion {

    private final String propertyName;
    private final Object value;
    private final String op;

    protected SimpleExpression(String propertyName, Object value, String op) {
        this.propertyName = propertyName;
        this.value = value;
        this.op = op;
        addParameterValue(propertyName,value);
    }

    protected final String getOp() {
        return op;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toSqlString(){
        StringBuilder fragment = new StringBuilder();
        fragment.append( this.propertyName);
        fragment.append( getOp() ).append( "#{" ).append(getPararmeterName(this.propertyName)).append("} ");
        return fragment.toString();
    }

    @Override
    public String toString() {
        return propertyName + getOp() + value;
    }
}
