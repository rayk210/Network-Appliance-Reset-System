/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.domain.command;


import com.raymond.networkreset.application.dto.FactoryResetCommand;
import com.raymond.networkreset.domain.command.expectation.ContainsTextExpectation;
import com.raymond.networkreset.domain.exception.UnexpectedResponseException;
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
public class CommandSessionTest {
    
    @Test
    public void testActionAndResponse() throws IOException {
        
        CommandExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Switch: "),
                new Response("Switch: "),
                new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
                new Response("File 'flash:config.text' deleted"),
                new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
                new Response("File 'flash:vlan.dat' deleted"),
                new Response("Would you like to enter to initial configuration dialog? [yes/no]: "))
        );
        
        FactoryResetCommand factoryResetCmd = new FactoryResetCommand(List.of(new CommandStep(new BreakSignalAction(), Duration.ofSeconds(10), new ContainsTextExpectation("Switch: "), 1),
                new CommandStep(new SendCommandAction("flash_init"), Duration.ofSeconds(6), new ContainsTextExpectation("Switch: "), 1),
                new CommandStep(new SendCommandAction("del flash:config.text"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:config.text' (y/n)?"), 1),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:config.text' deleted"), 1),
                new CommandStep(new SendCommandAction("del flash:vlan.dat"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"), 1),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:vlan.dat' deleted"), 1),
                new CommandStep(new SendCommandAction("boot"), Duration.ofSeconds(2), new ContainsTextExpectation("Would you like to enter to initial configuration dialog? [yes/no]: "), 1)
        ));
        
        CommandSession session = new CommandSession(mockSerial);
        
        assertDoesNotThrow(() -> session.execute(factoryResetCmd));
    }
    
    @Test
    public void testRetryExhausted() {
        CommandExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Unexpected Response "),
                new Response("unexpected Response ")
        ));
        
        FactoryResetCommand factoryResetCmd = new FactoryResetCommand(List.of(new CommandStep(new BreakSignalAction(), 
                                              Duration.ofSeconds(10), new ContainsTextExpectation("Switch: "), 2)
        ));
        
        CommandSession session = new CommandSession(mockSerial);
        
        assertThrows(UnexpectedResponseException.class, () -> session.execute(factoryResetCmd));
    }
    
    @Test
    public void testContinueAfterSuccessfulRetry() {
        CommandExecutor mockSerial = new MockSerialExecutor(List.of(
                new Response("Unexpected Response "),
                new Response("unexpected Response "),
                new Response("Switch: "),
                new Response("Switch: "),
                new Response("Are you sure you want to delete 'flash:config.text' (y/n)?"),
                new Response("File 'flash:config.text' deleted"),
                new Response("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"),
                new Response("File 'flash:vlan.dat' deleted"),
                new Response("Would you like to enter to initial configuration dialog? [yes/no]: "))
        );
        
        FactoryResetCommand factoryResetCmd = new FactoryResetCommand(List.of(
                new CommandStep(new BreakSignalAction(), Duration.ofSeconds(10), new ContainsTextExpectation("Switch: "), 3),
                new CommandStep(new SendCommandAction("flash_init"), Duration.ofSeconds(6), new ContainsTextExpectation("Switch: "), 3),
                new CommandStep(new SendCommandAction("del flash:config.text"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:config.text' (y/n)?"), 3),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:config.text' deleted"), 3),
                new CommandStep(new SendCommandAction("del flash:vlan.dat"), Duration.ofSeconds(2), new ContainsTextExpectation("Are you sure you want to delete 'flash:vlan.dat' (y/n)?"), 3),
                new CommandStep(new SendCommandAction("y"), Duration.ofSeconds(3), new ContainsTextExpectation("File 'flash:vlan.dat' deleted"), 3),
                new CommandStep(new SendCommandAction("boot"), Duration.ofSeconds(2), new ContainsTextExpectation("Would you like to enter to initial configuration dialog? [yes/no]: "), 3)
        ));
        
        CommandSession session = new CommandSession(mockSerial);
        
        assertDoesNotThrow(() -> session.execute(factoryResetCmd));
    }
}
