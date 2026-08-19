/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.exception.UnsupportedDeviceException;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class DellCommandProviderTest {
    
    @Test
    public void testCiscoCommandProviderSelectsSupportedModel() {
        
        DeviceModel dellS4000 = new DeviceModel(DeviceBrand.DELL, "S4000");
        
        CommandProvider dellProvider = new DellCommandProvider(dellS4000);
        
        DeviceCommand command = dellProvider.factoryReset();
        
        assertNotNull(command);
    }
    
    @Test
    public void testUnsupportedModelException() {
        DeviceModel dellE4000 = new DeviceModel(DeviceBrand.DELL, "E4000");
        
        CommandProvider dellProvider = new DellCommandProvider(dellE4000);
        
        assertThrows(UnsupportedDeviceException.class, () -> dellProvider.factoryReset());
    }
    
}
