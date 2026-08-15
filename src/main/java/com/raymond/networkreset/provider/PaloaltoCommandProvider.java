/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.exception.UnsupportedDeviceException;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.provider.modelresolver.ModelCommandResolver;
import com.raymond.networkreset.provider.modelresolver.paloalto.PaloaltoPA3250Resolver;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class PaloaltoCommandProvider implements CommandProvider {
    
    private final DeviceModel model;
    private final List<ModelCommandResolver> resolvers;
    
    public PaloaltoCommandProvider(DeviceModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Device model cannot be null");
        }
        this.model = model;
        
        this.resolvers = List.of(
                new PaloaltoPA3250Resolver()
        );
    }
    
    @Override
    public DeviceCommand factoryReset() {
        for (ModelCommandResolver resolver : resolvers) {
            if (resolver.supports(model)) {
                return resolver.createFactoryReset();
            }
        }
        throw new UnsupportedDeviceException("Factory reset is unsupported for " + model.getName());
    }
    
    @Override
    public DeviceCommand showVersion() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
    
    @Override
    public DeviceCommand reload() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
}
