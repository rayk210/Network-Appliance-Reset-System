/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raymond.networkreset.infrastructure.serial;

import com.raymond.networkreset.domain.command.Response;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rayk2
 */
public class SerialCommandExecutorTest {
    
    @Test
    public void testSendCommand() throws IOException {
        
        ByteArrayInputStream input = new ByteArrayInputStream(new byte[0]);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        SerialCommandExecutor executor = new SerialCommandExecutor(null, input, output);
        
        executor.sendCommand("flash_init");
        
        String result = output.toString(StandardCharsets.UTF_8);
        
        assertEquals("flash_init\r\n", result);
    }
    
    @Test
    public void testReceive() throws IOException {
        
        String simulatedResponse = "Switch#";
        
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedResponse.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        SerialCommandExecutor executor = new SerialCommandExecutor(null, input, output);
        
        Response response = executor.receive(Duration.ofMillis(100));
        
        assertEquals("Switch#", response.getRawText());
    }
}
