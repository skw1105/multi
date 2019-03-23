package edu.autocar.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/*
 * JDBC에서 session 관련 사항을
 * Bean 등록으로 해줌
 */

@Configuration
@PropertySource("classpath:database.properties")
@MapperScan("edu.autocar.**.dao")
//dao의 구현체를 자동으로 생성

public class DatabaseConfig {

	@Autowired
	private Environment env; // database.properties 값 접근

	public DatabaseConfig() {
	}

	@Bean // 빈 이름은 메소드 명
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	//mybatis 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("edu.autocar.domain");
		
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		
		// MyBatis 설정
		org.apache.ibatis.session.Configuration config = sessionFactory.getObject().getConfiguration();
		config.setMapUnderscoreToCamelCase(true);
		config.setCacheEnabled(false);
		return sessionFactory.getObject();
	}
}
