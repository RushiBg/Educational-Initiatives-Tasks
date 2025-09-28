package com.designpatterns.creational.factory;

import com.designpatterns.exceptions.InvalidReportTypeException;

/**
 * Factory Method Pattern Use Case: Report Generator Factory.
 */
public class ReportFactory {

    /**
     * Factory method to create a report object based on type.
     *
     * @param type The type of report to create ("pdf" or "csv").
     * @return A Report object.
     * @throws InvalidReportTypeException if the type is not supported.
     */
    public Report createReport(String type) throws InvalidReportTypeException {
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidReportTypeException("Report type cannot be null or empty.");
        }

        return switch (type.toLowerCase()) {
            case "pdf" -> new PdfReport();
            case "csv" -> new CsvReport();
            default -> throw new InvalidReportTypeException("Unsupported report type: " + type);
        };
    }
}
