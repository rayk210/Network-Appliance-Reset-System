/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import com.raymond.networkreset.domain.exception.UnexpectedResponseException;
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
            boolean success = false;
            System.out.println("Sent: " + step.getAction());
            
            for(int i = 0; i < step.getMaxRetries(); i++) {
                step.getAction().execute(executor);
                
                Response response = executor.receive(step.getTimeout());
                System.out.println("Received: " + response.getRawText());
                
                if (step.getExpectation().matches(response)) {
                    success = true;
                    break;
                }
            }
            if (!success) {
                throw new UnexpectedResponseException("Expected response did not match actual response");
            }
        }
    }
}
