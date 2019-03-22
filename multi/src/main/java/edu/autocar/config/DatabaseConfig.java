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

@Configuration
@PropertySource("classpath:database.properties")
@MapperScan("edu.autocar.**.dao") // Mapper와 연결할 인터페이스 스캔 후 Dao의 구현체 클래스를 자동으로 만들어줌
public class DatabaseConfig { // (Dao구현 필요 없음)

	@Autowired
	private Environment env;

	// 데이터베이스 커넥션 풀 관리하는 모듈
	// Common DBCP로 데이터베이스 커넥션 관리
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));

		return dataSource;
	}

	// 트랜잭션 관리
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("edu.autocar.domain");
		sessionFactory.setMapperLocations(
					new PathMatchingResourcePatternResolver()
					.getResources("classpath:mapper/*.xml")
				);
		
		// MyBatis 설정
		org.apache.ibatis.session.Configuration config = 
				sessionFactory.getObject().getConfiguration();
		config.setMapUnderscoreToCamelCase(true);
		config.setCacheEnabled(false);
		
		return sessionFactory.getObject();
	}
}
