/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.provider.CommandProvider;
import com.raymond.networkreset.domain.command.DeviceCommand;

/**
 *
 * @author rayk2
 */
public class DellCommandProvider implements CommandProvider {
    @Override
    public DeviceCommand factoryReset() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
    
    @Override
    public DeviceCommand showVersion() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
    
    @Override
    public DeviceCommand reload() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
}
