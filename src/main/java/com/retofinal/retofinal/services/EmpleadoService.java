/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal.services;

import com.retofinal.retofinal.Empleado;
import com.retofinal.retofinal.Repository.EmpleadoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository emprepository;
    
    
   public void EmpleadoData(String nombre_emp, String apellido1, String apellido2, String nif, String nus) { 
   
       Empleado emp= new Empleado();
       emp.setNombre_emp(nombre_emp);
       emp.setApellido1emp(apellido1);
       emp.setApellido2emp(apellido2);
       emp.setNif(nif);
       emp.setNus(nus);
      
       emprepository.save(emp);

   }
   
   
   
   
   
   
   
   
    
   public Optional<Empleado> getEmpleadoById(Long idemp) { 
   
       Optional <Empleado> empleado = emprepository.findById(idemp);
       
   return empleado;
   
   }
   
   public List<Empleado> getEmpleadosByName(String nombre) { 
   
       Optional<List<Empleado>> listanombres = emprepository.findEmpleadosbyNombre(nombre);
        
        if(listanombres.isPresent()) {
            return listanombres.get();
        } else {
            return null;
        }
   }
    
    
}
