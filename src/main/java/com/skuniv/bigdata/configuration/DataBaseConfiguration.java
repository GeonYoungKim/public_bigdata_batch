//package com.skuniv.bigdata.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataBaseConfiguration {
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://117.17.142.207:3306/real_estate_data?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf-8");
//        dataSource.setUsername( "root" );
//        dataSource.setPassword( "cs616" );
//        return dataSource;
//    }
//}
