package com.designpatterns.structural.proxy;

/**
 * Service Interface: Defines the contract for the resource fetcher.
 */
public interface ResourceFetcher {
    String fetchData(String resourceId);
}
