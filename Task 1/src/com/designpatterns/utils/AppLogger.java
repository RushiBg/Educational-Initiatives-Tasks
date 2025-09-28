package com.designpatterns.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple singleton logger to demonstrate the logging mechanism.
 */
public class AppLogger {
    private static final AppLogger instance = new AppLogger();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AppLogger() {}

    public static AppLogger getInstance() {
        return instance;
    }

    public void log(String level,String message) {
        System.out.printf("[%s] [%s] - %s\n", dtf.format(LocalDateTime.now()), level, message);
    }

    public void info(String msg){
        log("INFO",msg);
    }

    public void warn(String msg){
        log("WARN",msg);
    }

    public void error(String msg,Throwable throwable){
        log("ERROR",msg + (throwable != null ? ": " + throwable.getMessage() : ""));
    }
}
