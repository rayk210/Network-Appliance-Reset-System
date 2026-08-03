/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.command.DeviceCommand;

/**
 *
 * @author rayk2
 */
public interface CommandProvider {
    
    DeviceCommand factoryReset();
    
    DeviceCommand showVersion();
    
    DeviceCommand reload();
    
}
