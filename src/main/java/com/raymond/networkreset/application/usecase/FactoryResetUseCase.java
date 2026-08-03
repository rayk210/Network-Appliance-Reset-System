/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.domain.command.CommandExecutor;
import com.raymond.networkreset.domain.command.CommandSession;
import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.provider.CommandProvider;
import com.raymond.networkreset.provider.CommandProviderFactory;
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
        
        CommandSession session = new CommandSession(executor);
        
        session.execute(factoryResetCmd);
    }   
}
