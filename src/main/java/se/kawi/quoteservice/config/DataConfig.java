package se.kawi.quoteservice.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("se.kawi.quoteservice.repository")
@EnableTransactionManagement
@EnableJpaAuditing
public class DataConfig {

//	@Bean
//	DataSource dataSource() {
//		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/quoteservicedb?useSSL=false");
//		config.setUsername("rkd3j");
//		config.setPassword("password");
//
//		return new HikariDataSource(config);
//	}

	@Bean
	DataSource dataSource() {
		HikariConfig cfg = new HikariConfig();
		cfg.setDriverClassName("org.h2.Driver");
		cfg.setJdbcUrl("jdbc:h2:mem:test:;MODE=MySQL;DB_CLOSE_DELAY=-1");
		return new HikariDataSource(cfg);
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

	@Bean
	JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setGenerateDdl(true);

		return adapter;
	}
	
//	@Bean
//	JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase(Database.MYSQL);
//		adapter.setGenerateDdl(true);
//
//		return adapter;
//	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan("se.kawi.quoteservice.model");

		return factory;
	}

}
