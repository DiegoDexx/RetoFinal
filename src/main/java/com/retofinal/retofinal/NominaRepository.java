/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long>{
    
    @Override
    <S extends Nomina > S save(S s);
    
    // <T extends Nomina > T save2(T Empleado);
    
   // @Override
   // List <Nomina> findAll();
    
    
    
    
    
}
