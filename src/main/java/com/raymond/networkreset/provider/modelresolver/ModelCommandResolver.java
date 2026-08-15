/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.provider.modelresolver;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.valueobject.DeviceModel;

/**
 *
 * @author rayk2
 */
public interface ModelCommandResolver {
    boolean supports(DeviceModel model);
    
    DeviceCommand createFactoryReset();
}
