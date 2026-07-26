/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.parser;

import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.exception.UnsupportedDeviceException;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import java.util.Map;

/**
 *
 * @author rayk2
 */
public class CiscoBannerParser implements BannerParser {
    
    private static final Map<String, String> MODELS = Map.of(
            "catalyst 9300", "Catalyst 9300",
            "catalyst 9600", "Catalyst 9600"
    );
    
    @Override
    public boolean canParse(String banner) {
        return banner != null && banner.toLowerCase().contains("cisco");
    }
    
    @Override
    public DeviceModel parse(String banner) {
        
        String lowerBanner = banner.toLowerCase();
        
        for (Map.Entry<String, String> entry : MODELS.entrySet()) {
            if (lowerBanner.contains(entry.getKey())) {
                return new DeviceModel(DeviceBrand.CISCO, entry.getValue());
            }
        }
        throw new UnsupportedDeviceException("Cannot find Cisco device model");
    }
}
