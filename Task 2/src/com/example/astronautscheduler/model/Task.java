package com.example.astronautscheduler.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single task in the astronaut's schedule.
 * This is an immutable data class.
 *
 * @param description A brief description of the task.
 * @param startTime   The start time of the task.
 * @param endTime     The end time of the task.
 * @param priority    The priority level of the task (e.g., "High", "Medium", "Low").
 */
public record Task(String description, LocalTime startTime, LocalTime endTime, String priority) {

    /**
     * Checks if this task overlaps with another task.
     * Overlap occurs if the start time of one task is before the end time of the other,
     * and the end time of the first task is after the start time of the other.
     *
     * @param other The other task to check against.
     * @return true if the tasks overlap, false otherwise.
     */
    public boolean overlapsWith(Task other) {
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s-%s: %s [%s]",
                startTime.format(dtf),
                endTime.format(dtf),
                description,
                priority
        );
    }
}