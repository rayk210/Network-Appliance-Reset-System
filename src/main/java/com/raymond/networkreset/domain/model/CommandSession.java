/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.model;

import com.raymond.networkreset.domain.exception.UnexpectedResponseException;
import com.raymond.networkreset.domain.valueobject.Response;
import java.io.IOException;


/**
 *
 * @author rayk2
 */
public class CommandSession {
    
    private final CommandExecutor executor;
    
    public CommandSession(CommandExecutor executor) {
        this.executor = executor;
    }
    
    public void execute(DeviceCommand command) throws IOException {
        
        for (CommandStep step : command.commands()) {
            executor.sendCommand(step.getCommand());
            
            Response response = executor.receive();
            
            if (!step.getExpectation().matches(response)) {
                throw new UnexpectedResponseException("Expected response did not match the actual");
            }
        }
    }
}
