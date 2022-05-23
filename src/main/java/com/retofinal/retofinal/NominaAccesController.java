/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("/xmlfile")
public class NominaAccesController {
    
    @Autowired
    NominaAccesService service;
    
    @PostMapping("/read")
    public String readXML(HttpServletRequest request)throws IOException, ParserConfigurationException, SAXException {
        return service.getXML(request);
    
    }
    
}
