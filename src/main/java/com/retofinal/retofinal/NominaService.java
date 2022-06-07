/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Diego
 */
@Service
public class NominaService {

    @Autowired

    NominaRepository repositoryn;

    public String getFileContent(HttpServletRequest request) {

        String fileContent = "";

        try {
            InputStream input = request.getInputStream();

            byte[] data = new byte[1024];

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            fileContent = new String(buffer.toByteArray());

        } catch (IOException ex) {
            System.err.println("Error! " + ex.getMessage());
        }

        return fileContent;
    }

    public Nomina getNominaInfo(HttpServletRequest request) {
        Nomina nom = new Nomina();

//        // DATOS EMPRESA //
//        String nomempresanomina = empr.getnombreempresa();
//        String direccionempresanomina = empr.getDomicilio();
//        String cifempresanomina = empr.getCif();
//        String cccempresanomina = empr.getCcc();
//
//        // DATOS TRABAJADOR //
//        String nomtrabnomina = em.getNombre() + em.getApellido1() + em.getApellido2();
//        String niftrabnomina = em.getNif();
//        String nuftrabnomina = em.getNus();
//        String catTrabajadorNomina = getCatTrabajador();
//        String grupoCotizacionTrabajadorNomina = em.getGrupocot();
//
//        // DATOS NOMINA //
//        // PERIODO //
//        String fechaInicioNomina = nom.getFechaInicioNomina();
//        String fechaFinNomina = nom.getFechaFinNomina();
//        int numeroDiasNomina = nom.getNumeroDiasNomina();

        // DEVENGOS //
//        resultadoSalarioBase = 
//
//        nom.setNombreEmpleado(nomempleado);
//        nom.setCifNomina(cifnom);
//        nom.setDomicilioEmpresa(direccionnom);
//        nom.setCccnomina(cccempresa);
//        nom.setFechaInicio(fecIni);
//        nom.setFechaFinal(FechaFin);
//        nom.setGrupoCot(grupocot);
        String contenidoFicheroXML = getFileContent(request);
        DocumentBuilderFactory dbuilder = DocumentBuilderFactory.newInstance();

        try {

            //dbuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbuilder.newDocumentBuilder();
            Document xmlf = db.parse(new InputSource(new StringReader(contenidoFicheroXML)));

            // get Convenio
            NodeList convenio = xmlf.getElementsByTagName("convenio");

            for (int a = 0; a < convenio.getLength(); a++) {
                Node nodoConvenio = convenio.item(a);

                if (nodoConvenio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoConvenio = (Element) nodoConvenio;

                    NodeList tablasalarial = elementoConvenio.getElementsByTagName("tabla_salarial");

                    for (int b = 0; b < tablasalarial.getLength(); b++) {
                        Node nodetabla = tablasalarial.item(b);

                        Element elementanyo = (Element) nodoConvenio;
                        String anyo = elementanyo.getAttribute("anyo");
                        String year = "2022";

                        if (anyo.equals(year)) {

                            if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementoTabla = (Element) nodetabla;

                                NodeList grupo = elementoTabla.getElementsByTagName("grupo");

                                for (int c = 0; c < grupo.getLength(); c++) {
                                    Node nodegrupo = grupo.item(c);

                                    Element elementgrupo = (Element) nodegrupo;

                                    String grupocot = elementgrupo.getAttribute("numero");
                                    String grupoElegido = "6 1";

                                    if (grupocot.equals(grupoElegido)) {
                                        Element elementoGrupo = (Element) nodegrupo;
                                        String salario = elementoGrupo.getElementsByTagName("salario_base").item(0).getTextContent();
                                        String catProf = elementoGrupo.getElementsByTagName("categoria").item(0).getTextContent();
                                        Double SalBase = Double.parseDouble(salario);
                                        nom.setResultadoSalarioBase(SalBase);
                                        nom.setCatTrabajadorNomina(catProf);
                                    }
                                }

                            }
                        } else {
                            return null;
                        }

                    }

                }

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Ups, Something  wrong!");

        }
        repositoryn.save(nom);
        return nom;
    }

}
