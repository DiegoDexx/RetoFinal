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
@Table(name = "empleado")
public class Empleado {

    public Empleado() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nus")
    private String nus;

    /*@Column(name = "tipoContrato")
    private String tipoContrato; */

    public Long getIDEmpleado() {
        return id;
    }

    public void setIDEmpleado(Long IDEmpleado) {
        this.id = IDEmpleado;
    }

    public String getNombre_emp() {
        return nombre;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre = nombre_emp;
    }

    public String getApellido1emp() {
        return apellido1;
    }

    public void setApellido1emp(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2emp() {
        return apellido2;
    }

    public void setApellido2emp(String apellido2) {
        this.apellido2 = apellido2;
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

   /* public String getTipo() {
        return tipoContrato;
    }

    public void setTipo(String tipo) {
        this.tipoContrato = tipo; */
    
    
    }
    
    

