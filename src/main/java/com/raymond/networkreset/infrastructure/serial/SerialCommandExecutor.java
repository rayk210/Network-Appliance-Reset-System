/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.infrastructure.serial;

import com.raymond.networkreset.domain.command.CommandExecutor;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.enums.KeySignal;
import com.raymond.networkreset.domain.valueobject.SerialConfiguration;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 *
 *
 */
public class SerialCommandExecutor implements CommandExecutor {
    
    private InputStream input;
    private OutputStream output;
    private SerialPort comPort;
    private static final byte[] CRLF_BYTES = "\r\n".getBytes(StandardCharsets.UTF_8);
    private final SerialConfiguration config;
    private final boolean streamMode;
    
    public SerialCommandExecutor(SerialConfiguration config) {
        this.config = config;
        this.streamMode = false;
    }
    
    public SerialCommandExecutor(SerialConfiguration config, InputStream input, OutputStream output) {
        this.config = config;
        this.input = input;
        this.output = output;
        
        this.streamMode = true;
    }
    
    @Override
    public void connect() throws IOException {
        if (isConnected()) {
            return;
        }
        
        comPort = createPort();
        
        if (!comPort.openPort()) {
            throw new IOException("Failed to open " + config.getPort());
        }
        
        input = comPort.getInputStream();
        output = comPort.getOutputStream();
    }
    
    @Override
    public void disconnect() throws IOException {
        
        try {
            if (input != null) {
                input.close();
            }
            
            if (output != null) {
                output.close();
            }
            
            if (comPort != null && comPort.isOpen()) {
                if (!comPort.closePort()) {
                    throw new IOException("Failed to close com port");
                }
            }
        }
        finally {
            input = null;
            output = null;
            comPort = null;
        }
    }
    
    @Override
    public boolean isConnected() {
        if (streamMode) {
            return input != null && output != null;
        }
        return comPort != null && comPort.isOpen();
    }
    
    @Override
    public void sendCommand(String line) throws IOException {
        if(line == null || line.isBlank()) {
            throw new IllegalArgumentException("Command cannot be blank");
        }
        
        if(!isConnected()) {
            throw new IOException("Executor is not connected");
        }
        
        try {
            output.write((line).getBytes(StandardCharsets.UTF_8));
            output.write(CRLF_BYTES);
            output.flush();
        }
        catch (IOException e) {
            throw new IOException("Failed to send command: " + line, e);
        }
    }
    
    @Override
    public void sendBreak() throws IOException {
        if (!isConnected()) {
            throw new IOException("Serial executor is not connected");
        }
        
        try {
            comPort.setBreak();
        }
        catch (Exception e) {
            throw new IOException("Failed to send BREAK signal", e);
        }
    }
    
    @Override
    public void sendKey(KeySignal keySequence) throws IOException {
        if (!isConnected()) {
            throw new IOException("Serial executor is not connected");
        }
        
        try {
            output.write(keySequence.getKeySequence());
            output.flush();
        }
        catch (IOException e) {
            throw new IOException("Failed to send " + keySequence.name(), e);
        }
    }
    
    @Override
    public Response receive(Duration timeout) throws IOException {
        
        if(!isConnected())  {
            throw new IOException("Executor is not connected");
        }
        
        long deadline = System.currentTimeMillis() + timeout.toMillis();
        
        byte[] buffer = new byte[1024];
        
        StringBuilder builder = new StringBuilder();
        
        while(System.currentTimeMillis() < deadline) {
            
            try {
                if (input.available() > 0) {
                    int readBytes = input.read(buffer);
                    
                    if (readBytes > 0) {
                        builder.append(new String(buffer, 0, readBytes, StandardCharsets.UTF_8));
                        deadline = System.currentTimeMillis() + timeout.toMillis();
                    }
                }
                else {
                    try {
                        Thread.sleep(5);
                    }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Interrupted while waiting for response", e);
                    }
                }
            }
            catch (IOException e) {
                throw new IOException("Failed while reading from serial port", e);
            }
        }
        if (builder.length() == 0) {
            throw new IOException("Timed out waiting for response");
        }
        return new Response(builder.toString());
    }    
    
    private SerialPort createPort() {
        SerialPort port = SerialPort.getCommPort(config.getPort());
        port.setBaudRate(config.getBaudRate());
        port.setNumDataBits(config.getNumDataBits());
        port.setNumStopBits(config.getNumStopBits());
        port.setParity(config.getParity());
        port.setFlowControl(config.getFlowControl());
        
        return port;
    }
}
