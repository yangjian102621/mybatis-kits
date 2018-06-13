package com.ppblock.mybatis.spring.plus.mapper;


import com.ppblock.mybatis.spring.plus.util.Misc;

import javax.persistence.Column;
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
        //如果有添加了注解，则使用注解覆盖
        Column column = field.getAnnotation(Column.class);
        if (null != column) {
            this.columnName = column.name();
        }
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
