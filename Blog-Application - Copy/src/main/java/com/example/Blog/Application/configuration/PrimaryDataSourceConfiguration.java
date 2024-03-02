package com.example.Blog.Application.configuration;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.Blog.Application",
        excludeFilters = @ComponentScan.Filter(ReadOnlyRepository.class),
        entityManagerFactoryRef = "primaryEntityManagerFactory"
)
public class PrimaryDataSourceConfiguration {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer tomcatMaxActiveConnections;     // Max active connections during any heavy load
    private Integer tomcatMaxIdleConnections;       // Min active connections during low load
    private Integer tomcatTimeBetweenEvictionRunsInMillis;
    private Integer tomcatMinTimeForEvictionEligibilityInMillis;
    private Boolean tomcatRemoveAbandonedConnections;
    private Boolean showSQL;

    @Autowired
    public PrimaryDataSourceConfiguration() {
        this.url = ServerProperties.getPropertyValue("spring.datasource.url");
        this.username = ServerProperties.getPropertyValue("spring.datasource.username");
        this.password =ServerProperties.getPropertyValue("spring.datasource.password");
        this.driverClassName = ServerProperties.getPropertyValue("spring.datasource.driver-class-name");
        this.tomcatMaxActiveConnections = Integer.parseInt(ServerProperties.getPropertyValue("spring.datasource.tomcat.max-active"));
        this.tomcatMaxIdleConnections = Integer.parseInt(ServerProperties.getPropertyValue("spring.datasource.tomcat.max-idle"));
        this.tomcatTimeBetweenEvictionRunsInMillis = Integer.parseInt(ServerProperties.getPropertyValue("spring.datasource.tomcat.time-between-eviction-runs-millis"));
        this.tomcatMinTimeForEvictionEligibilityInMillis = Integer.parseInt(ServerProperties.getPropertyValue("spring.datasource.tomcat.min-evictable-idle-time-millis"));
        this.tomcatRemoveAbandonedConnections = Boolean.valueOf(ServerProperties.getPropertyValue("spring.datasource.tomcat.remove-abandoned"));
        this.showSQL = Boolean.valueOf(ServerProperties.getPropertyValue("spring.jpa.show-sql"));
    }

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(url);
        poolProperties.setInitialSize(20);
        poolProperties.setMinIdle(10);
        poolProperties.setMaxIdle(20);
        poolProperties.setMaxActive(200);
        poolProperties.setMaxWait(1000);
        poolProperties.setUsername(username);
        poolProperties.setPassword(password);
        poolProperties.setTestOnBorrow(true);
        poolProperties.setTestOnReturn(true);
        poolProperties.setDriverClassName(driverClassName);
        //poolProperties.setMaxActive(tomcatMaxActiveConnections);
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(poolProperties);
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        Map<String, String> properties = new HashMap<>();
//        properties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
//        properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put("hibernate.show_sql", String.valueOf(showSQL));
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(primaryDataSource());
        factoryBean.setPackagesToScan("com.example.Blog.Application");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.getJpaPropertyMap().putAll(properties);
        return factoryBean;
    }
}