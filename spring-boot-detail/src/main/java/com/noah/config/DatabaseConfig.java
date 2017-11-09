package com.noah.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	@Value("${db.connection.urlmaster}")
	private String PROPERTY_DB_URL_MASTER;
	
	@Value("${db.connection.urlslave}")
	private String PROPERTY_DB_URL_SLAVE;
	
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
	public DataSource buildDynamicDataSource() throws PropertyVetoException{
		DynamicDataSource dds = new DynamicDataSource();
		//DataSource master = buildMasterDataSource();
		//DataSource slave = buildSlaveDataSource();
		DataSource master = buildMasterDataSourceWithoutPooling();
		DataSource slave = buildSlaveDataSourceWithoutPooling();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("master", master);
		targetDataSources.put("slave", slave);
		dds.setDefaultTargetDataSource(targetDataSources.get("master"));
		dds.setTargetDataSources(targetDataSources);
		return dds;
	}
	
	//MasterDB use C3P0 connection pool	
	private DataSource buildMasterDataSource() throws PropertyVetoException{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass(PROPERTY_DB_DRIVER);
		cpds.setJdbcUrl(PROPERTY_DB_URL_MASTER);
		cpds.setUser(PROPERTY_DB_USERNAME);
		cpds.setPassword(PROPERTY_DB_PASSWORD);
		cpds.setMinPoolSize(Integer.valueOf(PROPERTY_DB_MIN_SIZE));
		cpds.setMaxPoolSize(Integer.valueOf(PROPERTY_DB_MAX_SIZE));
		cpds.setInitialPoolSize(Integer.valueOf(PROPERTY_DB_INITIAL_SIZE)); //normally, minPoolSize <= initialPoolSize <= maxPoolSize  
		cpds.setMaxIdleTime(Integer.valueOf(PROPERTY_DB_MAX_IDLE_TIME)); //c3p0 max idle means the max wait, unit is second
		cpds.setAcquireRetryAttempts(Integer.valueOf(PROPERTY_DB_RETRY_ATTEMPS)); //once got the connection fail, how many times it retry at most
		cpds.setAcquireRetryDelay(Integer.valueOf(PROPERTY_DB_RETRY_DELAY));//unit is millisecond. once got the connection fail, how long it waits for the next try
		LOGGER.info("Initial the Master DB DataSource(c3p0) successfully...");
		return cpds;
	}
	
	//SlaveDB use DBCP connection pool
	private DataSource buildSlaveDataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(PROPERTY_DB_DRIVER);
		bds.setUrl(PROPERTY_DB_URL_SLAVE);
		bds.setUsername(PROPERTY_DB_USERNAME);
		bds.setPassword(PROPERTY_DB_PASSWORD);
		bds.setMaxActive(Integer.valueOf(PROPERTY_DB_MAX_SIZE));
		bds.setMinIdle(Integer.valueOf(PROPERTY_DB_MIN_SIZE));
		bds.setInitialSize(Integer.valueOf(PROPERTY_DB_INITIAL_SIZE));
		bds.setMaxWait(30000L); // unit is milisecond
		bds.setRemoveAbandoned(true); //enable auto recollect
		bds.setRemoveAbandonedTimeout(10); // auto recollect timeout, unit is second
		LOGGER.info("Initial the Slave DB DataSource(dbcp) successfully...");
		return bds;
	}
	
	private DataSource buildSlaveDataSourceWithoutPooling(){
		//jdbc:postgresql://127.0.0.1:5432/SlaveSpringBootDB
		PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("127.0.0.1");
        ds.setDatabaseName("SlaveSpringBootDB");
        ds.setPortNumber(Integer.parseInt("5432"));
        ds.setUser("noah");
        ds.setPassword("123456");
        return ds;
	}
	
	private DataSource buildMasterDataSourceWithoutPooling(){
		//jdbc:postgresql://127.0.0.1:5432/SlaveSpringBootDB
		PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("127.0.0.1");
        ds.setDatabaseName("SpringBootDB");
        ds.setPortNumber(Integer.parseInt("5432"));
        ds.setUser("noah");
        ds.setPassword("123456");
        return ds;
	}
	
	
	@Bean
	//LocalContainerEntityManagerFactoryBean is an implementation of EntityManagerFactory
	//can control every detail of all entities 
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(buildDynamicDataSource());
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
