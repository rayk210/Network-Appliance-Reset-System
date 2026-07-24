/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.model;

import com.raymond.networkreset.domain.valueobject.DeviceModel;

/**
 *
 * @author rayk2
 */
public abstract class NetworkDevice {
    
    protected final DeviceModel model;
    
    protected NetworkDevice(DeviceModel model) {
        this.model = model;
    }

    public DeviceModel getModel() {
        return model;
    }
}
