package com.example.astronautscheduler;

import com.example.astronautscheduler.exception.InvalidTimeFormatException;
import com.example.astronautscheduler.exception.TaskConflictException;
import com.example.astronautscheduler.exception.TaskNotFoundException;
import com.example.astronautscheduler.factory.TaskFactory;
import com.example.astronautscheduler.model.Task;
import com.example.astronautscheduler.observer.ConflictNotifier;
import com.example.astronautscheduler.service.ScheduleManager;
import com.example.astronautscheduler.util.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Setup Observer
        scheduleManager.addObserver(new ConflictNotifier());

        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    removeTask();
                    break;
                case "view":
                    viewTasks();
                    break;
                case "exit":
                    isRunning = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nAstronaut Daily Schedule Organizer");
        System.out.println("Commands: add, remove, view, exit");
        System.out.print("> ");
    }

    private static void addTask() {
        try {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            System.out.print("Enter start time (HH:mm): ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time (HH:mm): ");
            String endTime = scanner.nextLine();
            System.out.print("Enter priority (High/Medium/Low): ");
            String priority = scanner.nextLine();

            Task newTask = TaskFactory.createTask(description, startTime, endTime, priority);
            scheduleManager.addTask(newTask);

        } catch (InvalidTimeFormatException | TaskConflictException e) {
            Logger.error(e.getMessage());
            // The Observer pattern already prints the conflict message, so we only print other errors.
            if (e instanceof InvalidTimeFormatException) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            Logger.error("An unexpected error occurred while adding a task: " + e.getMessage());
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    private static void removeTask() {
        try {
            System.out.print("Enter the description of the task to remove: ");
            String description = scanner.nextLine();
            scheduleManager.removeTask(description);
        } catch (TaskNotFoundException e) {
            Logger.error(e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Logger.error("An unexpected error occurred while removing a task: " + e.getMessage());
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }

    private static void viewTasks() {
        List<Task> tasks = scheduleManager.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            System.out.println("\nScheduled Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}