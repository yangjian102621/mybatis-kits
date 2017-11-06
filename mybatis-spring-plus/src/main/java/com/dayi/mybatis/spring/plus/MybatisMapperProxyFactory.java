package com.dayi.mybatis.spring.plus;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;

/**
 * @author chenzhaoju
 */
public class MybatisMapperProxyFactory<T> extends MapperProxyFactory<T> {


    public MybatisMapperProxyFactory(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MybatisMapperProxy<T>(sqlSession, getMapperInterface(), getMethodCache());
        return newInstance(mapperProxy);
    }

}
