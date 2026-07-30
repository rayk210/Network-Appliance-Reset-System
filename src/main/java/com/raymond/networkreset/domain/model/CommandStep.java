/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.model;

/**
 *
 * @author rayk2
 */
public final class CommandStep {
    
    private final String command;
    private final ResponseExpectation expectation;
    
    public CommandStep(String command, ResponseExpectation expectation) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        
        if (expectation == null) {
            throw new IllegalArgumentException("Expectation cannot be null");
        }
        
        this.command = command;
        this.expectation = expectation;
    }
    
    public String getCommand() {
        return command;
    }
    
    public ResponseExpectation getExpectation() {
        return expectation;
    }
}
