/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset;

import com.raymond.networkreset.application.usecase.DeviceDetectorUseCase;
import com.raymond.networkreset.application.usecase.FactoryResetUseCase;
import com.raymond.networkreset.detector.BannerParserResolver;
import com.raymond.networkreset.detector.NetworkDeviceFactory;
import com.raymond.networkreset.domain.command.CommandExecutor;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.infrastructure.communication.ConnectionSession;
import com.raymond.networkreset.infrastructure.serial.MockSerialExecutor;
import com.raymond.networkreset.provider.CommandProviderFactory;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class Application {
    
    public static void main(String[] args) {
        System.out.println("======= Network Appliance Reset System ========");
        CommandExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Cisco Catalyst 2960"),
                new Response("Switch: "),
                new Response("Switch: "),
                new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
                new Response("File 'flash:config.text' deleted"),
                new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
                new Response("File 'flash:vlan.dat' deleted"),
                new Response("Would you like to enter to initial configuration dialog? [yes/no]: "))
        );
        
        ConnectionSession session = new ConnectionSession(mockSerial);
        try {
            session.open();
       
            System.out.println("Successfully connected to COM3");
    
            System.out.println("Reading banner...\n");
            // instantiate banner resolver/network device detector
            BannerParserResolver resolver = new BannerParserResolver();
            NetworkDeviceFactory devFactory = new NetworkDeviceFactory();
    
            DeviceDetectorUseCase deviceUseCase = new DeviceDetectorUseCase(resolver, devFactory);
            
        
            NetworkDevice device = deviceUseCase.create(mockSerial);
            
            CommandProviderFactory cmdFactory = new CommandProviderFactory();
            
            FactoryResetUseCase factoryUseCase = new FactoryResetUseCase(cmdFactory);
            
            factoryUseCase.execute(device, mockSerial);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                session.close();
            }
            catch (IOException close) {
                close.printStackTrace();
            }
        }
    }
}
