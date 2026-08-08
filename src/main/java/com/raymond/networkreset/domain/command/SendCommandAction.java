/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import java.io.IOException;


/**
 *
 * @author rayk2
 */
public class SendCommandAction implements CommandAction {
    private final String command;
    
    public SendCommandAction(String command) {
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("Command cannot be blank");
        }
        this.command = command;
    }
    
    @Override
    public void execute(CommandExecutor executor) throws IOException {
        executor.sendCommand(command);
    }
    
    @Override
    public String toString() {
        return command;
    }
}
