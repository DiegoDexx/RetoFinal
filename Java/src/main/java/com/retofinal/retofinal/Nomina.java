/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.*;
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
@Table(name="nomina")
public class Nomina {
    
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
    
    // INFORMACIÓN EMPLEADO Y EMPRESA //
    @Column(name="nompreempnom")
    private String nombreEmpresa;
    
    @Column(name="domicilioempnom")
    private String domicilioEmpresa;
    
    @Column(name="cifnomina")
    private String cifnomina;
    
     @Column(name="cccempresanom")
    private String cccnomina;
    
    @Column(name="nomempleadonom")
    private String nombreEmpleado;
     
    @Column(name="cifempleadonom")
    private String cifNomina;
    
    @Column(name="nusempleadonom")
    private String nusNomina;
    
    @Column(name="grupocot")
    private String grupoCot;

    @Column(name="fecininomina")
    private String fechaInicio;
    
    @Column(name="fecfinnomina")
    private String fechaFinal;

    //INFORMACION Y CALCULOS NOMINA
    
    @Column(name="salbase")
    private double salBase;
    

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDomicilioEmpresa() {
        return domicilioEmpresa;
    }

    public void setDomicilioEmpresa(String domicilioEmpresa) {
        this.domicilioEmpresa = domicilioEmpresa;
    }

    public String getCifnomina() {
        return cifnomina;
    }

    public void setCifnomina(String cifnomina) {
        this.cifnomina = cifnomina;
    }

    public String getCccnomina() {
        return cccnomina;
    }

    public void setCccnomina(String cccnomina) {
        this.cccnomina = cccnomina;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCifNomina() {
        return cifNomina;
    }

    public void setCifNomina(String cifNomina) {
        this.cifNomina = cifNomina;
    }

    public String getNusNomina() {
        return nusNomina;
    }

    public String getGrupoCot() {
        return grupoCot;
    }

    public void setGrupoCot(String grupoCot) {
        this.grupoCot = grupoCot;
    }
    
    

    public void setNusNomina(String nusNomina) {
        this.nusNomina = nusNomina;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
     public double getSalarioBase(double salBase) {
        return salBase;
    }
     
    public void setSalarioBase(double SalarioBase){ 
        this.salBase = SalarioBase;
    }
    
   
    
    
}

