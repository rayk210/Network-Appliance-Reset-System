/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider.modelresolver.paloalto;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.provider.modelresolver.ModelCommandResolver;

/**
 *
 * @author rayk2
 */
public class PaloaltoPA3250Resolver implements ModelCommandResolver {
    
    @Override
    public boolean supports(DeviceModel model) {
        return model.getName().equalsIgnoreCase("PA-3250");
    }
    
    @Override
    public DeviceCommand createFactoryReset() {
        throw new UnsupportedOperationException("Factory reset sequence not yet implemented");
    }
}
