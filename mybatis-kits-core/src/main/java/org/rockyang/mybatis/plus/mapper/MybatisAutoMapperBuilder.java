package org.rockyang.mybatis.plus.mapper;


import org.rockyang.mybatis.plus.MybatisConfiguration;
import org.rockyang.mybatis.plus.constant.Template;
import org.rockyang.mybatis.plus.languagedriver.ConditionsLanguageDriver;
import org.rockyang.mybatis.plus.support.BaseMapper;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.LanguageDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * 处理预定义的CRUD 方法
 *
 * @author chenzhaoju
 * @author yangjian
 */
public class MybatisAutoMapperBuilder   {
    public final static Logger _Logger = LoggerFactory.getLogger(MybatisAutoMapperBuilder.class);
    /** mybatis configuration */
    private MybatisConfiguration mybatisConfiguration;
    private LanguageDriver languageDriver ;
    /** 额外添加处理conditions 查询的 */
    private LanguageDriver conditionsLanguageDriver;

    public MybatisAutoMapperBuilder(MybatisConfiguration mybatisConfiguration) {
        this.mybatisConfiguration = mybatisConfiguration;
        this.languageDriver = mybatisConfiguration.getDefaultScriptingLanuageInstance();
        this.conditionsLanguageDriver = mybatisConfiguration.getLanguageRegistry().getDriver(ConditionsLanguageDriver.class);
    }

    /**
     * 注入mapper
     * @param builderAssistant mapper builder assistant
     * @param boundType bound sql type
     */
    public void inject(MapperBuilderAssistant builderAssistant, Class boundType){

        Class<?> actualModelClass = getActualModelClass(boundType);
        if(null != actualModelClass){
            Table table = Table.valueOf(builderAssistant,actualModelClass);

            if(null != table.getIdTableField()){
                // 防止没有id字段导致出错
                get(builderAssistant, boundType, actualModelClass, table);
                update(builderAssistant, boundType, actualModelClass, table);
                updateAll(builderAssistant, boundType, actualModelClass, table);
                delete(builderAssistant, boundType, actualModelClass, table);
                deleteByConditions(builderAssistant, boundType, actualModelClass, table);
            } else {
                _Logger.warn("{}没有定义id字段！！", actualModelClass.getSimpleName());
            }
            getByMap(builderAssistant, boundType, actualModelClass, table);
            getByConditions(builderAssistant, boundType, actualModelClass, table);
            searchAll(builderAssistant, boundType, actualModelClass, table);
            searchByMap(builderAssistant, boundType, actualModelClass, table);
            searchByConditions(builderAssistant, boundType, actualModelClass, table);

            getCount(builderAssistant, boundType, actualModelClass, table);
            getCountByMap(builderAssistant, boundType, actualModelClass, table);
            getCountByConditions(builderAssistant, boundType, actualModelClass, table);

            add(builderAssistant, boundType, actualModelClass, table);
        }
    }

    /**
     * 获取 model 的类型
     * @param mapperClass mapper class
     * @return 返回 model 类型
     */
    private Class<?> getActualModelClass(Class<?> mapperClass) {
        if (mapperClass == BaseMapper.class) {
            return null;
        } else {
            Type[] types = mapperClass.getGenericInterfaces();
            ParameterizedType target = null;
            for (Type type : types) {
                if (type instanceof ParameterizedType && BaseMapper.class.isAssignableFrom(mapperClass)) {
                    target = (ParameterizedType) type;
                    break;
                }
            }
            Type[] parameters = target.getActualTypeArguments();
            Class<?> modelClass = (Class<?>) parameters[0];
            return modelClass;
        }
    }

    private MappedStatement addMappedStatement(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, String id, SqlSource sqlSource,
                                               SqlCommandType sqlCommandType, Class<?> parameterClass, String resultMap, Class<?> resultType,
                                               KeyGenerator keyGenerator, String keyProperty, String keyColumn) {
        return addMappedStatement(builderAssistant,mapperClass,id,sqlSource,sqlCommandType, parameterClass,
                resultMap,resultType,keyGenerator,keyProperty,keyColumn,this.languageDriver);
    }

    private MappedStatement addMappedStatement(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, String id, SqlSource sqlSource,
                                               SqlCommandType sqlCommandType, Class<?> parameterClass, String resultMap, Class<?> resultType,
                                               KeyGenerator keyGenerator, String keyProperty, String keyColumn,LanguageDriver languageDriver) {
        String statementName = mapperClass.getName() + "." + id;
        if (this.mybatisConfiguration.hasStatement(statementName)) {
            return null;// 已经有了，不再覆盖
        }
		/* 缓存逻辑处理 */
        boolean isSelect = sqlCommandType == SqlCommandType.SELECT ? true : false;

        return builderAssistant.addMappedStatement(id, sqlSource, StatementType.PREPARED, sqlCommandType, null, null, null,
                parameterClass, resultMap, resultType, null, !isSelect, isSelect, false, keyGenerator, keyProperty, keyColumn,
                this.mybatisConfiguration.getDatabaseId(), languageDriver, null);
    }

