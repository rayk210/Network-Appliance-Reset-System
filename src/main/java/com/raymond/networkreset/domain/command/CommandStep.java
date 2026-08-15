/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import java.time.Duration;

/**
 *
 * @author rayk2
 */
public final class CommandStep {
    
    private final CommandAction action;
    private final Duration timeout;
    private final ResponseExpectation expectation;  
    private final int maxRetries;
    
    public CommandStep(CommandAction action, Duration timeout, ResponseExpectation expectation, int maxRetries) {
        if (action == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        
        if (maxRetries < 1) {
            throw new IllegalArgumentException("Retry count must be at least one");
        }
        
        this.action = action;
        
        this.timeout = timeout != null ? timeout : Duration.ZERO;
        
        this.expectation = expectation;
        
        this.maxRetries = maxRetries;
    }
    
    public CommandAction getAction() {
        return action;
    }
    
    public Duration getTimeout() {
        return timeout;
    }
    
    public ResponseExpectation getExpectation() {
        return expectation;
    }
    
    public int getMaxRetries() {
        return maxRetries;
    }
    
    public boolean expectsResponse() {
        return expectation != null;
    }
}
