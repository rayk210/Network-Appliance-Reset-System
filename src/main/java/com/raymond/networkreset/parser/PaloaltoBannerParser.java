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
public class PaloaltoBannerParser implements BannerParser{
    
    private static final Map<String, String> MODELS = Map.of(
            "pa-3250", "PA-3250"
    );
    
    @Override
    public boolean canParse(String banner) {
        return banner != null && banner.toLowerCase().contains("paloalto");
    }
    
    @Override
    public DeviceModel parse(String banner) {
        
        String lowerBanner = banner.toLowerCase();
        
        for(Map.Entry<String, String> entry : MODELS.entrySet()) {
            if (lowerBanner.contains(entry.getKey())) {
                return new DeviceModel(DeviceBrand.PALO_ALTO, entry.getValue());
            }
        }
        throw new UnsupportedDeviceException("Cannot find Paloalto device model");
    }
}
