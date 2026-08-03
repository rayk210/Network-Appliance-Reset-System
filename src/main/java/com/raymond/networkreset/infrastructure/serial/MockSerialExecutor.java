/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.infrastructure.serial;

import com.raymond.networkreset.domain.command.CommandExecutor;
import com.raymond.networkreset.domain.command.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author rayk2
 */
public class MockSerialExecutor implements CommandExecutor {
    
    private boolean connection = false;
    private final List<String> sentCommands;
    private final Queue<Response> responses;
    
    public MockSerialExecutor(List<Response> responses) {
        this.sentCommands = new ArrayList<>();
        this.responses = new LinkedList<>(responses);
    }
    
    @Override
    public void connect() {
        this.connection = true;
    }
    
    @Override
    public void disconnect() {this.connection = false;}
    
    @Override
    public boolean isConnected() {return this.connection;}
    
    
    @Override
    public void sendCommand(String command) throws IOException {
        
        sentCommands.add(command);
    }
    
    @Override
    public Response receive() throws IOException {
        Response response = responses.poll();
        
        if (response == null) {
            throw new IOException("No responses available");
        }
        return response;
    }
}