    private MappedStatement addSelectMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass, String id, String sql, Class<?> resultType) {
        SqlSource sqlSource = languageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);
		/* 普通查询 */
        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.SELECT, null, null, resultType,
                new NoKeyGenerator(), null, null);
    }

    private MappedStatement addSelectConditionsMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass, String id, String sql, Class<?> resultType) {
        SqlSource sqlSource = this.conditionsLanguageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);
		/* 普通查询 */
        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.SELECT, null, null, resultType,
                new NoKeyGenerator(), null, null,this.conditionsLanguageDriver);
    }

    private MappedStatement addInsertMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass,
                                                     String id, String sql, Class<?> resultType, Table table) {
        SqlSource sqlSource = languageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);
        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.INSERT, resultType, null, int.class,
                new Jdbc3KeyGenerator(), table.getIdTableField().getColumnName(), null);

    }

    private MappedStatement addUpdateMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass, String id, String sql, Class<?> resultType) {
        SqlSource sqlSource = languageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);

        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.UPDATE, resultType, null, int.class,
                new NoKeyGenerator(), null, null);
    }

    private MappedStatement addDeleteMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass, String id, String sql, Class<?> resultType) {
        SqlSource sqlSource = languageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);
        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.DELETE, resultType, null, int.class,
                new NoKeyGenerator(), null, null);
    }

    private MappedStatement addDeleteConditionMappedStatement(MapperBuilderAssistant builderAssistant,Class<?> mapperClass, String id, String sql, Class<?> resultType) {
        SqlSource sqlSource = this.conditionsLanguageDriver.createSqlSource(this.mybatisConfiguration, sql, resultType);
		/* 普通查询 */
        return this.addMappedStatement(builderAssistant,mapperClass, id, sqlSource, SqlCommandType.DELETE, null, null, resultType,
                new NoKeyGenerator(), null, null,this.conditionsLanguageDriver);
    }

    /** 根据id 查找数据 */
    private void get(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET;
        // 组装语句
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName(),table.getIdTableField().getColumnName(),table.getIdTableField().getFieldName());

        addSelectMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql,modelClass);
    }

    /** 查询所有数据 */
    private void searchAll(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.SEARCH;
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName());

        addSelectMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 查询总记录数 */
    private void getCount(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET_COUNT;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),"");

        addSelectMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, Long.class);
    }

    /** 根据map 查询总记录数 */
    private void getCountByMap(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET_COUNT_BY_MAP;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(), genWhereSqlByMap());

        addSelectConditionsMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, Long.class);
    }


    /** 根据map 查询总记录数 */
    private void getCountByConditions(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET_COUNT_BY_CONDITIONS;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),genWhereSqlByCondition());

        addSelectConditionsMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, Long.class);
    }

    /** 根据map 查询所有记录 */
    private void getByMap(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET_BY_MAP;
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName(),genWhereSqlByMap());

        addSelectConditionsMappedStatement(builderAssistant, mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 根据Conditions 查询所有记录 */
    private void getByConditions(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.GET_BY_CONDITIONS;
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName(),genWhereSqlByCondition());

        addSelectConditionsMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 根据map 查询所有记录 */
    private void searchByMap(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.SEARCH_BY_MAP;
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName(),genWhereSqlByMap(), genOrderSqlByMap());

        addSelectMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }



    /** 根据map 查询所有记录 */
    private void searchByConditions(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.SEARCH_BY_CONDITIONS;
        String sql = String.format(sqlMethod.getSql(), table.getColumnsSqlAs(), table.getWrapName(),genWhereSqlByCondition());

        addSelectConditionsMappedStatement(builderAssistant, mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 添加数据 */
    private void add(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.ADD;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),table.getColumnsSql(),table.getFieldSql());

        addInsertMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass, table);
    }

    /** 增量更新数据 */
    private void update(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.UPDATE;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),table.getNotNullUpdateSetSql(),table.getIdTableField().getColumnName(),table.getIdTableField().getFieldName());

        addUpdateMappedStatement(builderAssistant, mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 全量更新数据 */
    private void updateAll(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.UPDATE_ALL;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),table.getUpdateSetSql(),table.getIdTableField().getColumnName(),table.getIdTableField().getFieldName());

        addUpdateMappedStatement(builderAssistant, mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /**
     * 根据条件批量删除数据
     * @param builderAssistant
     * @param mapperClass
     * @param modelClass
     * @param table
     */
    private void deleteByConditions(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.DELETE_BY_CONDITIONS;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),genDeleteWhereSqlByCondition());
        addDeleteConditionMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /** 删除数据 */
    private void delete(MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, Table table) {
        Template sqlMethod = Template.DELETE;
        String sql = String.format(sqlMethod.getSql(), table.getWrapName(),table.getIdTableField().getColumnName(),table.getIdTableField().getFieldName());
        addDeleteMappedStatement(builderAssistant,mapperClass, sqlMethod.getMethod(), sql, modelClass);
    }

    /**
     * 根据 map 生成查询条件
     * @return
     */
    private String genWhereSqlByMap(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n<if test=\"null != _parameter\">");
        sb.append("\n<where>");
        sb.append("\n<foreach collection=\"_parameter\" index=\"key\" item=\"value\" separator=\"AND\">");
        sb.append("\n<if test=\"'__order' != key\">");
        sb.append("\n ${key} = #{value} ");
        sb.append("</if>");
        sb.append("</foreach>");
        sb.append("</where>");
        sb.append("</if>");
        return sb.toString();
    }

    /**
     * 根据 map 生成 order 排序方式
     * @return
     */
    private String genOrderSqlByMap(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n<if test=\"null != _parameter\">");
        sb.append("\n<if test=\"null != _parameter.__order\">");
        sb.append("ORDER BY ${_parameter.__order}");
        sb.append("</if>");
        sb.append("</if>");
        return sb.toString();
    }

    /**
     * 生成删除的条件语句，删除的时候必须添加条件，防止误删
     * @return
     */
    private String genDeleteWhereSqlByCondition(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n<where>");
        sb.append("${_parameter.sql}");
        sb.append("</where>");
        return sb.toString();
    }

    private String genWhereSqlByCondition(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n<if test=\"null != _parameter\">");
        sb.append("\n<where>");
        sb.append("${_parameter.sql}");
        sb.append("</where>");
        sb.append(" ${_parameter.orderBySql} ");
        sb.append("</if>");
        return sb.toString();
    }

}
