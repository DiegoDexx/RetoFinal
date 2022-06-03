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
public class EmpresaService {
    
    @Autowired
    
    EmpresaRepository repository;
    
    public void EmpresaData(String nombreEmp, String convenio, String domicilio, String CIFEmpresa, String CCC) {  
    
    Empresa empresa = new Empresa();
    
    
    empresa.setnombreEmp(nombreEmp);
    empresa.setConvenio(convenio);
    empresa.setDomicilio(domicilio);
    empresa.setCif(CIFEmpresa);
    empresa.setCcc(CCC);
    
    
    repository.save(empresa);
    }
    
    public List getInfo() {
    
        List <Empresa> Empresainfo = repository.findAll();
    
        return Empresainfo;
    
    }
    
    public Empresa getEmpById(Long idempr) {
    
        Optional <Empresa> empresa = repository.findById(idempr);
        
        if (empresa.isPresent()) {
            return empresa.get();
        } else {
            return null;
        }
    
    }
    /*
    public List<Empresa> getEmpresaByNombreEmp(String nombreEmpr){
    
        Optional<List<Empresa>> listanombres = repository.findByNombreempresa(nombreEmpr);
        
        if(listanombres.isPresent()) {
            return listanombres.get();
        } else
            return null;
    
    }
    
    public List<Empresa> getEmpresaByDomicilio(String domicilio){ 
    
        Optional<List<Empresa>> listadomicilio = repository.findByDomicilio(domicilio);
        
        if(listadomicilio.isPresent()) { 
            return listadomicilio.get();
        }else
            return null;
  
    }
    
    public List<Empresa> getEmpresaByConvenio(String convenio) { 
    
     Optional<List<Empresa>> listaconvenio = repository.findByConvenio(convenio);
        
        if(listaconvenio.isPresent()) { 
            return listaconvenio.get();
        }else
            return null;
  
    
    }
    
    
    
    public List<Empresa> getEmpresaByCif(String CIF) {
    
        Optional<List<Empresa>> listaCIF = repository.findByCif(CIF);
        
        if(listaCIF.isPresent()) {
            return listaCIF.get();
          }else
            return null;
 
    }
    
       public List<Empresa> getEmpresaByCcc(String Ccc) {
    
        Optional<List<Empresa>> listaCcc = repository.findByCcc(Ccc);
        
        if(listaCcc.isPresent()) {
            return listaCcc.get();
          }else
            return null;
 
    }
    
    
    
    
    */
    
    }

    
    
    
    

