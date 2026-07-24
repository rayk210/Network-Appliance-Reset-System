/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.raymond.networkreset.domain.valueobject;

import com.raymond.networkreset.domain.enums.DeviceBrand;

/**
 *
 * @author rayk2
 */
public final class DeviceModel {
    
    private final DeviceBrand brand;
    private final String name;
    
    public DeviceModel(DeviceBrand brand, String name) {
        
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Model cannot be blank");
        }
        
        this.brand = brand;
        this.name = name;
    }

    public DeviceBrand getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return brand + " " + name;
    }
}
