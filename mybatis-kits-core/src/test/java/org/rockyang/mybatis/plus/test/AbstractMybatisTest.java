package org.rockyang.mybatis.plus.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 *
 * @author chenzhaoju
 * @author yangjian
 *
 */
public abstract class AbstractMybatisTest {
    public static final Logger _Logger = LoggerFactory.getLogger(AbstractMybatisTest.class);

    private String driverClassName = "com.mysql.jdbc.Driver" ;
    private String url = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf8" ;
    private String username = "root" ;
    private String password = "123456" ;


    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
