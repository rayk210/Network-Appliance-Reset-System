/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider.modelresolver.cisco;

import com.raymond.networkreset.application.dto.FactoryResetCommand;
import com.raymond.networkreset.domain.command.BreakSignalAction;
import com.raymond.networkreset.domain.command.CommandStep;
import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.command.SendCommandAction;
import com.raymond.networkreset.domain.command.expectation.ContainsTextExpectation;
import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.provider.modelresolver.ModelCommandResolver;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class CiscoCatalyst2960Resolver implements ModelCommandResolver {
    
    @Override
    public boolean supports(DeviceModel model) {
        return model.getName().equalsIgnoreCase("Catalyst 2960");
    }
    
    @Override
    public DeviceCommand createFactoryReset() {
        return new FactoryResetCommand(List.of(new CommandStep(new BreakSignalAction(), Duration.ofSeconds(10), new ContainsTextExpectation("Switch: "), 1),
                new CommandStep(new SendCommandAction("flash_init"), Duration.ofSeconds(6), new ContainsTextExpectation("Switch: "), 1),
                new CommandStep(new SendCommandAction("del flash:config.text"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:config.text' (y/n)?"), 1),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:config.text' deleted"), 1),
                new CommandStep(new SendCommandAction("del flash:vlan.dat"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"), 1),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:vlan.dat' deleted"), 1),
                new CommandStep(new SendCommandAction("boot"), Duration.ofSeconds(2), new ContainsTextExpectation("Would you like to enter to initial configuration dialog? [yes/no]: "), 1)
        ));
    }
}
