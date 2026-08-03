/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.command.expectation;

import com.raymond.networkreset.domain.command.ResponseExpectation;
import com.raymond.networkreset.domain.command.Response;

/**
 *
 * @author rayk2
 */
public final class ContainsTextExpectation implements ResponseExpectation {
    
    private final String expected;
    
    public ContainsTextExpectation(String expected) {
        this.expected = expected;
    }
    
    @Override
    public boolean matches(Response response) {
        if (response == null || response.getRawText() == null) {
            return false;
        }
        return response.getRawText().contains(expected);
    }
}
