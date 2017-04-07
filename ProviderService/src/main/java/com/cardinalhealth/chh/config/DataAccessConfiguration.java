package com.cardinalhealth.chh.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration for setting up JPA data access to this application. This
 * class sets up DB level details and other attributes necessary for making the
 * Db connection. The data source auto configuration works only if there is pone
 * data service.
 * 
 * Only one service instance of a given service type is bound to the
 * application. In this context,so if both a Oracle and a mySQL data is required
 * by the application then this auto-reconfiguration will not occur.
 * 
 * Only one bean of a matching type is in the Spring application context. For
 * example, you can have only one bean of type javax.sql.DataSource.
 * 
 * With auto-reconfiguration, Cloud Foundry creates the database or connection
 * factory bean itself, using its own values for properties such as host, port,
 * username and so on. For example, if you have a single javax.sql.DataSource
 * bean in your application context that Cloud Foundry auto-reconfigures and
 * binds to its own database service, Cloud Foundry does not use the username,
 * password and driver URL you originally specified. Instead, it uses its own
 * internal values. This is transparent to the application, which really only
 * cares about having a relational database to which it can write data but does
 * not really care what the specific properties are that created the database.
 * Also note that if you have customized the configuration of a service, such as
 * the pool size or connection properties, Cloud Foundry auto-reconfiguration
 * ignores the customizations
 * 
 * @author guru
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.cardinalhealth.chh")
public class DataAccessConfiguration {

	/**
	 * PCF automatically detects the data source and connects with the DB
	 * service name "cares-db that should be bounded to this app.
	 */
	@Autowired
	DataSource dataSource;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateDDLAuto;

	@Value("${packagesToScan}")
	private String packagestoScan;

	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * JPA EntityManagerFactory internally utilizes Hibernate session but it us
	 * transparent to the developers.
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(packagestoScan);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDDLAuto);
		jpaProperties.put("hibernate.show_sql", hibernateShowSQL);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	/**
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}