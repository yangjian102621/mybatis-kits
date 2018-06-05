package com.aizone.mybatis.spring.plus.support;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础model
 *
 * @author chenzhaoju
 * Modified By : yangjian
 */
public abstract class BaseModel<T> implements Serializable {
    /** id */
    private T id ;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    protected BaseModel() {}

    protected BaseModel(T id) {
        this.id = id;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public T getId() {
        return id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BaseModel setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BaseModel setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public BaseModel setId(T id) {
        this.id = id;
        return this;
    }
}
