package com.example.astronautscheduler.factory;

import com.example.astronautscheduler.exception.InvalidTimeFormatException;
import com.example.astronautscheduler.model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {

    /**
     * Creates a new Task object after validating the inputs.
     *
     * @param description The description of the task.
     * @param startTimeStr The start time as a string (HH:mm).
     * @param endTimeStr The end time as a string (HH:mm).
     * @param priority The priority of the task.
     * @return A new Task object.
     * @throws InvalidTimeFormatException if the time format is invalid or end time is not after start time.
     */
    public static Task createTask(String description, String startTimeStr, String endTimeStr, String priority) throws InvalidTimeFormatException {
        try{
            LocalTime startTime=LocalTime.parse(startTimeStr);
            LocalTime endTime=LocalTime.parse(endTimeStr);

            if(endTime.isBefore(startTime) ||  endTime.equals(startTime)) {
                throw new InvalidTimeFormatException("End time should be after start time");
            }
            return new Task(description, startTime, endTime, priority);
        } catch (DateTimeParseException e){
            throw new InvalidTimeFormatException("Error: Invalid time format. Please use HH:mm.");
        }
    }
}