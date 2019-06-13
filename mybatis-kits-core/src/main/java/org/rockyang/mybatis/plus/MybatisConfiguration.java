package org.rockyang.mybatis.plus;

import org.rockyang.mybatis.plus.languagedriver.ConditionsLanguageDriver;
import org.rockyang.mybatis.plus.mapper.MybatisAutoMapperBuilder;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

/**
 * 拓展mybatis 的配置对象
 *
 * @author yangjian
 * @author chenzhaoju
 */
public class MybatisConfiguration extends Configuration{
    /** 自动注入工具 */
    private MybatisAutoMapperBuilder mybatisAutoMapperBuilder;
    // fixed for mybatis-3.5.1
    protected final MapperRegistry mapperRegistry;

    public MybatisConfiguration() {
        super();
        getLanguageRegistry().register(ConditionsLanguageDriver.class);
        this.mapperRegistry = new MybatisMapperRegistry(this);
        this.mybatisAutoMapperBuilder = new MybatisAutoMapperBuilder(this);
    }

    public MybatisAutoMapperBuilder getMybatisAutoMapperBuilder() {
        return mybatisAutoMapperBuilder;
    }

    /**
     * 以下代码都是从 org.apache.ibatis.session.Configuration 直接搬过来用的
     */
    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void addMappers(String packageName, Class<?> superType) {
        mapperRegistry.addMappers(packageName, superType);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }
}
