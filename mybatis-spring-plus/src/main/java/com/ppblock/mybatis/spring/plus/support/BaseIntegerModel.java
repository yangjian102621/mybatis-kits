package com.ppblock.mybatis.spring.plus.support;

import java.io.Serializable;
import java.util.Date;

/**
 * Integer 类型 id 的通用数据模型
 * @author yangjian
 * @since 18-8-11 上午11:21
 */
public abstract class BaseIntegerModel implements Serializable {

    //主键ID
    public Integer id;
    // 创建时间
    public Date createTime;
    // 最后一次更新时间
    public Date updateTime;

    protected BaseIntegerModel() {
        this(null);
    }

    public BaseIntegerModel(Integer id) {
        this.id = id;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
