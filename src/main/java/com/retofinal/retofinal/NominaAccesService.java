/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.*;
import org.w3c.dom.Document;
import javax.servlet.http.HttpServletRequest;
import org.w3c.dom.Element;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego
 */
@Service
public class NominaAccesService {
    
    
    public String getXML(HttpServletRequest request)throws IOException, ParserConfigurationException, SAXException{ 
        String filecontent=" ";
        String path="C:\\Users\\MH-GAMES\\Desktop\\TrabajoRetoFinal\\";
        String file= "Reto_Nominas.xml";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    
        try{

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document fic = db.parse(new File(path+file));
            
            fic.getDocumentElement().normalize();
            
            System.out.println("Principal :" + fic.getDocumentElement().getNodeName());
            System.out.println("------");
            
            NodeList names= fic.getElementsByTagName("convenio");
            
            for ( int i=0; i<names.getLength(); i++) { 
                Node data= names.item(i);
                
                if ( data.getNodeType()== Node.ELEMENT_NODE) { 
                    Element element = (Element) data;
                    
                    NodeList fecha1 = fic.getElementsByTagName("inicio_vigencia");
                       for ( int j=0; j<fecha1.getLength(); j++){ 
                       Node nodef1 = fecha1.item(j);
                       
                       if ( nodef1.getNodeType() == Node.ELEMENT_NODE) { 
                           Element element2= (Element) nodef1;
                      
                         String dia1 = element2.getElementsByTagName("dia").item(0).getTextContent();
                         String mes1=  element2.getElementsByTagName("mes").item(0).getTextContent();
                         String anyo= element2.getElementsByTagName("anyo").item(0).getTextContent();
                         
                         System.out.println("--FECHA DE INICIO DE VIGENCIA--");
                         System.out.println("Dia: "+dia1);
                         System.out.println("Mes: "+mes1);
                         System.out.println("Año: "+anyo);
                       }                       
                       }
                }                    
                    NodeList fecha2 = fic.getElementsByTagName("final_vigencia");
                     for ( int j=0; j<fecha2.getLength(); j++){ 
                       Node nodef1 = fecha2.item(j);
                       
                       
                       if ( nodef1.getNodeType() == Node.ELEMENT_NODE) { 
                           Element element3= (Element) nodef1;
                      
                         String dia2 = element3.getElementsByTagName("dia").item(0).getTextContent();
                         String mes2=  element3.getElementsByTagName("mes").item(0).getTextContent();
                         String anyo2= element3.getElementsByTagName("anyo").item(0).getTextContent();
                         
                         System.out.println("\n--FECHA FINAL DE VIGENCIA--");
                         System.out.println("Dia: "+dia2);
                         System.out.println("Mes: "+mes2);
                         System.out.println("Año: "+anyo2);
                 
                       } 
                       }
                     
                       Element element4 = (Element) data;
                       String territorio = element4.getElementsByTagName("territorio").item(0).getTextContent();
                       System.out.println("\n--DATOS RECOGIDOS DE: "+territorio);
                     
                    NodeList tbs = fic.getElementsByTagName("tabla_salarial");
                    for( int f=0; f<tbs.getLength(); f++) {
                        Node nodefy= tbs.item(f);
                        
                        if( nodefy.getNodeType() == Node.ELEMENT_NODE){
                        
                        Element elementyear= (Element) nodefy;
                        
                        String anyo= elementyear.getAttribute("anyo");
                        System.out.println("---TABLA SALARIAL DE AÑO: "+anyo+" ---");        
                        
                        NodeList grupo= fic.getElementsByTagName("grupo");
                        for ( int k=0; k<grupo.getLength(); k++) { 
                           Node nodef2= grupo.item(k);
                           
                           Element elementgr= (Element) nodef2;
                           
                           String idgrupo= elementgr.getAttribute("numero");
                           System.out.println("\n--NÚMERO Y NIVEL DE GRUPO: "+idgrupo);
                                                      
                           if ( nodef2.getNodeType() == Node.ELEMENT_NODE) { 
                               Element element5 = (Element) nodef2;
                                  
                               String categoria= element5.getElementsByTagName("categoria").item(0).getTextContent();
                               String salario= element5.getElementsByTagName("salario_base").item(0).getTextContent();
                                    
                               System.out.println("\t-Categoria: "+categoria);
                               System.out.println("\t-Salario base: " +salario);
                           
                           }
                       }
                        }
                     }
             System.out.println("\nFin de la tabla salarial.");
             NodeList convenio2= fic.getElementsByTagName("fuente");
                
                for( int l=0; l<convenio2.getLength();l++ ){
                    Node last = convenio2.item(0);
                    Element idc= (Element) last;
                    String defaultid = idc.getAttribute("id");
                    System.out.println("-Id: "+defaultid);
                    
                    if( last.getNodeType() == Node.ELEMENT_NODE) { 
                    Element elementlast = (Element) last;
                    
                    String estatuto = elementlast.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println("\nFuente oficial: "+estatuto);
                    
                    }
                }
                }
                    

        
        }catch(ParserConfigurationException  | SAXException | IOException e) { 
            System.err.println("Ups, Something  wrong!");
        }
    
    
    
    return filecontent;
    }
    
    
    
}
