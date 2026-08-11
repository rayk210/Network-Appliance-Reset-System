/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.raymond.networkreset.domain.enums;

/**
 *
 * @author rayk2
 */
public enum KeySignal {
    ESC(new byte[] {27}),
    UP(new byte[] {27, 91, 65}),
    DOWN(new byte[] {27, 91, 66}),
    ENTER(new byte[] {13, 10});
    
    private final byte[] keySequence;
    
    KeySignal(byte[] keySequence) {
        this.keySequence = keySequence;
    }
    
    public byte[] getKeySequence() {
        return keySequence.clone();
    }
}
