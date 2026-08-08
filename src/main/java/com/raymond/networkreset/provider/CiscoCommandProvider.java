/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.application.dto.FactoryResetCommand;
import com.raymond.networkreset.domain.command.CommandStep;
import com.raymond.networkreset.domain.command.expectation.ContainsTextExpectation;
import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.command.BreakSignalAction;
import com.raymond.networkreset.domain.command.SendCommandAction;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class CiscoCommandProvider implements CommandProvider {
    
    @Override
    public DeviceCommand factoryReset() {
        return new FactoryResetCommand(List.of(new CommandStep(new BreakSignalAction(), Duration.ofSeconds(10), new ContainsTextExpectation("Switch: "), 1),
                                               new CommandStep(new SendCommandAction("flash_init"), Duration.ofSeconds(6), new ContainsTextExpectation("Switch: "), 1),
                                               new CommandStep(new SendCommandAction("del flash:config.text"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:config.text' (y/n)?"), 1),
                                               new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:config.text' deleted"), 1),
                                               new CommandStep(new SendCommandAction("del flash:vlan.dat"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"), 1),
                                               new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:vlan.dat' deleted"), 1),
                                               new CommandStep(new SendCommandAction("boot"), Duration.ofSeconds(2), new ContainsTextExpectation("Would you like to enter to initial configuration dialog? [yes/no]: "), 1)
        ));
    }
    
    @Override
    public DeviceCommand showVersion() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
    
    @Override
    public DeviceCommand reload() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
}
