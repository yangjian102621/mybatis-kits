package org.rockyang.mybatis.plus.support.ext;

import org.rockyang.mybatis.plus.support.Criterion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhaoju
 * @author yangjian
 */
public abstract class AbstractCriterion implements Criterion {

    protected Map<String,Object> parameterValues = new HashMap<>();

    @Override
    public Map<String, Object> getParameterValues() {
        return this.parameterValues;
    }

    public AbstractCriterion addParameterValue(String parameterName, Object value){
        this.parameterValues.put(getPararmeterName(parameterName), value);
        return this;
    }

    public String getPararmeterName(String parameterName){
        return this.getClass().getSimpleName() + "_" + parameterName + "_" + hashCode();
    }
}
