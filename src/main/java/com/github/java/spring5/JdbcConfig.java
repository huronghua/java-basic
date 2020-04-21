package com.github.java.spring5;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-04-27 16 23
 */
@Configuration
public class JdbcConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		DataSource dataSource = new DataSource();
		dataSource.setUrl("");
		dataSource.setPassword("");
		dataSource.setUsername("");
		dataSource.setDriverClassName("");
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
}
