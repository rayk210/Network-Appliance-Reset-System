/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.application.dto;

import com.raymond.networkreset.domain.command.CommandStep;
import com.raymond.networkreset.domain.command.DeviceCommand;
import java.util.List;

/**
 *
 * @author rayk2
 */
public final class FactoryResetCommand implements DeviceCommand {
    
    private final List<CommandStep> commands;
    
    public FactoryResetCommand(List<CommandStep> commands) {
        this.commands = List.copyOf(commands);
    }
    
    @Override
    public List<CommandStep> commands() {
        return commands;
    }
}
