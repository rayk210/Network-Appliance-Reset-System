/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.detector;

import com.raymond.networkreset.domain.model.CiscoDevice;
import com.raymond.networkreset.domain.model.DellDevice;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.domain.model.PaloaltoDevice;
import com.raymond.networkreset.domain.valueobject.DeviceModel;

/**
 *
 * @author rayk2
 */
public class NetworkDeviceFactory {
    
    public NetworkDevice create(DeviceModel model) {
        
        if (model == null) {
            throw new IllegalArgumentException("Device model cannot be null");
        }
        
        switch(model.getBrand()) {
            
            case CISCO:
                return new CiscoDevice(model);
            
            case DELL:
                return new DellDevice(model);
                
            case PALO_ALTO:
                return new PaloaltoDevice(model);
                
            default:
                throw new IllegalArgumentException("Cannot find suitable network device from model");
        }
    }
}
