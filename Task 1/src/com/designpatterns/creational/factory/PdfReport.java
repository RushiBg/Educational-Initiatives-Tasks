package com.designpatterns.creational.factory;

import com.designpatterns.utils.AppLogger;

/**
 * Concrete Product: Implements a PDF report generator.
 */
public class PdfReport implements Report {
    @Override
    public void generate(String data) {
        AppLogger.getInstance().info("Generating PDF Report...");
        System.out.println("================ PDF REPORT ================");
        System.out.println("Data: " + data);
        System.out.println("==========================================");
    }
}
