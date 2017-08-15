package com.dayi.mybatis.spring.plus.mapper;


import com.dayi.mybatis.spring.plus.util.Misc;

import java.lang.reflect.Field;

/**
 * model 对应的 字段
 * @author chenzhaoju
 */
public class TableField {

    /** 属性名称 */
    private String fieldName ;
    /** 字段名称 */
    private String columnName;
    /** 是否为 id */
    private boolean isId ;
    /** 属性的java 类型 */
    private Class<?> javaType;

    public TableField(Field field) {
        this.fieldName = field.getName();
        this.columnName = Misc.toCamelUnderline(fieldName);
        this.javaType = field.getType();
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public boolean isId() {
        return isId;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setIsId() {
        this.isId = true;
    }
}
