package com.designpatterns.exceptions;

/**
 * Custom exception for demonstrating exception handling in the program.
 */
public class InvalidReportTypeException extends Exception {
    public InvalidReportTypeException(String message) {
        super(message);
    }
}