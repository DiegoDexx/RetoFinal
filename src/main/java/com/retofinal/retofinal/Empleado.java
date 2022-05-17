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
@Table(name="Empleado")
public class Empleado {
    
    public Empleado() { }
    
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     
    Long IDEmpleado;
     
     @Column(name="nombre_emp")
     private String nombre_emp;
     
     @Column(name="apellido1_emp")
     private String apellido1emp;
     
     @Column(name="apellido2_emp")
     private String apellido2emp;
     
     @Column(name="nif_empleado")
     private String nif;
     
     @Column(name="nus_empleado")
     private String nus;
     
     @Column(name="tipo_contrato")
     private String tipo;

    public Long getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(Long IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getApellido1emp() {
        return apellido1emp;
    }

    public void setApellido1emp(String apellido1emp) {
        this.apellido1emp = apellido1emp;
    }

    public String getApellido2emp() {
        return apellido2emp;
    }

    public void setApellido2emp(String apellido2emp) {
        this.apellido2emp = apellido2emp;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNus() {
        return nus;
    }

    public void setNus(String nus) {
        this.nus = nus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
     
     
     
     
}
