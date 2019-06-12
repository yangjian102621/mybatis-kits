package org.rockyang.mybatis.spring.plus.plugins.page;

import org.rockyang.mybatis.spring.plus.MybatisRowBounds;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * 分页插件
 * @author chenzhaoju
 *
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class, CacheKey.class ,
        BoundSql.class})})
public class PaginationInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget(); //被代理对象
        Object[] args = invocation.getArgs(); //方法参数

        if(target instanceof Executor){
            MappedStatement mappedStatement = (MappedStatement) args[0];
            Object parameterObject = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            BoundSql boundSql = (BoundSql) args[5];

            if(null == rowBounds || RowBounds.DEFAULT == rowBounds){
                return invocation.proceed(); // 不用处理分页
            }

            if(rowBounds instanceof MybatisRowBounds){
                Page page = ((MybatisRowBounds) rowBounds).getPage();
                String sql = boundSql.getSql();

                Executor executor = (Executor) target;
                Connection connection = null;
                try {
                    connection = executor.getTransaction().getConnection();

                    StringBuilder sb = new StringBuilder();
                    sb.append("select count(1) from ( ");
                    sb.append(sql).append(" ) temp ");

                    PreparedStatement preparedStatement = connection.prepareStatement(sb.toString());
                    // 处理参数
                    LanguageDriver lang = mappedStatement.getLang();
                    ParameterHandler parameterHandler = lang.createParameterHandler(mappedStatement, parameterObject, boundSql);
                    parameterHandler.setParameters(preparedStatement);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    long total = 0 ;
                    if(resultSet.next()){
                        total = resultSet.getLong(1);
                    }
                    page.setTotalRecord(total);

                    MetaObject boundSqlMetaObject = SystemMetaObject.forObject(boundSql);
                    StringBuilder limitSql = buildLimitSql(page, sql);

                    boundSqlMetaObject.setValue("sql",limitSql.toString());

                    Object proceed = invocation.proceed();
                    if(null != proceed && proceed instanceof List){
                        List result = (List) proceed;
                        page.setResults(result);
                        return proceed;
                    }
                    return proceed;
                }finally {
                    // 取的是当前事务的 connection 不需要在这里关闭
                    /*if(null != connection){
                        try {
                            connection.close();
                        } catch (Exception e) {
                            _Logger.debug("分页查询关闭连接错误",e);
                        }
                    }*/
                }
            }

        }
        return invocation.proceed();
    }

    private StringBuilder buildLimitSql(Page page, String sql) {
        StringBuilder limit = new StringBuilder();
        limit.append(sql);
        limit.append(" LIMIT ").append(page.getStartRow()).append(" , ").append(page.getPageSize());
        return limit;
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof CachingExecutor){
            MetaObject cachingExecutorMetaObject = SystemMetaObject.forObject(target);
            Object delegate = cachingExecutorMetaObject.getValue("delegate");
            Object wrap = Plugin.wrap(delegate, this);
            cachingExecutorMetaObject.setValue("delegate",wrap);
        }
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {}


}
