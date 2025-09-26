package com.example.astronautscheduler.observer;

public class ConflictNotifier implements Observer {

    @Override
    public void update(String msg) {
        System.out.println(msg);
    }
}
