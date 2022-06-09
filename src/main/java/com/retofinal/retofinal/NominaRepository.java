/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
    
    @Override
    Optional<Nomina> findById(Long id);
    
    // @Override
   // List <Nomina> findAll();

    }
    
    
   
    
    
    
    
    

