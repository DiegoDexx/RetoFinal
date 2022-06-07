/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;


import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Override
    <S extends Empleado> S save(S s);   
    
    @Override
    List<Empleado> findAll();

    @Override
    Optional<Empleado> findById(Long id);
    
    Optional<List<Empleado>> findEmpleadoByNombre(String nombremp);
    
    Optional<List<Empleado>> findEmpleadoByApellido1(String apellido1);
    
    Optional<List<Empleado>> findEmpleadoByNif(String nif_empleado);

    
}
