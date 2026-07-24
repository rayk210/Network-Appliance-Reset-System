/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.parser;

import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.valueobject.DeviceModel;

/**
 *
 * @author rayk2
 */
public class PaloaltoBannerParser implements BannerParser{
    
    @Override
    public boolean canParse(String banner) {
        return banner != null && banner.toLowerCase().contains("paloalto");
    }
    
    @Override
    public DeviceModel parse(String banner) {
        
        String lowerBanner = banner.toLowerCase();
        
        if (lowerBanner.contains("pa-3250")) {
            return new DeviceModel(DeviceBrand.PALO_ALTO, "pa-3250");
        }
        throw new IllegalArgumentException("Model not found");
    }
}
