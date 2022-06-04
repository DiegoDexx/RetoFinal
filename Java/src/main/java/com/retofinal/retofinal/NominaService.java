/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego
 */
@Service
public class NominaService {
    
    @Autowired
            
            NominaRepository repositoryn;
    
    
    Empleado em = new Empleado();
    Empresa empr = new Empresa();
   
    
    public Nomina getNominaInfo(Long isnomina, Long idempresa, Long idemp, String nomempleado, String direccionnom, String cifnom, String cccempresa,String fecIni, String FechaFin,
                              double SalBase, String grupocot, String catProf, HttpServletRequest request)throws IOException, ParserConfigurationException, SAXException { 
       
        Nomina nom = new Nomina();
        
        nomempleado= em.getNombre()+em.getApellido1()+em.getApellido2();
        cifnom = em.getNif();
        direccionnom = empr.getDomicilio();
        cccempresa = empr.getCcc();
        fecIni = nom.getFechaInicio();
        FechaFin= nom.getFechaFinal();
        grupocot = em.getGrupocot(); /*ESTO NO FUNCIONA, HAY QUE GUARDAR LOS DATOS DE EMPLEADO Y EMPRESA 
                                        EN LOS DATOS DE LA NOMINA */
        
        nom.setNombreEmpleado(nomempleado);
        nom.setCifNomina(cifnom);
        nom.setDomicilioEmpresa(direccionnom);
        nom.setCccnomina(cccempresa);
        nom.setFechaInicio(fecIni);
        nom.setFechaFinal(FechaFin);
        nom.setGrupoCot(grupocot);
      
       
        String path="C:\\Users\\MH-GAMES\\Desktop\\TrabajoRetoFinal\\";
        String file= "Reto_Nominas.xml";
        
        DocumentBuilderFactory dbuilder = DocumentBuilderFactory.newInstance();
    
        try{ 
            
            dbuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true );
            DocumentBuilder db = dbuilder.newDocumentBuilder();
            Document xmlf = db.parse(new File(path+file));
            
            NodeList convenio = xmlf.getElementsByTagName("convenio");
            
            for( int a=0; a<convenio.getLength(); a++){ 
                Node node = convenio.item(a);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) { 
                Element element = (Element) node;
                
                NodeList tablasalarial = xmlf.getElementsByTagName("tabla_salarial");
                
                    for( int b=0; b<tablasalarial.getLength(); b++) { 
                       Node  nodetabla = tablasalarial.item(b);
                       
                       Element elementanyo = (Element) node;
                       String anyo = elementanyo.getAttribute("anyo");
                       String year="2022";
                       
                    if( anyo.equals(year)){
                       
                       if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                           Element elementt = (Element) nodetabla;
                           
                           NodeList grupo = xmlf.getElementsByTagName("grupo");
                           
                           for( int c=0; c<grupo.getLength(); c++) { 
                           Node nodegrupo = grupo.item(c);
                           
                           Element elementgrupo = (Element) nodegrupo;
                           
                           grupocot = elementgrupo.getAttribute("numero");
                           
                           switch(grupocot) {
                               
                               case "6 1":  if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                                                Element elementg1 = (Element) nodegrupo;
                                                String Salario = elementg1.getElementsByTagName("salario_base").item(0).getTextContent();
                                                 catProf = elementg1.getElementsByTagName("categoria").item(0).getTextContent();
                                                 SalBase = Double.parseDouble(Salario);
                                                 nom.setSalarioBase(SalBase);    }   
                           
                               case "7 1":   if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                                                Element elementg2 = (Element) nodegrupo;
                                                String Salario = elementg2.getElementsByTagName("salario_base").item(0).getTextContent();
                                                 catProf = elementg2.getElementsByTagName("categoria").item(0).getTextContent();
                                                 SalBase = Double.parseDouble(Salario);
                                                 nom.setSalarioBase(SalBase);    }                      
                               case "7 2": if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                                                Element elementg3 = (Element) nodegrupo;
                                                String Salario = elementg3.getElementsByTagName("salario_base").item(0).getTextContent();
                                                 catProf = elementg3.getElementsByTagName("categoria").item(0).getTextContent();
                                                 SalBase = Double.parseDouble(Salario);
                                                 nom.setSalarioBase(SalBase);    }  
                               
                               
                               /* DE ACUERDO CON LOS GRUPOS POR AÑO EN EL XML, ¿HABRIA QUE HACER 25 CASES? XD */
                               
                           }
                               }
                           
                      
                           }
                       } else
                        return null;                   
                    
                    }

                }
            
            
            }

        
        }catch( ParserConfigurationException |  SAXException | IOException e) {
         System.err.println("Ups, Something  wrong!");
        
        }
    
    
    repositoryn.save(nom);
    return nom; }
    
}
