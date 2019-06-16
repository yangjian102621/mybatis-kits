package org.rockyang.mybatis.plus.support;

import java.io.Serializable;
import java.util.Map;

/**
 * @author chenzhaoju
 * @author yangjian
 */
public interface Criterion extends Serializable {

    /**
     *
     * 获取sql 片段
     *
     * @return 返回 SQL 字符串片段
     *
     */
    String toSqlString();

    /**
     * 获取 参数列表
     * @return 返回参数列表
     */
    Map<String,Object> getParameterValues();

}
