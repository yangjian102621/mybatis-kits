package org.rockyang.mybatis.sharingjdbc.starter;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.shardingsphere.core.constant.properties.ShardingPropertiesConstant;
import io.shardingsphere.core.exception.ShardingException;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.util.DataSourceUtil;
import org.rockyang.mybatis.sharingjdbc.starter.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.rockyang.mybatis.sharingjdbc.starter.sharding.SpringBootShardingRuleConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Spring boot sharding and master-slave configuration.
 *
 * @author caohao
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootShardingRuleConfigurationProperties.class,
        SpringBootMasterSlaveRuleConfigurationProperties.class})
public class ShardingAutoConfiguration implements EnvironmentAware {
    
    @Autowired
    private SpringBootShardingRuleConfigurationProperties shardingProperties;
    
    @Autowired
    private SpringBootMasterSlaveRuleConfigurationProperties masterSlaveProperties;
    
    private final Map<String, DataSource> dataSourceMap = new HashMap<>();
    
    private final Properties props = new Properties();
    private final Map<String, Object> configMap = new HashMap();

    private final String dataSourceProperPrefix = "sharding.jdbc.datasource.";
    private final String shardingPropsPrefix = "sharding.jdbc.config.sharding.props.";

    @Bean
    public DataSource dataSource() throws SQLException {
        if (null == masterSlaveProperties.getMasterDataSourceName()) {
            return ShardingDataSourceFactory.createDataSource(
                    dataSourceMap,
                    shardingProperties.getShardingRuleConfiguration(),
                    configMap,
                    props);
        } else {
            return MasterSlaveDataSourceFactory.createDataSource(
                    dataSourceMap,
                    masterSlaveProperties.getMasterSlaveRuleConfiguration(),
                    configMap,
                    props);
        }
    }
    
    @Override
    public void setEnvironment(final Environment environment) {
        setDataSourceMap(environment);
        setShardingProperties(environment);
    }
    
    private void setDataSourceMap(final Environment environment)
    {
        Binder binder = Binder.get(environment);
        String dataSources = environment.getProperty(dataSourceProperPrefix+"names");
        for (String each : dataSources.split(",")) {
            try {
                System.out.println(dataSourceProperPrefix + each);
                Map<String, Object> dataSourceProps = binder.bind(dataSourceProperPrefix + each,
                        Map.class).get();
                Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource properties!");
                DataSource dataSource = DataSourceUtil.getDataSource(dataSourceProps.get("type").toString(), dataSourceProps);
                dataSourceMap.put(each, dataSource);
            } catch (final ReflectiveOperationException ex) {
                throw new ShardingException("Can't find datasource type!", ex);
            }
        }
    }
    
    private void setShardingProperties(final Environment environment) {

        String showSQL = environment.getProperty(shardingPropsPrefix + ShardingPropertiesConstant.SQL_SHOW.getKey());
        if (!Strings.isNullOrEmpty(showSQL)) {
            props.setProperty(ShardingPropertiesConstant.SQL_SHOW.getKey(), showSQL);
        }
        String executorSize =
                environment.getProperty(shardingPropsPrefix + ShardingPropertiesConstant.EXECUTOR_SIZE.getKey());
        if (!Strings.isNullOrEmpty(executorSize)) {
            props.setProperty(ShardingPropertiesConstant.EXECUTOR_SIZE.getKey(), executorSize);
        }
    }
}
