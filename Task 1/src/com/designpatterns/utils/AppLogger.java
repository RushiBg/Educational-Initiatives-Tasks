package com.designpatterns.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple singleton logger to demonstrate the logging mechanism.
 */
public class AppLogger {
    private static final AppLogger INSTANCE = new AppLogger();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AppLogger() {}

    public static AppLogger getInstance() {
        return INSTANCE;
    }

    private void log(String level, String message) {
        System.out.printf("[%s] [%s] - %s\n", dtf.format(LocalDateTime.now()), level, message);
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void warn(String message) {
        log("WARN", message);
    }

    public void error(String message, Throwable throwable) {
        log("ERROR", message + (throwable != null ? ": " + throwable.getMessage() : ""));
    }
}

