package com.designpatterns.behavioural.command;

/**
 * Invoker: Executes a given command without knowing its details.
 */
public class FileManager {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set to execute.");
        }
    }
}
