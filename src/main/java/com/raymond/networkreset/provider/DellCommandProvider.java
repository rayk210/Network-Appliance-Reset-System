/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.provider;

import com.raymond.networkreset.application.dto.FactoryResetCommand;
import com.raymond.networkreset.domain.command.CommandStep;
import com.raymond.networkreset.domain.command.DeviceCommand;
import com.raymond.networkreset.domain.command.KeySignalAction;
import com.raymond.networkreset.domain.command.SendCommandAction;
import com.raymond.networkreset.domain.command.expectation.ContainsTextExpectation;
import com.raymond.networkreset.domain.enums.KeySignal;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class DellCommandProvider implements CommandProvider {
    @Override
    public DeviceCommand factoryReset() {
        return new FactoryResetCommand(List.of(new CommandStep(new KeySignalAction(KeySignal.ESC), Duration.ofSeconds(10), new ContainsTextExpectation("S4000 boot flash label"), 2),
                                               new CommandStep(new KeySignalAction(KeySignal.DOWN), Duration.ofSeconds(6), new ContainsTextExpectation(""), 2),
                                               new CommandStep(new KeySignalAction(KeySignal.ENTER), Duration.ofSeconds(2), new ContainsTextExpectation("BOOT_USER#"), 2),
                                               new CommandStep(new SendCommandAction("ignore starup-config"), Duration.ofSeconds(2), new ContainsTextExpectation(""), 2),
                                               new CommandStep(new SendCommandAction("reload"), Duration.ofSeconds(20), new ContainsTextExpectation("DellEMC>"), 2),
                                               new CommandStep(new SendCommandAction("en"), Duration.ofSeconds(3), new ContainsTextExpectation("DellEMC#"), 2),
                                               new CommandStep(new SendCommandAction("restore factory-defaults stack-unit all clear-all"), Duration.ofSeconds(2), new ContainsTextExpectation("Warning"), 2),
                                               new CommandStep(new SendCommandAction("yes"), Duration.ofSeconds(2), new ContainsTextExpectation("Success"), 2)));
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
