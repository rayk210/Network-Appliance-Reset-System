/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.infrastructure.serial;

import com.raymond.networkreset.domain.command.CommandExecutor;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.enums.KeySignal;
import java.io.IOException;
import java.time.Duration;
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
    private final List<KeySignal> sentKeys = new ArrayList<>();
    
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
    public void sendBreak() {
        
    }
    
    @Override
    public void sendKey(KeySignal keySequence) {
        sentKeys.add(keySequence);
    }
    
    @Override
    public Response receive(Duration timeout) throws IOException {
        
        long deadline = System.currentTimeMillis() + timeout.toMillis();
        
        while(System.currentTimeMillis() < deadline) {
            
            Response response = responses.poll();
            
            if (response != null) {
                return response;
            }
            
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException(e);
            }
        }
        throw new IOException("Timed out waiting for response"); 
    }
    
    public List<String> getSentCommand() {
        return List.copyOf(sentCommands);
    }
    
    public List<KeySignal> getKeySignal() {
        return List.copyOf(sentKeys);
    }
}
