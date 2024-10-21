package com.blu4ck.Palm_Coffee;

import com.blu4ck.Palm_Coffee.zero.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalmCoffeeApplication {

    @Autowired
    private DatabaseService databaseService;

    public static void main(String[] args) {
        SpringApplication.run(PalmCoffeeApplication.class, args);
    }

    public void run(String... args) {
        if (databaseService.isOnline()) {
            databaseService.switchToOnlineMode();
        } else {
            databaseService.switchToOfflineMode();
        }
    }
}
