package com.blu4ck.Palm_Coffee.zero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"com.blu4ck.Palm_Coffee.one.repository", "com.blu4ck.Palm_Coffee.zero.repository"})
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Önce PostgreSQL'i deneyelim
        try {
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:3535/palmcoffee");
            dataSource.setUsername("postgres");
            dataSource.setPassword("2503");

            // Bağlantıyı test edelim
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("PostgreSQL baglantisi basarili.");
            }
        } catch (SQLException e) {
            // PostgreSQL bağlantısı başarısız olursa, SQLite'a geçiş yapalım
            System.out.println("PostgreSQL connection failed. SQLite'a geciliyor.");
            dataSource.setDriverClassName("org.sqlite.JDBC");
            dataSource.setUrl("jdbc:sqlite:./palmcoffee.db");
        }

        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.blu4ck.Palm_Coffee.one.model","com.blu4ck.Palm_Coffee.zero.model"); // Modellerinizi buraya doğru paket adı ile ayarlayın

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();

        // Dialect'i dinamik olarak belirleyelim
        if (dataSource.getClass().getName().contains("sqlite")) {
            properties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect"); // Doğru dialect
        } else {
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        }

        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true); // SQL sorgularını görebilmek için
        properties.put("hibernate.format_sql", true); // SQL sorgularını formatlı gösterir

        em.setJpaPropertyMap(properties);

        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return hibernateProperties -> hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
    }
}