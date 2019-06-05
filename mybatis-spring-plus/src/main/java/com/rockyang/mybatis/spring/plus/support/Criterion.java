package com.rockyang.mybatis.spring.plus.support;

import java.io.Serializable;
import java.util.Map;

/**
 * @author chenzhaoju
 */
public interface Criterion extends Serializable {

    /**
     *
     * 获取sql 片段
     *
     * @return
     *
     */
    public String toSqlString();

    /**
     * 获取 参数列表
     * @return
     */
    public Map<String,Object> getParameterValues();

}
