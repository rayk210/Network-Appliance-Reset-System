/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package integration;

import com.raymond.networkreset.application.usecase.DeviceDetectorUseCase;
import com.raymond.networkreset.application.usecase.FactoryResetUseCase;
import com.raymond.networkreset.detector.BannerParserResolver;
import com.raymond.networkreset.detector.NetworkDeviceFactory;
import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.enums.DeviceBrand;
import com.raymond.networkreset.domain.model.NetworkDevice;
import com.raymond.networkreset.infrastructure.communication.ConnectionSession;
import com.raymond.networkreset.infrastructure.serial.MockSerialExecutor;
import com.raymond.networkreset.provider.CiscoCommandProvider;
import com.raymond.networkreset.provider.CommandProviderFactory;
import com.raymond.networkreset.provider.DellCommandProvider;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class FactoryResetIntegrationTest {
    
    @Test
    public void ciscoCatalyst2960FactoryReset() throws IOException {
        
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(new Response("Cisco Catalyst 2960"),
            new Response("Switch: "),
            new Response("Switch: "),
            new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
            new Response("File 'flash:config.text' deleted"),
            new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
            new Response("File 'flash:vlan.dat' deleted"),
            new Response("Would you like to enter to initial configuration dialog? [yes/no]: ")));
        
        ConnectionSession session = new ConnectionSession(mockSerial);
        
        session.open();
        
        BannerParserResolver resolver = new BannerParserResolver();
        NetworkDeviceFactory factory = new NetworkDeviceFactory();
        
        DeviceDetectorUseCase deviceUseCase = new DeviceDetectorUseCase(resolver, factory);
        
        NetworkDevice ciscoCatalyst2960 = deviceUseCase.create(mockSerial);
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        FactoryResetUseCase factoryReset = new FactoryResetUseCase(cmdFactory);
        
        assertDoesNotThrow(() -> factoryReset.execute(ciscoCatalyst2960, mockSerial));
        
        assertAll(
                () -> assertInstanceOf(CiscoCommandProvider.class, cmdFactory.create(ciscoCatalyst2960)),
                () -> assertEquals(DeviceBrand.CISCO, ciscoCatalyst2960.getModel().getBrand()),
                () -> assertEquals("Catalyst 2960", ciscoCatalyst2960.getModel().getName()));
        
        session.close();
    }
    
    @Test
    public void testCiscoCatalyst2960CmdSequence() throws IOException {
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(new Response("Cisco Catalyst 2960"),
            new Response("Switch: "),
            new Response("Switch: "),
            new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
            new Response("File 'flash:config.text' deleted"),
            new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
            new Response("File 'flash:vlan.dat' deleted"),
            new Response("Would you like to enter to initial configuration dialog? [yes/no]: ")));
        
        ConnectionSession session = new ConnectionSession(mockSerial);
        
        session.open();
        
        BannerParserResolver resolver = new BannerParserResolver();
        NetworkDeviceFactory factory = new NetworkDeviceFactory();
        
        DeviceDetectorUseCase deviceUseCase = new DeviceDetectorUseCase(resolver, factory);
        
        NetworkDevice ciscoCatalyst2960 = deviceUseCase.create(mockSerial);
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        FactoryResetUseCase factoryReset = new FactoryResetUseCase(cmdFactory);
        
        factoryReset.execute(ciscoCatalyst2960, mockSerial);
        
        List<String> sentCommands = mockSerial.getSentCommand();
        
        assertEquals(7, sentCommands.size());
        
        assertEquals(List.of(
                        "Break Signal Issued...",
                        "flash_init",
                        "del flash:config.text",
                        "y",
                        "del flash:vlan.dat",
                        "y",
                        "boot"), sentCommands);
    }
    
    @Test
    public void dellS4000FactoryReset() throws IOException {
        
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Dell (EMC) S4000"),
                new Response("S4000 boot flash label"),
                new Response("BOOT_USER#"),
                new Response("DellEMC>"),
                new Response("DellEMC#"),
                new Response("Warning"),
                new Response("Success")
        ));
        
        ConnectionSession session = new ConnectionSession(mockSerial);
        
        session.open();
        
        BannerParserResolver resolver = new BannerParserResolver();
        NetworkDeviceFactory factory = new NetworkDeviceFactory();
        
        DeviceDetectorUseCase deviceUseCase = new DeviceDetectorUseCase(resolver, factory);
        
        NetworkDevice dellS4000 = deviceUseCase.create(mockSerial);
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        FactoryResetUseCase factoryReset = new FactoryResetUseCase(cmdFactory);
        
        assertDoesNotThrow(() -> factoryReset.execute(dellS4000, mockSerial));
        
        assertAll(
                () -> assertInstanceOf(DellCommandProvider.class, cmdFactory.create(dellS4000)),
                () -> assertEquals(DeviceBrand.DELL, dellS4000.getModel().getBrand()),
                () -> assertEquals("S4000", dellS4000.getModel().getName()));
        
        session.close();
    }
    
    @Test
    public void testDellS4000CmdSequence() throws IOException {
        MockSerialExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Dell (EMC) S4000"),
                new Response("S4000 boot flash label"),
                new Response("BOOT_USER#"),
                new Response("DellEMC>"),
                new Response("DellEMC#"),
                new Response("Warning"),
                new Response("Success")
        ));
        
        ConnectionSession session = new ConnectionSession(mockSerial);
        
        session.open();
        
        BannerParserResolver resolver = new BannerParserResolver();
        NetworkDeviceFactory factory = new NetworkDeviceFactory();
        
        DeviceDetectorUseCase deviceUseCase = new DeviceDetectorUseCase(resolver, factory);
        
        NetworkDevice dellS4000 = deviceUseCase.create(mockSerial);
        
        CommandProviderFactory cmdFactory = new CommandProviderFactory();
        
        FactoryResetUseCase factoryReset = new FactoryResetUseCase(cmdFactory);
        
        factoryReset.execute(dellS4000, mockSerial);
        
        List<String> sentCommands = mockSerial.getSentCommand();
        
        assertEquals(8, sentCommands.size());
        
        assertEquals(List.of(
                    "ESC",
                    "DOWN",
                    "ENTER",
                    "ignore startup-config",
                    "reload",
                    "en",
                    "restore factory-defaults stack-unit all clear-all",
                    "yes"), sentCommands);
    }
}
