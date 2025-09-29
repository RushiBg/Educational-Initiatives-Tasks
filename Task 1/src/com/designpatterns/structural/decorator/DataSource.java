package com.designpatterns.structural.decorator;

/**
 * Component Interface: Defines the contract for data sources.
 */
public interface DataSource {
    void writeData(String data);
    String readData();
}
