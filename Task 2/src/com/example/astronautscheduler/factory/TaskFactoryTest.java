package com.example.astronautscheduler.factory;

import static org.junit.jupiter.api.Assertions.*;

import com.example.astronautscheduler.exception.InvalidTimeFormatException;
import com.example.astronautscheduler.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class TaskFactoryTest {

    // Test Case 1: The "Happy Path" - when everything is correct
    @Test
    void shouldCreateTaskSuccessfullyWithValidInput() {
        try {
            // Action: Call the method you want to test
            Task task = TaskFactory.createTask("Morning Exercise", "07:00", "08:00", "High");
            assertNotNull(task); // Check that a task object was actually created
            assertEquals("Morning Exercise", task.description());
            assertEquals(LocalTime.of(7, 0), task.startTime());
            assertEquals(LocalTime.of(8, 0), task.endTime());

        } catch (InvalidTimeFormatException e) {
            // If an exception is thrown, this test will fail, which is correct for this case.
            fail("Should not have thrown an exception for valid time.");
        }
    }

    // Test Case 2: The "Negative Path" - when you expect an error
    @Test
    void shouldThrowExceptionForInvalidTimeFormat() {
        // Action & Assertion:
        // This checks that the code inside the lambda () -> { ... }
        // throws the specific exception we expect.
        assertThrows(InvalidTimeFormatException.class, () -> {
            TaskFactory.createTask("Invalid Task", "25:00", "26:00", "Low");
        });
    }
}