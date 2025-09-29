package com.designpatterns.behavioural.command;

import com.designpatterns.utils.AppLogger;

/**
 * Concrete Command: Encapsulates the action of deleting a file.
 */
public class DeleteFileCommand implements Command {
    private String filename;

    public DeleteFileCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute() {
        AppLogger.getInstance().info("Executing DELETE command...");
        System.out.println("Simulating deletion of file: " + filename);
    }
}
