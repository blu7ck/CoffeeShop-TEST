package com.blu4ck.Palm_Coffee.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseService {

    @Autowired
    private Environment env;

    public boolean isOnline() {
        try (Connection connection = DriverManager.getConnection(env.getProperty("spring.datasource.postgresql.url"))) {
            return connection.isValid(2);
        } catch (SQLException e) {
            return false; // Bağlantı kurulamıyorsa çevrimdışı
        }
    }

    public void switchToOfflineMode() {
        // SQLite veritabanını kullanarak çevrimdışı modda çalışma mantığı
        System.out.println("Offline mode activated: Using SQLite database.");
    }

    public void switchToOnlineMode() {
        // PostgreSQL veritabanını kullanarak çevrimiçi çalışma mantığı
        System.out.println("Online mode activated: Using PostgreSQL database.");
    }
}
