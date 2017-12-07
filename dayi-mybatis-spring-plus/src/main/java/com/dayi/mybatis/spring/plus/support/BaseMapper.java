package com.dayi.mybatis.spring.plus.support;


import com.dayi.mybatis.spring.plus.plugins.page.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * Mapper 继承基类，自动提供部分接口功能
 *
 * @param <M> model
 *
 * @author chenzhaoju
 *
 */
public interface BaseMapper<M> {

    /**
     * 生成一个新的字符串id
     *
     * @return
     */
    public String getNewId() ;

    /**
     *
     * 添加一条记录
     *
     * @param model
     * @return int
     */
    public int add(M model);

    /**
     *
     * 根据 id 更新对应的model（更新非null 字段）
     *
     * @param model
     * @return int
     */
    public int update(M model);

    /**
     *
     * 根据 id 更新对应的model（更新所有字段，包括null的）
     * @param model
     * @return
     */
    public int updateAll(M model);

    /**
     *
     * 删除一条数据
     *
     * @param model
     * @return
     */
    public int delete(M model);

    /**
     *
     * 根据 id 查询
     *
     * @param id 主键
     * @return M
     */
    public M get(Serializable id);

    /**
     * 根据map 获取一条数据
     * @param map
     * @return
     */
    public M getByMap(Map<String, Object> map);

    /**
     *
     * 根据查询条件获取一个对象
     * @param conditions
     * @return
     */
    public M getByConditions(Conditions conditions);

    /**
     *
     * 取出全部数据
     *
     * @return
     */
    public List<M> search();

    /**
     * 分页搜索
     * @param page
     * @return
     */
    public Page<M> search(Page<M> page);

    /**
     * 根据map查询
     * @param map
     * @return
     */
    public List<M> searchByMap(Map<String, Object> map);

    /**
     * 分页搜索
     *
     * @param page
     * @param map
     * @return
     */
    public Page<M> searchByMap(Page<M> page, Map<String, Object> map);

    /**
     * 获取总记录数
     * @return
     */
    public long getCount();

    /**
     * 根据map条件查询总记录数
     * @param map
     * @return
     */
    public long getCount(Map<String, Object> map);

    /**
     *
     * 根据指定条件 查询对应的列表数据
     * @param conditions
     * @return
     */
    public List<M> searchByConditions(Conditions conditions);

    /**
     *
     * 根据指定条件 分页查询对应的列表数据
     *
     * @param conditions
     * @return
     */
    public Page<M> searchByConditions(Page<M> page,Conditions conditions);

}
