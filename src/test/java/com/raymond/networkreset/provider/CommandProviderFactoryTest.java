/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.model.CiscoDevice;
import com.raymond.networkreset.domain.model.DellDevice;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.domain.model.PaloaltoDevice;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class CommandProviderFactoryTest {
    
    @Test
    public void testCommandProviderBasedOnDevice() throws IOException {
        NetworkDevice ciscoDevice = new CiscoDevice(new DeviceModel(DeviceBrand.CISCO, "Catalyst 9300"));
        NetworkDevice dellDevice = new DellDevice(new DeviceModel(DeviceBrand.DELL, "S3248T-ON"));
        NetworkDevice paloaltoDevice = new PaloaltoDevice(new DeviceModel(DeviceBrand.PALO_ALTO, "PA-3250"));
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        CommandProvider ciscoProvider = cmdFactory.create(ciscoDevice);
        CommandProvider dellProvider = cmdFactory.create(dellDevice);
        CommandProvider paloaltoProvider = cmdFactory.create(paloaltoDevice);
        
        assertAll(
                () -> assertInstanceOf(CiscoCommandProvider.class, ciscoProvider),
                () -> assertInstanceOf(DellCommandProvider.class, dellProvider),
                () -> assertInstanceOf(PaloaltoCommandProvider.class, paloaltoProvider)
        );
    }
}
