/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal.Repository;

import com.retofinal.retofinal.Empleado;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
     @Override
    <S extends Empleado> S save(S s);   
    
    @Autowired
    
    
    
    
    @Override
    Optional<Empleado> findById(Long id);
    
    Optional<List<Empleado>> findEmpleadosbyNombre(String nombremp);
    
    
    
    
    
}
