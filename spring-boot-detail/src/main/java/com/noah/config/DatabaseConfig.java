package com.noah.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
	
	@Value("${db.connection.driver}")
	private String PROPERTY_DB_DRIVER;
	
	@Value("${db.connection.url}")
	private String PROPERTY_DB_URL;
	
	@Value("${db.connection.username}")
	private String PROPERTY_DB_USERNAME;
	
	@Value("${db.connection.password}")
	private String PROPERTY_DB_PASSWORD;
	
	@Value("${db.connection.poolsize.min}")
	private String PROPERTY_DB_MIN_SIZE;
	
	@Value("${db.connection.poolsize.max}")
	private String PROPERTY_DB_MAX_SIZE;
	
	@Value("${db.connection.poolsize.initial}")
	private String PROPERTY_DB_INITIAL_SIZE;
	
	@Value("${db.connection.maxidletime}")
	private String PROPERTY_DB_MAX_IDLE_TIME;
	
	@Value("${db.connection.retry.attemps}")
	private String PROPERTY_DB_RETRY_ATTEMPS;
	
	@Value("${db.connection.retry.delay}")
	private String PROPERTY_DB_RETRY_DELAY;
	
	private String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
	
	private String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	
	@Autowired
	private Environment env;

	
	
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass(PROPERTY_DB_DRIVER);
		cpds.setJdbcUrl(PROPERTY_DB_URL);
		cpds.setUser(PROPERTY_DB_USERNAME);
		cpds.setPassword(PROPERTY_DB_PASSWORD);
		cpds.setMinPoolSize(Integer.valueOf(PROPERTY_DB_MIN_SIZE));
		cpds.setMaxPoolSize(Integer.valueOf(PROPERTY_DB_MAX_SIZE));
		cpds.setInitialPoolSize(Integer.valueOf(PROPERTY_DB_INITIAL_SIZE)); //normally, minPoolSize <= initialPoolSize <= maxPoolSize  
		cpds.setMaxIdleTime(Integer.valueOf(PROPERTY_DB_MAX_IDLE_TIME)); //unit is second. once the connection is idle for longer than that period, it will be recollected .
		cpds.setAcquireRetryAttempts(Integer.valueOf(PROPERTY_DB_RETRY_ATTEMPS)); //once got the connection fail, how many times it retry at most
		cpds.setAcquireRetryDelay(Integer.valueOf(PROPERTY_DB_RETRY_DELAY));//unit is millisecond. once got the connection fail, how long it waits for the next try
		LOGGER.info("Initial the DataSource successfully...");
		return cpds;
	}
	
	@Bean
	//LocalContainerEntityManagerFactoryBean is an implementation of EntityManagerFactory
	//can control every detail of all entities 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("com.noah.domain");
		entityManagerFactoryBean.setJpaProperties(hbProperties());
		return entityManagerFactoryBean;
	}
	
	private Properties hbProperties(){
		Properties properties = new Properties();
		properties.put(PROPERTY_HIBERNATE_DIALECT, env.getProperty(PROPERTY_HIBERNATE_DIALECT));
		properties.put(PROPERTY_HIBERNATE_SHOW_SQL, env.getProperty(PROPERTY_HIBERNATE_SHOW_SQL));
		return properties;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() throws PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
