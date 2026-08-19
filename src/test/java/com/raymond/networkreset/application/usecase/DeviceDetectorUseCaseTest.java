/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.application.usecase;

import com.raymond.networkreset.detector.BannerParserResolver;
import com.raymond.networkreset.detector.NetworkDeviceFactory;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.exception.UnsupportedDeviceException;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.infrastructure.serial.MockSerialExecutor;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class DeviceDetectorUseCaseTest {
    
    @Test
    public void testDetectDevice() throws IOException {
        
        BannerParserResolver resolver = new BannerParserResolver();
    
        NetworkDeviceFactory devFactory = new NetworkDeviceFactory();
        
        MockSerialExecutor mockCom1 = new MockSerialExecutor(List.of(
                                        new Response("Cisco Catalyst 2960")));
        
        MockSerialExecutor mockCom2 = new MockSerialExecutor(List.of(
                                        new Response("Dell S4000")));
        
        MockSerialExecutor mockCom3 = new MockSerialExecutor(List.of(
                                        new Response("Paloalto PA-3250")));
        
        DeviceDetectorUseCase devUseCase = new DeviceDetectorUseCase(resolver, devFactory);
        
        NetworkDevice ciscoDev = devUseCase.create(mockCom1);
        
        NetworkDevice dellDev = devUseCase.create(mockCom2);
        
        NetworkDevice paloaltoDev = devUseCase.create(mockCom3);
        
        assertAll(
                () -> assertEquals(DeviceBrand.CISCO, ciscoDev.getModel().getBrand()),
                () -> assertEquals(DeviceBrand.DELL, dellDev.getModel().getBrand()),
                () -> assertEquals(DeviceBrand.PALO_ALTO, paloaltoDev.getModel().getBrand())
        );
    }
    
    @Test
    public void testBannerEmptyException() {
        
        BannerParserResolver resolver = new BannerParserResolver();
        
        NetworkDeviceFactory devFactory = new NetworkDeviceFactory();
        
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of());
        
        DeviceDetectorUseCase devUseCase = new DeviceDetectorUseCase(resolver, devFactory);
        
        assertThrows(IOException.class, () -> devUseCase.create(mockSerial));
    }
    
    @Test
    public void testUnsupportedDeviceException() throws IOException {
        BannerParserResolver resolver = new BannerParserResolver();
        
        NetworkDeviceFactory devFactory = new NetworkDeviceFactory();
        
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(new Response("Cisco Catalyst 6500")));
        
        DeviceDetectorUseCase devUseCase = new DeviceDetectorUseCase(resolver, devFactory);
        
        assertThrows(UnsupportedDeviceException.class, () -> devUseCase.create(mockSerial));
    }
}
