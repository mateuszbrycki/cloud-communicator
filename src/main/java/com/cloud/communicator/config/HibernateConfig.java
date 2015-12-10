package com.cloud.communicator.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.cloud.communicator"})
@PropertySource(value = { "classpath:hibernate.properties" })
public class HibernateConfig {

    @Inject
    private Environment environment;

    //MySQL Configuration
    @Bean(name="sessionFactoryMySQL")
    public LocalSessionFactoryBean sessionFactoryMySQL() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceMySQL());
        sessionFactory.setPackagesToScan(new String[]{"com.cloud.communicator"});
        sessionFactory.setHibernateProperties(hibernatePropertiesMySQL());
        return sessionFactory;
    }

    @Bean(name="dataSourceMySQL")
    public DataSource dataSourceMySQL() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.mysql.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.mysql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.mysql.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.mysql.password"));
        return dataSource;
    }

    private Properties hibernatePropertiesMySQL() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.mysql.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Inject
    public HibernateTransactionManager transactionManagerMySQL(@Qualifier("sessionFactoryMySQL") SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    //PostgreSQL Configuration
    @Bean(name="sessionFactoryPostgres")
    public LocalSessionFactoryBean sessionFactoryPostgres() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourcePostgres());
        sessionFactory.setPackagesToScan(new String[]{"com.cloud.communicator"});
        sessionFactory.setHibernateProperties(hibernatePropertiesPostgres());
        return sessionFactory;
    }

    @Bean(name="dataSourcePostgres")
    @Primary
    public DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    private Properties hibernatePropertiesPostgres() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.postgresql.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Inject
    public HibernateTransactionManager transactionManagerPostgreSQL(@Qualifier("sessionFactoryPostgres") SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }


}
