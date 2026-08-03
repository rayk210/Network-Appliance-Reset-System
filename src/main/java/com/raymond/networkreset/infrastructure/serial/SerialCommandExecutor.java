/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.infrastructure.serial;

import com.raymond.networkreset.domain.command.CommandExecutor;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.valueobject.SerialConfiguration;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 *
 */
public class SerialCommandExecutor implements CommandExecutor {
    
    private InputStream input;
    private OutputStream output;
    private SerialPort comPort;
    private final SerialConfiguration config;
    
    public SerialCommandExecutor(SerialConfiguration config) {
        this.config = config;
    }
    
    @Override
    public void connect() throws IOException {
        
        comPort = SerialPort.getCommPort(config.getPort());
        comPort.setBaudRate(config.getBaudRate());
        comPort.setNumDataBits(config.getNumDataBits());
        comPort.setNumStopBits(config.getNumStopBits());
        comPort.setParity(config.getParity());
        comPort.setFlowControl(config.getFlowControl());
        
        if (!comPort.openPort()) {
            throw new IOException("Failed to open " + config.getPort());
        }
        
        input = comPort.getInputStream();
        output = comPort.getOutputStream();
    }
    
    @Override
    public void disconnect() throws IOException {
        if (input != null) {
            input.close();
        }
        
        if (output != null) {
            output.close();
        }
        
        if (comPort != null && comPort.isOpen()) {
            
            if (!comPort.closePort()) {
                throw new IOException("Failed to close COM port");
            }
        }
    }
    
    @Override
    public boolean isConnected() {
        return comPort != null && comPort.isOpen();
    }
    
    @Override
    public void sendCommand(String line) throws IOException {
        output.write((line + "\r\n").getBytes(StandardCharsets.UTF_8));
        output.flush(); 
    }
    
    @Override
    public Response receive() throws IOException {
        
        byte[] buffer = new byte[1024];
        
        int bytesRead = input.read(buffer);
        
        if (bytesRead <= 0) {
            throw new IOException("No response received");
        }
        
        String text = new String(buffer,0, bytesRead, StandardCharsets.UTF_8);
        
        return new Response(text);
    }
}
