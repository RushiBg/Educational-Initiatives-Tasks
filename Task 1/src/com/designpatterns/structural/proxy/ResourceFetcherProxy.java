package com.designpatterns.structural.proxy;

import com.designpatterns.utils.AppLogger;

import java.util.HashMap;
import java.util.Map;

public class ResourceFetcherProxy implements ResourceFetcher {
    private HighLatencyResourceFetcher realFetcher;
    private final Map<String, String> cache = new HashMap<>();
    private final AppLogger logger = AppLogger.getInstance();
    private static final int MAX_RETRIES = 3;

    @Override
    public String fetchData(String resourceId) {
        // 1. Check cache first for performance optimization
        if (cache.containsKey(resourceId)) {
            logger.info("Returning cached data for '" + resourceId + "'.");
            return cache.get(resourceId);
        }

        // 2. Lazy initialization of the real, expensive object
        if (realFetcher == null) {
            realFetcher = new HighLatencyResourceFetcher();
        }

        // 3. Transient error handling with retry logic
        String data = null;
        for (int i = 1; i <= MAX_RETRIES; i++) {
            try {
                // Simulate a transient network failure (33% chance)
                if (Math.random() < 0.33 && i < MAX_RETRIES) {
                    throw new RuntimeException("Simulated network timeout!");
                }
                data = realFetcher.fetchData(resourceId);
                logger.info("Successfully fetched data on attempt " + i);
                break;
            } catch (Exception e) {
                logger.error("Attempt " + i + " failed to fetch data for '" + resourceId + "'", e);
                if (i == MAX_RETRIES) {
                    logger.error("Max retries reached. Could not fetch resource.", null);
                    return "Error: Failed to fetch resource after " + MAX_RETRIES + " attempts.";
                }
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        // 4. Cache the result for future requests
        if (data != null && !data.startsWith("Error")) {
            cache.put(resourceId, data);
            logger.info("Data for '" + resourceId + "' has been cached.");
        }

        return data;
    }
}
