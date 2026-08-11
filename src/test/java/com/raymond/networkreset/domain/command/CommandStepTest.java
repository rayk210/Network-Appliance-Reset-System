/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.domain.command;

import com.raymond.networkreset.domain.command.expectation.ContainsTextExpectation;
import com.raymond.networkreset.infrastructure.serial.MockSerialExecutor;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class CommandStepTest {
    
    @Test
    public void testCommandStepFields() {
        
        CommandAction action = new BreakSignalAction();
        
        ContainsTextExpectation expected = new ContainsTextExpectation("Switch: ");
        
        CommandStep step = new CommandStep(action,Duration.ofMillis(500), expected, 2);
        
        assertAll(
                () -> assertEquals(action, step.getAction()),
                () -> assertEquals(Duration.ofMillis(500), step.getTimeout()),
                () -> assertEquals(expected, step.getExpectation()),
                () -> assertEquals(2, step.getMaxRetries())
        );
    }
    
    @Test
    public void testNullCommandStepFields() {
        
        ResponseExpectation expected = new ContainsTextExpectation("Switch: ");
        
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new CommandStep(new SendCommandAction(null), Duration.ofMillis(500), expected, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> new CommandStep(new BreakSignalAction(), Duration.ofMillis(100), new ContainsTextExpectation(null), 2)),
                () -> assertThrows(IllegalArgumentException.class, () -> new CommandStep(new SendCommandAction("y"), Duration.ofMillis(200), new ContainsTextExpectation("File: vlan.dat deleted"), 0)
        ));
    }
    
    @Test
    public void testCommandAction() throws IOException {
        // objective is to ensure execute method from command action is calling sendCommand in Mock Serial executor
        
        MockSerialExecutor executor = new MockSerialExecutor(List.of());
        CommandAction action = new SendCommandAction("del flash:config.text");
        
        action.execute(executor);
        
        assertEquals("del flash:config.text", executor.getSentCommand().get(0));
    }
}
