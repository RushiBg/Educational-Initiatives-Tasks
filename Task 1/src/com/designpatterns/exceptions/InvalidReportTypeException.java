package com.designpatterns.exceptions;

/**
 * Custom exception for demonstrating exception handling.
 */
public class InvalidReportTypeException extends Exception {
    public InvalidReportTypeException(String message) {
        super(message);
    }
}
