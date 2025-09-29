package com.designpatterns.structural.decorator;

import com.designpatterns.utils.AppLogger;

public class FileDataSource implements DataSource {
    private String fileName;
    private String content;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
        this.content = "";
    }

    @Override
    public void writeData(String data) {
        this.content = data;
        AppLogger.getInstance().info("Wrote '" + data + "' to file: " + fileName);
    }

    @Override
    public String readData() {
        AppLogger.getInstance().info("Read '" + fileName + "' from file: " + fileName);
        return this.content;
    }
}
