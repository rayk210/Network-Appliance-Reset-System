/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import com.raymond.networkreset.domain.enums.KeySignal;
import java.io.IOException;
import java.time.Duration;

/**
 *
 * @author rayk2
 */
public interface CommandExecutor {
    
    void connect() throws IOException;
    
    void disconnect() throws IOException;
    
    boolean isConnected();
    
    void sendCommand(String command) throws IOException;
    
    void sendKey(KeySignal keySequence) throws IOException;
    
    void sendBreak() throws IOException;
    
    Response receive(Duration timeout) throws IOException;
}
