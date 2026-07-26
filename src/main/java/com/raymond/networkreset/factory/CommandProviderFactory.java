/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.factory;

import com.raymond.networkreset.domain.model.NetworkDevice;

/**
 *
 * @author rayk2
 */
public class CommandProviderFactory {
    
    public CommandProvider create(NetworkDevice device) {
        
        switch (device.getModel().getBrand()) {
            
            case CISCO:
                return new CiscoCommandProvider();
            
            case DELL:
                return new DellCommandProvider();
            
            case PALO_ALTO:
                return new PaloaltoCommandProvider();
                
            default:
                throw new IllegalArgumentException("Command provider not found");
        }
    }
}
