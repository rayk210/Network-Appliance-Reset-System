/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import java.io.IOException;

/**
 *
 * @author rayk2
 */
public class BreakSignalAction implements CommandAction {
    
    @Override
    public void execute(CommandExecutor executor) throws IOException {
        executor.sendBreak();
    }
    
    @Override
    public String toString() {
        return "Break Signal Issued...";
    }
}
