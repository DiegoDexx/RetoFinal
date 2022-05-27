/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author Diego
 */
@Entity
@Table(name="empresa")
public class Empresa {
    
    public Empresa() { 
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
      
     @Column(name="nomempresa")
     private String nombreempresa;
     
     @Column(name="direccion")
     private String domicilio;
     
     @Column(name="convenio")
     private String convenio;
     
     @Column(name="cif")
     private String cif;
     
     @Column(name="ccc")
     private String ccc;

     
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombreempresa() {
        return nombreempresa;
    }

    public void setnombreEmp(String nombreEmpresa) {
        this.nombreempresa= nombreEmpresa;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
     
     

    
    
}
