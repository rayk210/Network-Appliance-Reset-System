/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.model.CiscoDevice;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.infrastructure.serial.MockSerialExecutor;
import com.raymond.networkreset.provider.CommandProviderFactory;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class FactoryResetUseCaseTest {
    
    @Test
    public void testExecutefactoryReset() throws IOException {
        
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Switch: "),
                new Response("Switch: "),
                new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
                new Response("File 'flash:config.text' deleted"),
                new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
                new Response("File 'flash:vlan.dat' deleted"),
                new Response("Would you like to enter to initial configuration dialog? [yes/no]: "))
        );
        
        NetworkDevice ciscoDevice = new CiscoDevice(new DeviceModel(DeviceBrand.CISCO, "Catalyst 2960"));
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        FactoryResetUseCase factoryResetUse = new FactoryResetUseCase(cmdFactory);
        
        assertDoesNotThrow(() -> factoryResetUse.execute(ciscoDevice, mockSerial));
    }
}
