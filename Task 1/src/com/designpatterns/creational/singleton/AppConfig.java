package com.designpatterns.creational.singleton;

import com.designpatterns.utils.AppLogger;

/**
 * Singleton Pattern Use Case: Application Configuration Manager.
 * Ensures a single, globally accessible point for configuration settings.
 */
public class AppConfig {
    private static volatile AppConfig instance;
    private final AppLogger logger = AppLogger.getInstance();

    private String appVersion;
    private String databaseUrl;

    // Private constructor to prevent instantiation
    private AppConfig() {
        // Simulate loading configuration from a file
        this.appVersion = "1.0.0";
        this.databaseUrl = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
        logger.info("AppConfig instance created and settings loaded.");
    }

    /**
     * Provides the single instance of AppConfig, creating it if it doesn't exist.
     * Double-checked locking is used to ensure thread safety and performance.
     *
     * @return The single instance of AppConfig.
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void displayConfig() {
        System.out.println("--- Application Configuration ---");
        System.out.println("App Version: " + appVersion);
        System.out.println("Database URL: " + databaseUrl);
        System.out.println("---------------------------------");
    }
}
