package com.dayi.mybatis.spring.plus.support;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author chenzhaoju
 */
public abstract class BaseModel implements Serializable {
    /** id */
    private String id ;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    protected BaseModel() {}

    protected BaseModel(String id) {
        this.id = id;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public String getId() {
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

    public BaseModel setId(String id) {
        this.id = id;
        return this;
    }
}
