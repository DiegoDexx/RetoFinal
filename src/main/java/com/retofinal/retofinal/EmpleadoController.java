/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
   
    @Autowired
    EmpleadoService service;
    
    @PostMapping("/create")
    public ResponseEntity<String> createEmp(String nombre_emp, String apellido1, String apellido2, String nif, String nus) { 
    
        service.EmpleadoData(nombre_emp, apellido1, apellido2, nif, nus);
        
        return ResponseEntity.ok().build();
    
    }
    
    @GetMapping("/lista")
    public ResponseEntity<String> listarEmp() { 
    
    List<Empleado> listarEmpleados = service.getLista();
    String res="";
    
    for(Empleado e: listarEmpleados){ 
        res+= e.getNombre_emp() + "\n";
        
    }
    
    return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<String> FindById(@PathVariable("id") Long idemp) { 
    
        Empleado empleadoid = service.getEmpleadoById(idemp);
        
        if( empleadoid== null){ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con id= "+idemp+" no existe");
        }else { 
            return ResponseEntity.ok().build();
        }      
        
    }
    
    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<Empleado>> FindByNombre(@PathVariable("name") String nombreemp) { 
    
    List<Empleado> listanombres = service.getEmpleadosByNombre(nombreemp);
    
        return ResponseEntity.ok(listanombres);
    
    } 
    
   @GetMapping("/apellido1/{apellido1}")
    public ResponseEntity<List<Empleado>> FindByApellido1(@PathVariable("apellido1") String apellido1) { 
    
    List<Empleado> listaapellidos = service.getEmpleadoByApellido1(apellido1);
    
        return ResponseEntity.ok(listaapellidos);
    }
    
    @GetMapping("/nif/{nif_empleado}")
    public ResponseEntity<List<Empleado>> FindByNif_empleado(@PathVariable("nif_empleado") String nif) { 
    
    List<Empleado> listanif = service.getEmpleadoByNIF(nif);
    
        return ResponseEntity.ok(listanif);
    }
    
        
     
     
     
     
    
    
    
    
    
}
