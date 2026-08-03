/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.infrastructure.communication;

import com.raymond.networkreset.domain.command.CommandExecutor;
import java.io.IOException;

/**
 *
 * @author rayk2
 */
public final class ConnectionSession {
    private final CommandExecutor executor;
    
    public ConnectionSession(CommandExecutor executor) {
        this.executor = executor;
    }
    
    public void open() throws IOException {
        System.out.println("Successfully established connection");
        executor.connect();
    }
    
    public void close() throws IOException {
        System.out.println("Connection has been disconnected");
        executor.disconnect();
    }
}
