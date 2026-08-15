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
            
            for(int i = 0; i < step.getMaxRetries(); i++) {
                step.getAction().execute(executor);
                System.out.println((i == 0 ? "Sent: " : "Resent: ") + step.getAction());
                
                if(!step.expectsResponse()) {
                    System.out.println(step.getAction() + " does not expect a response");
                    success = true;
                    break;
                }
                
                Response response = executor.receive(step.getTimeout());
                System.out.println("Received: " + response.getRawText());
                
                if (step.getExpectation().matches(response)) {
                    System.out.println("Received correct response on attempt " + (i + 1) + "/" + step.getMaxRetries());
                    System.out.println("Continue to next step...");
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
