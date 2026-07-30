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
public class UnexpectedResponseException extends ApplicationException {
    
    public UnexpectedResponseException() {
        super(ErrorCode.UNEXPECTED_RESPONSE);
    }
    
    public UnexpectedResponseException(String dynamicMsg) {
        super(ErrorCode.UNEXPECTED_RESPONSE, dynamicMsg);
    }
    
    public UnexpectedResponseException(Throwable cause) {
        super(ErrorCode.UNEXPECTED_RESPONSE, cause);
    }
}
