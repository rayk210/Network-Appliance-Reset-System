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
    
    private final String command;
    private final Duration timeout;
    private final ResponseExpectation expectation;  
    private final int retryCount;
    
    public CommandStep(String command, Duration timeout, ResponseExpectation expectation, int retryCount) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        
        if (expectation == null) {
            throw new IllegalArgumentException("Expectation cannot be null");
        }
        
        if (retryCount < 1) {
            throw new IllegalArgumentException("Retry count must be at least one");
        }
        
        this.command = command;
        
        this.timeout = timeout != null ? timeout : Duration.ZERO;
        
        this.expectation = expectation;
        
        this.retryCount = retryCount;
    }
    
    public String getCommand() {
        return command;
    }
    
    public Duration getTimeout() {
        return timeout;
    }
    
    public ResponseExpectation getExpectation() {
        return expectation;
    }
    
    public int getRetryCount() {
        return retryCount;
    }
}
