package com.dayi.mybatis.spring.plus.support;

import java.io.Serializable;

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

}
