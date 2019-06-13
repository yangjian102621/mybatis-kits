package org.rockyang.mybatis.plus.support;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用数据模型
 * @author yangjian
 */
public abstract class BaseModel<T> implements Serializable {

    //主键ID
    public T id;
    // 创建时间
    public Date createTime;
    // 最后一次更新时间
    public Date updateTime;

    protected BaseModel() {
        this(null);
    }

    public BaseModel(T id) {
        this.id = id;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
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
