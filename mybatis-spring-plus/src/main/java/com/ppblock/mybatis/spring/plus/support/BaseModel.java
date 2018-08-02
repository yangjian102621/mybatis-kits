package com.ppblock.mybatis.spring.plus.support;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用数据模型，作为一般数据模型的基础类
 * @author yangjian
 */
public abstract class BaseModel<T> implements Serializable {

    //主键ID, 泛型，一般是字符串类型的或者整数形的
    protected T id;
    // 创建时间
    protected Date createTime;
    // 最后一次更新时间
    protected Date updateTime;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    protected BaseModel() {
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    protected BaseModel(T id) {
        this.id = id;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
