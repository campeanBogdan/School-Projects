package com.example.Assignment3;

import com.example.Assignment3.Model.Model.*;
import com.example.Assignment3.Model.Service.AdminService;
import com.example.Assignment3.Model.Service.ArticleService;
import com.example.Assignment3.Model.Service.Implementation.AdminServiceImpl;
import com.example.Assignment3.Model.Service.Implementation.ArticleServiceImpl;
import com.example.Assignment3.Model.Service.Implementation.WriterServiceImpl;
import com.example.Assignment3.Model.Service.WriterService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class JPAConfig {

    private EntityManagerFactory entityManagerFactory;


    @Bean
    public Article article() {
        return new Article();
    }

    @Bean
    public Writer writer() {
        return new Writer();
    }

    @Bean
    public Admin admin() {
        return new Admin();
    }

    @Bean
    public Reader reader() {
        return new Reader();
    }

    @Bean
    public Option option() { return new Option(); }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl();
    }

    @Bean
    public AdminService adminService() { return new AdminServiceImpl(); }


//    @Bean
//    public WriterService writerService() {
//        return new WriterServiceImpl();
//    }

    @ConfigurationProperties
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

//    @Bean
//    @Primary
//    public void entityManagerFactory(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
}
