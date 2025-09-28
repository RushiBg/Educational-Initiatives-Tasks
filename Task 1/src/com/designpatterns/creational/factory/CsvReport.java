package com.designpatterns.creational.factory;

import com.designpatterns.utils.AppLogger;

/**
 * Concrete Product: Implements a CSV report generator.
 */
public class CsvReport implements Report {
    @Override
    public void generate(String data) {
        AppLogger.getInstance().info("Generating CSV Report...");
        System.out.println("type,data");
        System.out.println("csv,\"" + data.replace("\"", "\"\"") + "\"");
    }
}
