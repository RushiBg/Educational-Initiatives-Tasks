package com.example.astronautscheduler.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple logging utility to demonstrate the logging mechanism.
 */
public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.printf("[INFO] %s: %s%n", LocalDateTime.now().format(formatter), message);
    }

    public static void error(String message) {
        System.err.printf("[ERROR] %s: %s%n", LocalDateTime.now().format(formatter), message);
    }
}
