/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego, Ismael, Laura
 */
@RestController()
@RequestMapping("/info")
public class InfoController {

    @Autowired
    EmpresaService servicee;

    @PostMapping("/createEmpresa")
    public ResponseEntity<String> createEmpresa(String nombreEmpresa, String domicilio, String convenio, String cif, String ccc) {

        servicee.EmpresaData(nombreEmpresa, domicilio, convenio, cif, ccc);

        return ResponseEntity.ok().build();

    }
    
  @GetMapping("/empresa")
    public ResponseEntity<String> listaInfo() {

        List<Empresa> listaInfo = servicee.getInfo();
        String inf = " ";

        for (Empresa e : listaInfo) {
            inf += e.getnombreempresa() + "\n";
            inf += e.getDomicilio() + "\n";
            inf += e.getConvenio() + "\n";
            inf += e.getCif() + "\n";
            inf += e.getCcc();
        }

        return ResponseEntity.ok(inf);
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<String> listaEmpresaPorId(@PathVariable("id") Long idEmpresa) {

        String inf2 = "";
        Empresa empresa = servicee.getEmpById(idEmpresa);

        if (empresa != null) {
            inf2 += empresa.getnombreempresa() + "\n";
            inf2 += empresa.getDomicilio() + "\n";
            inf2 += empresa.getConvenio() + "\n";
            inf2 += empresa.getCif() + "\n";
            inf2 += empresa.getCcc();
            return ResponseEntity.ok(inf2);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna empresa con ese id");
        }

    }
  
    
    @Autowired
    EmpleadoService service;
     @PostMapping("/createEmpleado")
    public ResponseEntity<String> createEmpleado(String nombre, String apellido1, String apellido2, String nif, String nus, String tipocontrato, String fecinicontrato, String fecfincontrato, int idempresa, String grupocot, int irpf) {

        service.EmpleadoData(nombre, apellido1, apellido2, nif, nus, tipocontrato, fecinicontrato, fecfincontrato, idempresa, grupocot, irpf);

        return ResponseEntity.ok().build();
    }
   
    
    
    @GetMapping("/empleado")
    public ResponseEntity<String> listaEmpleado() {

        List<Empleado> listaemp = service.getLista();
        String inf2 = "";

        for (Empleado em : listaemp) {
            inf2 += em.getNombre() + " ";
            inf2 += em.getApellido1() + " ";
            inf2 += em.getApellido2() + "\n";
            inf2 += em.getNif() + "\n";
            inf2 += em.getNus() + "\n";
            inf2 += em.getFechainic() + "\n";
            inf2 += em.getFechafinc() + "\n";
            inf2 += em.getIdempresa() + "\n";          
            inf2 += em.getGrupocot() + "\n";
            inf2 += em.getIrpf() + "\n\n";

        }

        return ResponseEntity.ok(inf2);
    }
    
     @GetMapping("/empleado/{id}")
    public ResponseEntity<String> listaEmpleadoPorId(@PathVariable("id") Long idEmpleado) {

        String inf2 = "";
        Empleado em = service.getEmpleadoById(idEmpleado);

        if (em != null) {
            inf2 += em.getNombre() + " ";
            inf2 += em.getApellido1() + " ";
            inf2 += em.getApellido2() + "\n";
            inf2 += em.getNif() + "\n";
            inf2 += em.getNus() + "\n";
            inf2 += em.getFechainic() + "\n";
            inf2 += em.getFechafinc() + "\n";
            inf2 += em.getIdempresa() + "\n";
            inf2 += em.getGrupocot() + "\n";
            inf2 += em.getIrpf() + "\n\n";
            return ResponseEntity.ok(inf2);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ningún empleado con ese id");
        }
        
        
        

    }
    
    @Autowired 
    
    NominaService servicenomina;
    
    @GetMapping("/nomina")
    public ResponseEntity<String> NominaInfo(HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException{ 
        
        Empleado em= new Empleado();
        Empresa emp= new Empresa();
        
        
        String inf3=" ";
       Nomina nom = servicenomina.getNominaInfo(Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE, inf3, inf3, inf3, inf3, inf3, inf3, 0, inf3, inf3, request);
        
       if( nom != null) { 
       
        inf3 += nom.getNombreEmpleado()+" \n";
        inf3 += nom.getCifNomina()+" \n";
        inf3 += nom.getNusNomina() +"\n";
        inf3 += nom.getNombreEmpresa()+"\n";
        inf3 += nom.getDomicilioEmpresa()+"\n";
        inf3 += nom.getFechaInicio() + "\n";
        inf3 += nom.getFechaInicio() +"\n";
        inf3 += nom.getFechaFinal() +"\n";
        inf3 += nom.getSalarioBase(0) +"\n";
       
}
    
     return ResponseEntity.ok(inf3);} 
    
    
    
    
    
  
    
    
    
    
    
    
}