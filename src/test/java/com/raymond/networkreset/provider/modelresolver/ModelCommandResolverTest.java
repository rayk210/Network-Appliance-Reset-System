/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.provider.modelresolver;

import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.provider.modelresolver.cisco.CiscoCatalyst2960Resolver;
import com.raymond.networkreset.provider.modelresolver.dell.DellS4000Resolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class ModelCommandResolverTest {
    
    @Test
    public void testModelResolverSupportMethod() {
        
        DeviceModel ciscoCatalyst2960 = new DeviceModel(DeviceBrand.CISCO, "Catalyst 2960");
        
        DeviceModel dellS4000 = new DeviceModel(DeviceBrand.DELL, "S4000");
        
        ModelCommandResolver cisco2960Resolver = new CiscoCatalyst2960Resolver();
        
        ModelCommandResolver dellS4000Resolver = new DellS4000Resolver();
        
        assertAll( 
            () -> assertTrue(cisco2960Resolver.supports(ciscoCatalyst2960)),
            () -> assertTrue(dellS4000Resolver.supports(dellS4000))
        );
    }
    
    @Test
    public void testFactoryResetUnsupported() {
        DeviceModel ciscoCatalyst3650 = new DeviceModel(DeviceBrand.CISCO, "Catalyst 3650");
        DeviceModel dellE3200 = new DeviceModel(DeviceBrand.DELL, "Dell E3200");
        
        ModelCommandResolver ciscoCatalyst2960 = new CiscoCatalyst2960Resolver();
        ModelCommandResolver dellS4000 = new DellS4000Resolver();
        
        assertFalse(ciscoCatalyst2960.supports(ciscoCatalyst3650));
        assertFalse(dellS4000.supports(dellE3200));
    }
    
    @Test
    public void testCreateFactoryReset() {
        ModelCommandResolver cisco2960Resolver = new CiscoCatalyst2960Resolver(); 
        
        ModelCommandResolver dellS4000Resolver = new DellS4000Resolver(); 
        
        assertAll( 
              () -> assertInstanceOf(DeviceCommand.class, cisco2960Resolver.createFactoryReset()), 
              () -> assertInstanceOf(DeviceCommand.class, dellS4000Resolver.createFactoryReset()));
    }
}
