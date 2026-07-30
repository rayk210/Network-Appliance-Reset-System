/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.domain.model.CommandExecutor;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.domain.valueobject.Response;
import com.raymond.networkreset.factory.NetworkDeviceFactory;
import com.raymond.networkreset.parser.BannerParser;
import com.raymond.networkreset.parser.BannerParserResolver;
import java.io.IOException;

/**
 *
 * @author rayk2
 */
public class DeviceDetectorUseCase {
    private final BannerParserResolver resolver;
    private final NetworkDeviceFactory factory;

    public DeviceDetectorUseCase(BannerParserResolver resolver, NetworkDeviceFactory factory) {
        this.resolver = resolver;
        this.factory = factory;
    }
    
    public NetworkDevice create(CommandExecutor executor) throws IOException {
        
        executor.connect();
        
        try {
            Response banner = executor.receive();
        
            BannerParser parser = resolver.resolve(banner);
        
            DeviceModel model = parser.parse(banner);
        
            return factory.create(model); 
        }
        finally {
            executor.disconnect();
        } 
    }
}
