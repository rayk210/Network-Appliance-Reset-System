/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.raymond.networkreset.domain.enums;

/**
 *
 * @author rayk2
 */
public enum ErrorCode {
    UNSUPPORTED_DEVICE(1001, "Cannot find suitable network device from model");
    
    private final int errorCode;
    private final String errorMsg;
    
    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }
}
