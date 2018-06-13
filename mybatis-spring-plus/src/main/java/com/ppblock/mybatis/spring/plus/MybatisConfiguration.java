package com.ppblock.mybatis.spring.plus;

import com.ppblock.mybatis.spring.plus.languagedriver.ConditionsLanguageDriver;
import com.ppblock.mybatis.spring.plus.mapper.MybatisAutoMapperBuilder;
import org.apache.ibatis.session.Configuration;

/**
 *
 * 拓展mybatis 的配置对象
 *
 * @author chenzhaoju
 *
 */
public class MybatisConfiguration extends Configuration{
    /** 自动注入工具 */
    private MybatisAutoMapperBuilder mybatisAutoMapperBuilder;

    public MybatisConfiguration() {
        super();
        getLanguageRegistry().register(ConditionsLanguageDriver.class);
        this.mapperRegistry = new MybatisMapperRegistry(this);
        this.mybatisAutoMapperBuilder = new MybatisAutoMapperBuilder(this);
    }

    public MybatisAutoMapperBuilder getMybatisAutoMapperBuilder() {
        return mybatisAutoMapperBuilder;
    }
}
