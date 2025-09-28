package com.designpatterns.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    private final AppLogger logger= AppLogger.getInstance();

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads an integer from the console within a specified range.
     *
     * @param prompt The message to display to the user.
     * @param min The minimum acceptable value.
     * @param max The maximum acceptable value.
     * @return A valid integer input by the user.
     */
    public int getIntInput(String prompt, int min, int max) {
        int input = -1;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    logger.warn("Input out of range. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                logger.error("Invalid input. Please enter a valid integer.", e);
                scanner.next(); // Clear the invalid input from the scanner buffer
            } finally {
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Consume the rest of the line
                }
            }
        }
    }

    /**
     * Reads a non-empty string from the console.
     *
     * @param prompt The message to display to the user.
     * @return A valid, non-empty string.
     */
    public String getStringInput(String prompt) {
        String input = "";
        while (input == null || input.trim().isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                logger.warn("Input cannot be empty. Please try again.");
            }
        }
        return input.trim();
    }
}
