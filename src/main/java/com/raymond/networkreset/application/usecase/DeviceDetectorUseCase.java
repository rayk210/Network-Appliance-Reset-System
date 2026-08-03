/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.domain.command.CommandExecutor;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.detector.NetworkDeviceFactory;
import com.raymond.networkreset.detector.BannerParser;
import com.raymond.networkreset.detector.BannerParserResolver;
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
        
        Response banner = executor.receive();
        System.out.println("Banner: " + banner.getRawText());
        
        BannerParser parser = resolver.resolve(banner);
        
        DeviceModel model = parser.parse(banner);
        System.out.println(model.getBrand() + " " + model.getName() + " detected");
        
        return factory.create(model); 
    }
}
