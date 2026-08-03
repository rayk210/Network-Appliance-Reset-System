/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import com.raymond.networkreset.domain.command.CommandStep;
import java.util.List;

/**
 *
 * @author rayk2
 */
public interface DeviceCommand {
    List<CommandStep> commands();
}
