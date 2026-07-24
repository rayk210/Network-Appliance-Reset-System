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
public class CiscoBannerParser implements BannerParser {
    
    @Override
    public boolean canParse(String banner) {
        return banner != null && banner.toLowerCase().contains("cisco");
    }
    
    @Override
    public DeviceModel parse(String banner) {
        
        String lowerBanner = banner.toLowerCase();
        
        if (lowerBanner.contains("catalyst 9300")) {
            return new DeviceModel(DeviceBrand.CISCO, "catalyst 9300");
        }
        
        if (lowerBanner.contains("catalyst 9600")) {
            return new DeviceModel(DeviceBrand.CISCO, "catalyst 9600");
        }
        throw new IllegalArgumentException("Model not found");
    }
}
