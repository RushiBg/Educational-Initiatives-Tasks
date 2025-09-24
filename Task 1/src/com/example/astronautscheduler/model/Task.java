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

}
