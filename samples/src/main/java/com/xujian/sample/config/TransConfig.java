package com.xujian.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 一般不需要, 除非是有两套持久化依赖(JPA、JDBC)
 * Created by xujian on 2019-08-18
 */
@Configuration
public class TransConfig {
    //    @Bean(name = "txManager1")
//    @Bean
//    public PlatformTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

//    @Bean(name = "txManager2")
//    @Bean
//    public DataSourceTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
