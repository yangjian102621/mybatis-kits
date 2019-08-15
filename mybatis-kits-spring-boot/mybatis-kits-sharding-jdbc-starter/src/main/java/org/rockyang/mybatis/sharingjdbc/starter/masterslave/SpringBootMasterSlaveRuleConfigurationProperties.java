package org.rockyang.mybatis.sharingjdbc.starter.masterslave;

import io.shardingsphere.core.yaml.masterslave.YamlMasterSlaveRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Master slave rule configuration properties.
 *
 * @author caohao
 */
@ConfigurationProperties(prefix = "sharding.jdbc.config.masterslave")
public class SpringBootMasterSlaveRuleConfigurationProperties extends YamlMasterSlaveRuleConfiguration {
}
