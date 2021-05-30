package com.rmit.trading_backend.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;


@Configuration
@ComponentScan(basePackages = "com.rmit.trading_backend")
@EnableJpaRepositories(basePackages = "com.rmit.trading_backend")
@EnableTransactionManagement
@EnableWebMvc
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class AppConfig {

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {

        // config Hibernate properties
        Properties properties = new Properties();

        // config database connection
        // define type of database - PostgresSQL
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");

        // mapping all entities to the DB
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.rmit.trading_backend");

        // config data source
        // including database info
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        // local datasource
        dataSource.setUrl("jdbc:postgresql://localhost:5432/trading_company");
        dataSource.setUsername("postgres");
        dataSource.setPassword("3007");

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory);
        return tx;
    }

}
