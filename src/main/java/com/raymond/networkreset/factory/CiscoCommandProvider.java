/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.factory;

import com.raymond.networkreset.domain.model.DeviceCommand;

/**
 *
 * @author rayk2
 */
public class CiscoCommandProvider implements CommandProvider {
    
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
