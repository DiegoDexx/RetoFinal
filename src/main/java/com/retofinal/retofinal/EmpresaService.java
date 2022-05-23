/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class EmpresaService {
    
    @Autowired
    
    EmpresaRepository repository;
    
    public void EmpresaData(String nombreEmp, String convenio, String domicilio, String CIFEmpresa, String CCC) {  
    
    Empresa empresa = new Empresa();
    
    empresa.setNombre_empresa(nombreEmp);
    empresa.setConvenio(convenio);
    empresa.setDomiclio(domicilio);
    empresa.setCif(CIFEmpresa);
    empresa.setCcc(CCC);
    
    repository.save(empresa);
    
    
    
    }
    
    
    
    
}
