package com.designpatterns.structural.proxy;

import com.designpatterns.utils.AppLogger;

/**
 * Real Subject: Represents the actual resource-intensive object.
 */
public class HighLatencyResourceFetcher implements ResourceFetcher {
    public HighLatencyResourceFetcher() {
        // Simulate a costly initialization
        try {
            AppLogger.getInstance().info("Initializing connection to remote resource...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String fetchData(String resourceId) {
        // Simulate network latency
        try {
            AppLogger.getInstance().info("Fetching data for '" + resourceId + "' from remote source...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Data for " + resourceId;
    }
}