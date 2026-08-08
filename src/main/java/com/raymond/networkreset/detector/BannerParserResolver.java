/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raymond.networkreset.detector;

import com.raymond.networkreset.domain.command.Response;
import java.util.List;

/**
 *
 * @author rayk2
 */
public class BannerParserResolver {
    
    private static final List<BannerParser> PARSERS = List.of(
        new CiscoBannerParser(),
        new DellBannerParser(),
        new PaloaltoBannerParser()
    );
     
    public BannerParser resolve(Response banner) {
        
        if (banner == null || banner.getRawText().trim().isEmpty()) {
            throw new IllegalArgumentException("Banner cannot be empty");
        }
        
        for (BannerParser parser : PARSERS) {
            if (parser.canParse(banner)) {
                return parser;
            }
        }
        throw new IllegalArgumentException("Cannot resolve parser type");
    }  
}
