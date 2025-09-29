package com.designpatterns;

import com.designpatterns.creational.singleton.AppConfig;
import com.designpatterns.utils.AppLogger;
import com.designpatterns.utils.InputHandler;
import com.designpatterns.behavioural.command.*;
import com.designpatterns.behavioural.strategy.*;
import com.designpatterns.creational.factory.ReportFactory;
import com.designpatterns.creational.factory.Report;
import com.designpatterns.structural.decorator.*;
import com.designpatterns.structural.proxy.*;
import com.designpatterns.exceptions.InvalidReportTypeException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main application class to demonstrate the six chosen design patterns.
 * This class provides a menu-driven console interface for the user.
 */
public class Main {
    private static final AppLogger logger = AppLogger.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final InputHandler inputHandler = new InputHandler(scanner);
    private static boolean isRunning = true;

    public static void main(String[] args) {
        logger.info("Design Patterns Demonstration Application Started.");

        while (isRunning) {
            printMenu();
            int choice = inputHandler.getIntInput("Enter your choice: ", 0, 6);
            handleMenuChoice(choice);
        }

        logger.info("Application shutting down. Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n========= Design Patterns Demo Menu =========");
        System.out.println("--- Creational Patterns ---");
        System.out.println("1. Singleton Pattern (App Configuration)");
        System.out.println("2. Factory Method Pattern (Report Generator)");
        System.out.println("--- Structural Patterns ---");
        System.out.println("3. Decorator Pattern (File Read/Write with Encryption & Compression)");
        System.out.println("4. Proxy Pattern (Remote Resource Caching & Retry)");
        System.out.println("--- Behavioural Patterns ---");
        System.out.println("5. Strategy Pattern (Dynamic Sorting Algorithm)");
        System.out.println("6. Command Pattern (File Operations)");
        System.out.println("-------------------------------------------");
        System.out.println("0. Exit");
        System.out.println("===========================================");
    }

    private static void handleMenuChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    demonstrateSingleton();
                    break;
                case 2:
                    demonstrateFactoryMethod();
                    break;
                case 3:
                    demonstrateDecorator();
                    break;
                case 4:
                    demonstrateProxy();
                    break;
                case 5:
                    demonstrateStrategy();
                    break;
                case 6:
                    demonstrateCommand();
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    logger.warn("Invalid choice. Please select a valid option from the menu.");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred in the selected option", e);
        }
        if (isRunning) {
            inputHandler.getStringInput("\nPress Enter to continue...");
        }
    }

    private static void demonstrateSingleton() {
        System.out.println("\n--- Demonstrating Singleton Pattern ---");
        AppConfig config1 = AppConfig.getInstance();
        config1.displayConfig();

        System.out.println("Getting another instance of AppConfig...");
        AppConfig config2 = AppConfig.getInstance();

        if (config1 == config2) {
            System.out.println("Success: Both config1 and config2 are the same instance.");
            logger.info("Singleton pattern verified successfully.");
        } else {
            System.out.println("Failure: The instances are different.");
            logger.error("Singleton pattern verification failed.", null);
        }
    }

    private static void demonstrateFactoryMethod() throws InvalidReportTypeException {
        System.out.println("\n--- Demonstrating Factory Method Pattern ---");
        ReportFactory factory = new ReportFactory();
        String reportData = "This is the sample data for the report.";

        String type = inputHandler.getStringInput("Enter report type (pdf/csv): ");
        Report report = factory.createReport(type);
        report.generate(reportData);
        logger.info("Report of type '" + type + "' generated successfully.");
    }



    private static void demonstrateDecorator() {
        System.out.println("\n--- Demonstrating Decorator Pattern ---");
        DataSource source = new FileDataSource("my-secret-file.txt");
        String myData = "This is confidential data.";

        System.out.println("\n1. Writing plain data...");
        source.writeData(myData);
        System.out.println("   Reading plain data: " + source.readData());

        System.out.println("\n2. Writing data with Encryption...");
        DataSource encryptedSource = new EncryptionDecorator(source);
        encryptedSource.writeData(myData);
        System.out.println("   Reading from encrypted source: " + encryptedSource.readData());

        System.out.println("\n3. Writing data with Compression and Encryption...");
        DataSource compressedAndEncryptedSource = new CompressionDecorator(new EncryptionDecorator(source));
        compressedAndEncryptedSource.writeData(myData);
        System.out.println("   Reading from compressed & encrypted source: " + compressedAndEncryptedSource.readData());
    }

    private static void demonstrateProxy() {
        System.out.println("\n--- Demonstrating Proxy Pattern ---");
        ResourceFetcher fetcher = new ResourceFetcherProxy();

        System.out.println("First request for resource 'doc-A'...");
        System.out.println("Data: " + fetcher.fetchData("doc-A"));
        System.out.println("\nSecond request for resource 'doc-A' (should be cached)...");

        System.out.println("Data: " + fetcher.fetchData("doc-A"));

        System.out.println("\nFirst request for resource 'img-B' (may encounter transient errors)...");
        System.out.println("Data: " + fetcher.fetchData("img-B"));
    }

    private static void demonstrateStrategy() {
        System.out.println("\n--- Demonstrating Strategy Pattern ---");
        int[] numbers = { 5, 1, 4, 2, 8, 9, 3 };
        Sorter sorter = new Sorter();

        System.out.println("Original Array: " + Arrays.toString(numbers));
        int choice = inputHandler.getIntInput("Choose sorting algorithm (1=Bubble Sort, 2=Quick Sort): ", 1, 2);

        if (choice == 1) {
            sorter.setStrategy(new BubbleSortStrategy());
        } else {
            sorter.setStrategy(new QuickSortStrategy());
        }

        sorter.performSort(numbers);
        System.out.println("Sorted Array:   " + Arrays.toString(numbers));
        logger.info("Array sorted using the chosen strategy.");
    }

    private static void demonstrateCommand() {
        System.out.println("\n--- Demonstrating Command Pattern ---");
        FileManager invoker = new FileManager();

        String filename = inputHandler.getStringInput("Enter a filename (e.g., report.txt): ");

        Command createCommand = new CreateFileCommand(filename);
        Command deleteCommand = new DeleteFileCommand(filename);

        System.out.println("\nExecuting Create Command...");
        invoker.setCommand(createCommand);
        invoker.executeCommand();

        System.out.println("\nExecuting Delete Command...");
        invoker.setCommand(deleteCommand);
        invoker.executeCommand();
    }
}
