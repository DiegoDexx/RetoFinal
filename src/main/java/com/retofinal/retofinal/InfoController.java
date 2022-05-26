/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@RestController()
@RequestMapping("/info")
public class InfoController {
    
    @Autowired
    EmpresaService servicee;    
    
     @PostMapping("/createEmpresa")
    public ResponseEntity<String> createEmpresa(String nombreEmpresa, String domicilio, String convenio, String cif, String ccc) { 
    
        servicee.EmpresaData(nombreEmpresa, domicilio, convenio, cif, ccc);
        
        return ResponseEntity.ok().build();
    
    }
    
       @GetMapping("/empresa")
    public ResponseEntity<String> listaInfo() {
    
        List<Empresa> listaInfo = servicee.getInfo();
        String inf=" ";
        
        for (Empresa e: listaInfo) { 
        inf += e.getnombreempresa()+ "\n";
        inf += e.getDomicilio()+ "\n";
        inf += e.getConvenio()+"\n";
        inf += e.getCif()+"\n";
        inf += e.getCcc()+"\n";
        }
        
      
    
    return ResponseEntity.ok(inf);
    }
    
    @Autowired
            
    EmpleadoService service;
    @PostMapping("/createEmpleado")
    public ResponseEntity<String> createEmpleado(String nombre, String apellido1, String apellido2, String nif, String nus, String tipo) { 
    
        service.EmpleadoData(nombre, apellido1, apellido2, nif, nus);
        
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping("/empleado")
    public ResponseEntity<String> listaEmpleado() {
    
        List <Empleado> listaemp = service.getLista();
        String inf2=" ";
        
        for (Empleado em: listaemp) {
        inf2 += em.getNombre_emp() +" ";
        inf2+= em.getApellido1emp()+" ";
        inf2 += em.getApellido2emp()+"\n";
        inf2 +=em.getNif()+"\n";
        inf2 += em.getNif()+"\n";
        inf2 += em.getFechainic() +"\n";
        inf2 += em.getFechafinc() +"\n";
          
        } 
    
    
    
    return ResponseEntity.ok(inf2);}
    
    
    
    
    
    
}
