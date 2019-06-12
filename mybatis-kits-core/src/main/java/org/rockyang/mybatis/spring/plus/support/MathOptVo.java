package org.rockyang.mybatis.spring.plus.support;

/**
 * 用于在 mapper 中对某个字段进行数学运算： 加减乘除
 * 作为 parameterType 参数传入 mapper 方法
 * @author yangjian
 * @since 2018-08-08 下午6:25.
 */
public class MathOptVo<K, V> {

	// 主键
	protected K id;
	// 字段名称
	protected String field;
	// 字段值的增量
	protected V offset;
	// 操作类型（可选：加,减,乘,数）
	protected String opt;

	public MathOptVo(K id, String field, V offset, String opt) {
		this.id = id;
		this.field = field;
		this.offset = offset;
		this.opt = opt;
	}

	public static final String ADD = "add";
	public static final String SUBTRACE = "subtract";
	public static final String MULTIPLY = "multiply";
	public static final String DIVIDE = "divide";

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public V getOffset() {
		return offset;
	}

	public void setOffset(V offset) {
		this.offset = offset;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}
}
