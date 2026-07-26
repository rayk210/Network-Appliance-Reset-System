/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.domain.exception;

import com.raymond.networkreset.domain.enums.ErrorCode;

/**
 *
 * @author rayk2
 */
public class UnsupportedDeviceException extends ApplicationException {
    
    public UnsupportedDeviceException() {
        super(ErrorCode.UNSUPPORTED_DEVICE);
    }
    
    public UnsupportedDeviceException(String dynamicMsg) {
        super(ErrorCode.UNSUPPORTED_DEVICE, dynamicMsg);
    }
    
    public UnsupportedDeviceException(Throwable cause) {
        super(ErrorCode.UNSUPPORTED_DEVICE, cause);
    }
}
