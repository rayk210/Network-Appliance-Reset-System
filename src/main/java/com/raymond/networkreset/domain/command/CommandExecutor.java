/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import java.io.IOException;

/**
 *
 * @author rayk2
 */
public interface CommandExecutor {
    
    void connect() throws IOException;
    
    void disconnect() throws IOException;
    
    boolean isConnected();
    
    void sendCommand(String command) throws IOException;
    
    Response receive() throws IOException;
}
