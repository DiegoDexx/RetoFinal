/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;
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
    EmpleadoRepository repository;

    public void EmpleadoData(String nombre_emp, String apellido1, String apellido2, String nif, String nus) {

        Empleado emp = new Empleado();
        emp.setNombre_emp(nombre_emp);
        emp.setApellido1emp(apellido1);
        emp.setApellido2emp(apellido2);
        emp.setNif(nif);
        emp.setNus(nus);

        repository.save(emp);

    }

    public List getLista() {
        List<Empleado> listaEmpleado = repository.findAll();

        return listaEmpleado;
    }

    public Empleado getEmpleadoById(Long idemp) {

        Optional<Empleado> empleado = repository.findById(idemp);

        if (empleado.isPresent()) {
            return empleado.get();
        } else {
            return null;
        }
    }

    public List<Empleado> getEmpleadosByNombre(String nombre) {

        Optional<List<Empleado>> listanombres = repository.findEmpleadoByNombre(nombre);

        if (listanombres.isPresent()) {
            return listanombres.get();
        } else {
            return null;
        }
    } 
    
   public List<Empleado> getEmpleadoByApellido1(String apellido1) { 
        
        Optional <List<Empleado>> listapellidos = repository.findEmpleadoByApellido1(apellido1);
        
        if ( listapellidos.isPresent()) { 
            return listapellidos.get();
            
        }else
            return null;
    } 
    
    public List<Empleado> getEmpleadoByNIF(String nif) { 
        
        Optional <List<Empleado>> listanif = repository.findEmpleadoByNif(nif);
        
        if ( listanif.isPresent()) { 
            return listanif.get();
            
        }else
            return null;
    }
}