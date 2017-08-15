package com.dayi.mybatis.spring.plus;

import com.dayi.mybatis.spring.plus.mapper.MybatisAutoMapperBuilder;
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
    private MybatisAutoMapperBuilder mybatisAutoMapperBuilder = new MybatisAutoMapperBuilder(this);

    public MybatisConfiguration() {
        super();
        this.mapperRegistry = new MybatisMapperRegistry(this);
    }

    public MybatisAutoMapperBuilder getMybatisAutoMapperBuilder() {
        return mybatisAutoMapperBuilder;
    }
}
