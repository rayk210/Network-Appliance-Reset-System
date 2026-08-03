/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command;

/**
 *
 * @author rayk2
 */
public final class Response {
    private final String rawText;
    
    public Response(String rawText) {
        this.rawText = rawText;
    }
    
    public String getRawText() {
        return rawText;
    }
    
    public boolean containsIgnoreCase(String text) {
        return rawText.toLowerCase().contains(text.toLowerCase());
    }
    
    public boolean contains(String text) {
        return rawText.contains(text);
    }
}
