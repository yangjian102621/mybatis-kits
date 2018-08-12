package com.ppblock.mybatis.spring.plus.support;


import com.ppblock.mybatis.spring.plus.plugins.page.Page;

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
 * Modified By : yangjian
 *
 */
public interface BaseMapper<M> {

    /**
     * 生成一个新的字符串id
     *
     * @return
     */
    String getNewId() ;

    /**
     *
     * 添加一条记录
     *
     * @param model
     * @return int
     */
    int add(M model);

    /**
     *
     * 根据 id 更新对应的model（更新非null 字段）
     *
     * @param model
     * @return int
     */
    int update(M model);

    /**
     *
     * 根据 id 更新对应的model（更新所有字段，包括null的）
     * @param model
     * @return
     */
    int updateAll(M model);

    /**
     *
     * 删除一条数据
     *
     * @param model
     * @return
     */
    int delete(M model);

    /**
     *
     * 根据 id 删除一条数据
     *
     * @param id
     * @return
     */
    int delete(Serializable id);

    /**
     * 根据条件批量删除
     * @param conditions
     * @return
     */
    int deleteByConditions(Conditions conditions);

    /**
     *
     * 根据 id 查询
     *
     * @param id 主键
     * @return M
     */
    M get(Serializable id);

    /**
     * 根据map 获取一条数据
     * @param map
     * @return
     */
    M getByMap(Map<String, Object> map);

    /**
     *
     * 根据查询条件获取一个对象
     * @param conditions
     * @return
     */
    M getByConditions(Conditions conditions);

    /**
     *
     * 取出全部数据
     * @return
     */
    List<M> search();

    /**
     * 分页搜索
     * @param page
     * @return
     */
    Page<M> search(Page<M> page);

    /**
     * 根据map查询
     * @param map
     * @return
     */
    List<M> searchByMap(Map<String, Object> map);

    /**
     * 分页搜索
     *
     * @param page
     * @param map
     * @return
     */
    Page<M> searchByMap(Page<M> page, Map<String, Object> map);

    /**
     * 获取总记录数
     * @return
     */
    long getCount();

    /**
     * 根据map条件查询总记录数
     * @param map
     * @return
     */
    long getCountByMap(Map<String, Object> map);

    /**
     * 根据 Conditions 条件查询总记录数
     * @param conditions
     * @return
     */
    long getCountByConditions(Conditions conditions);

    /**
     *
     * 根据指定条件 查询对应的列表数据
     * @param conditions
     * @return
     */
    List<M> searchByConditions(Conditions conditions);

    /**
     *
     * 根据指定条件 分页查询对应的列表数据
     * @param page
     * @param conditions
     * @return
     */
    Page<M> searchByConditions(Page<M> page,Conditions conditions);

}
