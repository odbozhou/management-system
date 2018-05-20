package com.management;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.management.dao")
public class ElectronicExperimentalReportManagementApplication {

	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource.druid")
	public DataSource dataSource(){
		return DruidDataSourceBuilder.create().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ElectronicExperimentalReportManagementApplication.class, args);
	}
}
