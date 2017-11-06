package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzhaoju
 */
public abstract class AbstractCriterion implements Criterion {

    private Map<String,Object> parameterValues = new HashMap<>();

    @Override
    public Map<String, Object> getParameterValues() {
        return this.parameterValues;
    }

    public AbstractCriterion addParameterValue(Criterion criterion, String parameterName, Object value){
        this.parameterValues.put(getPararmeterName(criterion,parameterName), value);
        return this;
    }

    public String getPararmeterName(Criterion criterion,String parameterName){
        return criterion.getClass().getSimpleName() + "_" + parameterName;
    }
}
