/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    @Override
    <S extends Empresa> S save(S s);
   
      @Override
    List<Empresa> findAll();
    
    
    
    @Override
    Optional <Empresa> findById(Long Id);
    /*
    Optional <List<Empresa>> findByNombreempresa(String nombreEmpresa);
        
    Optional <List<Empresa>> findByDomicilio(String domicilio);
    
    Optional <List<Empresa>> findByConvenio(String convenio);
    
    Optional <List<Empresa>> findByCif(String CIF);
    
    Optional <List<Empresa>> findByCcc(String CCC); */
    
}
