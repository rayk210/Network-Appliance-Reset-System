/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import com.raymond.networkreset.domain.enums.KeySignal;
import java.io.IOException;

/**
 *
 * @author rayk2
 */
public class KeySignalAction implements CommandAction {
    
    private final KeySignal keySequence;
    
    public KeySignalAction(KeySignal keySequence) {
        if (keySequence == null) {
            throw new IllegalArgumentException("Key cannot be blank");
        }
        
        this.keySequence = keySequence;
    }
    
    @Override
    public void execute(CommandExecutor executor) throws IOException {
        executor.sendKey(keySequence);
    }
    
    @Override
    public String toString() {
        return keySequence.name();
    }
}
