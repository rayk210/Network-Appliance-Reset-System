/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.domain.command.expectation;

import com.raymond.networkreset.domain.command.Response;
import com.raymond.networkreset.domain.command.ResponseExpectation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class ContainsTextExpectationTest {
    
    @Test
    public void testContainsTextExpectation() {
        
        Response response = new Response("Switch: ");
        
        ResponseExpectation expected = new ContainsTextExpectation("Switch: ");
        
        assertTrue(expected.matches(response));
    }
    
    @Test
    public void testDoesNotContainTextExpectation() {
        
        Response response = new Response("Router: ");
        
        ResponseExpectation expected = new ContainsTextExpectation("Switch: ");
        
        assertFalse(expected.matches(response));
    }
    
    @Test
    public void testContainsNullResponse() {
        
        ContainsTextExpectation expected = new ContainsTextExpectation("Switch: ");
        
        assertFalse(expected.matches(null));
    }
    
    @Test
    public void testContainsPartialText() {
        
        Response response = new Response("Cisco. Catalyst 9600");
        
        ContainsTextExpectation expected = new ContainsTextExpectation("Cisco");
        
        assertTrue(expected.matches(response));
    }
}
