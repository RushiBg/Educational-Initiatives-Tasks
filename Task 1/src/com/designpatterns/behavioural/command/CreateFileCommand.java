package com.designpatterns.behavioural.command;

import com.designpatterns.utils.AppLogger;

/**
 * Concrete Command: Encapsulates the action of creating a file.
 */
public class CreateFileCommand implements Command {
    private String filename;

    public CreateFileCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute() {
        // In a real app, this would interact with the file system.
        AppLogger.getInstance().info("Executing CREATE command...");
        System.out.println("Simulating creation of file: " + filename);
    }
}
