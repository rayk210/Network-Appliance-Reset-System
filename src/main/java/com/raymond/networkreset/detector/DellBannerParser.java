/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.detector;

import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.exception.UnsupportedDeviceException;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.domain.command.Response;
import java.util.Map;

/**
 *
 * @author rayk2
 */
public class DellBannerParser implements BannerParser {
    
    private static final Map<String, String> MODELS = Map.of(
            "s3248t-on", "S3248T-ON",
            "s5248f-on", "S5248F-ON"
    );
    
    @Override
    public boolean canParse(Response banner) {
        return banner != null && banner.containsIgnoreCase("dell");
    }
    
    @Override
    public DeviceModel parse(Response banner) {
        
        String lowerBanner = banner.getRawText().toLowerCase();
        
        for (Map.Entry<String, String> entry : MODELS.entrySet()) {
            if (lowerBanner.contains(entry.getKey())) {
                return new DeviceModel(DeviceBrand.DELL, entry.getValue());
            }
        }
        throw new UnsupportedDeviceException("Cannot find Dell device model");
    }
}
