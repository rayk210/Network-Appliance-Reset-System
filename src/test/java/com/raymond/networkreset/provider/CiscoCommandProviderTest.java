/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class CiscoCommandProviderTest {
    
    @Test
    public void testCiscoCommandProviderSelectsSupportedModel() {
        
        DeviceModel ciscoCatalyst2960 = new DeviceModel(DeviceBrand.CISCO, "Catalyst 2960");
        
        CommandProvider ciscoProvider = new CiscoCommandProvider(ciscoCatalyst2960);
        
        DeviceCommand command = ciscoProvider.factoryReset();
        
        assertNotNull(command);
    }
    
    
}
