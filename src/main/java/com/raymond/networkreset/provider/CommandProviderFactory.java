/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.model.NetworkDevice;

/**
 *
 * @author rayk2
 */
public class CommandProviderFactory {
    
    public CommandProvider create(NetworkDevice device) {
        
        switch (device.getModel().getBrand()) {
            
            case CISCO:
                return new CiscoCommandProvider(device.getModel());
            
            case DELL:
                return new DellCommandProvider(device.getModel());
            
            case PALO_ALTO:
                return new PaloaltoCommandProvider(device.getModel());
                
            default:
                throw new IllegalArgumentException("Command provider not found");
        }
    }
}
