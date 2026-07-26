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
public class ApplicationException extends IllegalArgumentException {
    
    private final ErrorCode code;
    
    protected ApplicationException(ErrorCode code) {
        super(code.getErrorMsg());
        this.code = code;
    }
    
    protected ApplicationException(ErrorCode code, String dynamicMsg) {
        super(dynamicMsg);
        this.code = code;
    }
    
    protected ApplicationException(ErrorCode code, Throwable cause) {
        super(code.getErrorMsg(), cause);
        this.code = code;
    }
    
    public ErrorCode getCode() {
        return code;
    }
}
