/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.domain.model;

import java.io.IOException;

/**
 *
 * @author rayk2
 */
public interface CommandExecutor {
    
    void connect() throws IOException;
    
    void disconnect();
    
    boolean isConnected();
    
    void execute(DeviceCommand cmd);
    
    void sendCommand(String command)throws IOException;
    
    String readResponse() throws IOException;
}
