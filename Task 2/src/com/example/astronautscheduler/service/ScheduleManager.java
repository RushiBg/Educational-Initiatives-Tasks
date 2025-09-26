package com.example.astronautscheduler.service;

import com.example.astronautscheduler.exception.TaskConflictException;
import com.example.astronautscheduler.exception.TaskNotFoundException;
import com.example.astronautscheduler.model.Task;
import com.example.astronautscheduler.observer.Observer;
import com.example.astronautscheduler.util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manages all tasks in the schedule.
 * This class implements the Singleton Design Pattern to ensure a single instance
 * manages the schedule throughout the application.
 * It also acts as the 'Subject' in the Observer Design Pattern, notifying observers
 * of events like task conflicts.
 */
public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<Observer> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Provides the global point of access to the Singleton instance.
     *
     * @return The single instance of ScheduleManager.
     */
    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    /**
     * Adds a task to the schedule after checking for conflicts.
     *
     * @param newTask The task to be added.
     * @throws TaskConflictException if the new task overlaps with an existing one.
     */
    public void addTask(Task newTask) throws TaskConflictException {
        for (Task existingTask : tasks) {
            if (newTask.overlapsWith(existingTask)) {
                String conflictMessage = String.format("Error: Task conflicts with existing task \"%s\".", existingTask.description());
                notifyObservers(conflictMessage);
                throw new TaskConflictException(conflictMessage);
            }
        }
        tasks.add(newTask);
        tasks.sort(Comparator.comparing(Task::startTime)); // Keep tasks sorted
        Logger.info("Task added successfully. No conflicts.");
        System.out.println("Task added successfully. No conflicts.");
    }

    /**
     * Removes a task from the schedule by its description.
     *
     * @param description The description of the task to remove.
     * @throws TaskNotFoundException if no task with the given description is found.
     */
    public void removeTask(String description) throws TaskNotFoundException {
        boolean removed = tasks.removeIf(task -> task.description().equalsIgnoreCase(description));
        if (!removed) {
            throw new TaskNotFoundException("Error: Task not found.");
        }
        Logger.info("Task '" + description + "' removed successfully.");
        System.out.println("Task removed successfully.");
    }

    /**
     * Returns an unmodifiable list of all tasks.
     *
     * @return A list of tasks sorted by start time.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }
}