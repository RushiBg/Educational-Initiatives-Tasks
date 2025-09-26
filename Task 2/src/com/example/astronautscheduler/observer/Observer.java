package com.example.astronautscheduler.observer;

/**
 * The Observer interface for the Observer Design Pattern.
 * Classes implementing this interface can be notified of updates.
 */
public interface Observer {
    void update(String msg);
}
