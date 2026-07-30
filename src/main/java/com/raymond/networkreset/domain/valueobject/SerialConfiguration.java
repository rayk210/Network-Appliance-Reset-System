/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.valueobject;

/**
 *
 * @author rayk2
 */
public final class SerialConfiguration {
    private final String port;
    
    private final int baudRate;
    
    private final int numDataBits;
    
    private final int numStopBits;
    
    private final int parity;
    
    private final int flowControl;
    
    public SerialConfiguration(String port, int baudRate, int numDataBits,
            int numStopBits, int parity, int flowControl) {
        
        if (port == null || port.isBlank()) {
            throw new IllegalArgumentException("Port cannot be blank");
        }
        
        if (baudRate <= 0) {
            throw new IllegalArgumentException("Invalid baud rate");
        }
        
        if (numDataBits <= 0) {
            throw new IllegalArgumentException("Invalid data bits");
        }
        
        if (numStopBits <= 0) {
            throw new IllegalArgumentException("Invalid stop bits");
        }
        
        this.port = port;
        this.baudRate = baudRate;
        this.numDataBits = numDataBits;
        this.numStopBits = numStopBits;
        this.parity = parity;
        this.flowControl = flowControl;
    }

    public String getPort() {
        return port;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public int getNumDataBits() {
        return numDataBits;
    }

    public int getNumStopBits() {
        return numStopBits;
    }

    public int getParity() {
        return parity;
    }

    public int getFlowControl() {
        return flowControl;
    }
}
