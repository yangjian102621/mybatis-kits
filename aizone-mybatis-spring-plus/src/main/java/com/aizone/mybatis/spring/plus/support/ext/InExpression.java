package com.aizone.mybatis.spring.plus.support.ext;

import com.aizone.mybatis.spring.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class InExpression  extends AbstractCriterion  implements Criterion {

    private final String propertyName;
    private final Object[] values;

    protected InExpression(String propertyName, Object[] values) {
        this.propertyName = propertyName;
        this.values = values;
        for (int i = 0; i < this.values.length; i++) {
            Object value = this.values[i];
            addParameterValue(this.propertyName + "_" + i,value);
        }
    }

    @Override
    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append( this.propertyName);
        fragment.append( " in (" );
        for (int i = 0; i < this.values.length; i++) {
            if(0 < i){
                fragment.append(" , ");
            }
            fragment.append(" #{");
            fragment.append(getPararmeterName(this.propertyName + "_" + i));
            fragment.append(" }");
        }
        fragment.append(" ) ");
        return fragment.toString();
    }


    @Override
    public String toString() {
        return propertyName + " in (" + values + ')';
    }
}
