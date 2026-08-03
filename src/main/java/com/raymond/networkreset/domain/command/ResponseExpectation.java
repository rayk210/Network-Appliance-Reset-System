/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.domain.command;

/**
 *
 * @author rayk2
 */
public interface ResponseExpectation {
    boolean matches(Response response);
}
