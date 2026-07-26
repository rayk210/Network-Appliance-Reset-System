/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.domain.model.CommandExecutor;
import com.raymond.networkreset.domain.model.DeviceCommand;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.factory.CommandProvider;
import com.raymond.networkreset.factory.CommandProviderFactory;
import java.io.IOException;

/**
 *
 * @author rayk2
 */
public class FactoryResetUseCase {
    
    private final CommandProviderFactory factory;
    
    public FactoryResetUseCase(CommandProviderFactory factory) {
        this.factory = factory;
    }
    
    public void execute(NetworkDevice device, CommandExecutor executor) throws IOException {
        
        CommandProvider provider = factory.create(device);
        DeviceCommand factoryResetCmd = provider.factoryReset();
        
        executor.connect();
        
        if (!executor.isConnected()) {
            throw new IOException("Failed to establish connection");
        }
        
        try {
            executor.execute(factoryResetCmd);
        }
        finally {
            executor.disconnect();
        }
    }   
}
