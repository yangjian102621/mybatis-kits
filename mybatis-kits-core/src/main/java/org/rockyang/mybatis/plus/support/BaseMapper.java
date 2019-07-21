package org.rockyang.mybatis.plus.support;


import org.rockyang.mybatis.plus.plugins.page.Page;

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
     * @return 返回一个唯一 ID
     */
    String getNewId();

    /**
     *
     * 添加一条记录
     *
     * @param model 数据模型
     * @return int 返回影响记录条数
     */
    int add(M model);

    /**
     *
     * 根据 id 更新对应的model（更新非null 字段）
     *
     * @param model 要更新的数据模型
     * @return 返回影响记录条数
     */
    int update(M model);

    /**
     *
     * 根据 id 更新对应的 model（更新所有字段，包括null的）
     * @param model 要更新的数据模型
     * @return 返回影响记录条数
     */
    int updateAll(M model);

    /**
     *
     * 根据模型删除一条数据
     *
     * @param model 数据模型
     * @return 返回影响记录条数
     */
    int delete(M model);

    /**
     *
     * 根据 id 删除一条数据
     *
     * @param id 记录ID
     * @return 返回影响记录条数
     */
    int delete(Serializable id);

    /**
     * 根据条件批量删除
     * @param conditions 查询条件
     * @return 返回影响记录条数
     */
    int deleteByConditions(Conditions conditions);

    /**
     *
     * 根据 id 查询
     *
     * @param id ID主键
     * @return 返回查询到的记录
     */
    M get(Serializable id);

    /**
     * 根据map 获取一条数据
     * @param map 查询条件 Map
     * @return 返回查询到的记录
     */
    M getByMap(Map<String, Object> map);

    /**
     *
     * 根据查询条件获取一个对象
     * @param conditions 查询条件
     * @return 返回查询到的数据记录
     */
    M getByConditions(Conditions conditions);

    /**
     *
     * 取出全部数据
     * @return 返回所有数据
     */
    List<M> search();

    /**
     * 分页搜索
     * @param page 分页对象
     * @return 返回填充数据后的分页对象
     */
    Page<M> search(Page<M> page);

    /**
     * 根据map查询
     * @param map 查询条件 Map
     * @return 返回数据列表
     */
    List<M> searchByMap(Map<String, Object> map);

    /**
     * 分页搜索
     *
     * @param page 查询分页对象
     * @param map 查询条件 Map
     * @return 返回填充数据后的 Page 对象
     */
    Page<M> searchByMap(Page<M> page, Map<String, Object> map);

    /**
     * 获取总记录数
     * @return 返回记录总条数
     */
    long getCount();

    /**
     * 根据map条件查询总记录数
     * @param map 查询 Map
     * @return 返回符合条件的记录总数
     */
    long getCountByMap(Map<String, Object> map);

    /**
     * 根据 Conditions 条件查询总记录数
     * @param conditions 查询条件
     * @return 返回符合条件的记录总数
     */
    long getCountByConditions(Conditions conditions);

    /**
     *
     * 根据指定条件 查询对应的列表数据
     * @param conditions 查询条件
     * @return 返回符合条件的数据列表
     */
        List<M> searchByConditions(Conditions conditions);

    /**
     *
     * 根据指定条件 分页查询对应的列表数据
     * @param page 分页对象
     * @param conditions 查询条件
     * @return 返回填充数据后的 Page 对象
     */
    Page<M> searchByConditions(Page<M> page,Conditions conditions);

}
