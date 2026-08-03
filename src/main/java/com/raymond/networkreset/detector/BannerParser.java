/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.raymond.networkreset.detector;

import com.raymond.networkreset.domain.valueobject.DeviceModel;
import com.raymond.networkreset.domain.command.Response;

/**
 *
 * @author rayk2
 */
public interface BannerParser {
    
    boolean canParse(Response banner);
    
    DeviceModel parse(Response banner);
}
