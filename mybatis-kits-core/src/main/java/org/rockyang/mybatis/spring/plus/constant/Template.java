package org.rockyang.mybatis.spring.plus.constant;

/**
 *
 * 用于注入的语句模板
 *
 * @author chenzhaoju
 * Modified By : yangjian
 *
 */
public enum Template {
	/** sql 语句 模板 */
	ADD("add", "添加数据", "<script>INSERT INTO %s %s VALUES %s</script>"),
	UPDATE("update", "增量更新数据", "<script>UPDATE %s %s WHERE %s=#{%s}</script>"),
	UPDATE_ALL("updateAll", "全量更新数据", "<script>UPDATE %s %s WHERE %s=#{%s}</script>"),
	DELETE("delete", "删除一条数据", "<script>DELETE FROM %s WHERE %s=#{%s}</script>"),
	DELETE_BY_CONDITIONS("deleteByConditions", "批量删除数据", "<script>DELETE FROM %s %s</script>"),

	GET("get", "根据id查询", "<script>SELECT %s FROM %s WHERE %s=#{%s}</script>"),
	GET_BY_MAP("getByMap", "根据map 查询数据一条数据", "<script>SELECT %s FROM %s %s</script>"),
	GET_BY_CONDITIONS("getByConditions", "根据Conditions获取一个对象", "<script>SELECT %s FROM %s %s LIMIT 0,1</script>"),

	GET_COUNT("getCount", "获取总记录数", "<script>SELECT COUNT(1) FROM %s %s</script>"),
	GET_COUNT_BY_MAP("getCountByMap", "根据map获取总记录数", "<script>SELECT COUNT(1) FROM %s %s</script>"),
	GET_COUNT_BY_CONDITIONS("getCountByConditions", "根据条件获取总记录数", "<script>SELECT COUNT(1) FROM %s %s</script>"),
	SEARCH("search", "根据条件获取数据", "<script>SELECT %s FROM %s</script>"),
	SEARCH_BY_MAP("searchByMap", "根据map 查询数据", "<script>SELECT %s FROM %s %s %s</script>"),
	SEARCH_BY_CONDITIONS("searchByConditions", "根据Conditions 查询数据", "<script>SELECT %s FROM %s %s</script>");


	/** 方法名称 */
	private final String method;
	/** 简单描述 */
	private final String desc;
	/** sql 语句模板 */
	private final String sql;

	Template(String method, String desc, String sql) {
		this.method = method;
		this.desc = desc;
		this.sql = sql;
	}

	public String getMethod() {
		return this.method;
	}

	public String getDesc() {
		return this.desc;
	}

	public String getSql() {
		return this.sql;
	}
}
