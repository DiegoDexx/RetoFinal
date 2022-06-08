/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
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

    @Autowired
    EmpleadoRepository repositoryEmpleado;

    @Autowired
    EmpresaRepository repositoryEmpresa;

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

        String contenidoFicheroXML = getFileContent(request);
        DocumentBuilderFactory dbuilder = DocumentBuilderFactory.newInstance();

        List<Empleado> listaEmpleados = repositoryEmpleado.findAll();

        for (Empleado e : listaEmpleados) {

            Empresa empr = repositoryEmpresa.findById(e.getIdempresa()).get();

            // DATOS EMPRESA //
            nom.setNombreEmpresaNomina(empr.getnombreempresa());
            nom.setDomicilioEmpresaNomina(empr.getDomicilio());
            nom.setCifEmpresaNomina(empr.getCif());
            nom.setCccEmpresaNomina(empr.getCcc());

            // DATOS TRABAJADOR //
            nom.setIdEmpleado(e.getId());
            nom.setNombreTrabajadorNomina(e.getNombre() + e.getApellido1() + e.getApellido2());
            nom.setNifTrabajadorNomina(e.getNif());
            nom.setNusTrabajadorNomina(e.getNus());
            nom.setGrupoCotizacionTrabajadorNomina(e.getGrupocot());

            // DATOS NOMINA //
            // PERIODO //
            if(nom.getId() == null){
                nom.setId(1L);
            } else {
                nom.setId(nom.getId() + 1);
            }
            nom.setFechaInicioNomina("2022/06/01");
            nom.setFechaFinNomina("2022/06/30");
            nom.setNumeroDiasNomina(nom.getNumeroDiasNomina());

            // DEVENGOS //
//        resultadoSalarioBase = 
            try {
                boolean salir = true;

                //dbuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                DocumentBuilder db = dbuilder.newDocumentBuilder();
                Document xmlf = db.parse(new InputSource(new StringReader(contenidoFicheroXML)));

                // get Convenio
                NodeList convenio = xmlf.getElementsByTagName("convenio");

                for (int a = 0; salir && a < convenio.getLength(); a++) {
                    Node nodoConvenio = convenio.item(a);

                    if (nodoConvenio.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoConvenio = (Element) nodoConvenio;

                        NodeList infor = elementoConvenio.getElementsByTagName("informacion");

                        for (int inf = 0; inf < infor.getLength() && salir; inf++) {
                            Node nodoInfor = convenio.item(inf);
                            Element elementoInfor = (Element) nodoInfor;
                            String nombreConvenio = elementoInfor.getElementsByTagName("nombre").item(0).getTextContent();
                            String nomConvenio = empr.getConvenio();

                            if (nombreConvenio.equals(nomConvenio)) {

                                NodeList tablasalarial = elementoConvenio.getElementsByTagName("tabla_salarial");

                                for (int b = 0; b < tablasalarial.getLength() && salir; b++) {
                                    Node nodetabla = tablasalarial.item(b);

                                    Element elementanyo = (Element) nodetabla;
                                    String anyo = elementanyo.getAttribute("anyo");
                                    String year = "2022";

                                    if (anyo.equals(year)) {

                                        if (nodetabla.getNodeType() == Node.ELEMENT_NODE) {
                                            Element elementoTabla = (Element) nodetabla;

                                            NodeList grupo = elementoTabla.getElementsByTagName("grupo");

                                            for (int c = 0; c < grupo.getLength() && salir; c++) {
                                                Node nodegrupo = grupo.item(c);

                                                Element elementgrupo = (Element) nodegrupo;

                                                String grupocot = elementgrupo.getAttribute("numero");
                                                String grupoElegido = e.getGrupocot();

                                                if (grupocot.equals(grupoElegido)) {
                                                    salir = false;
                                                    Element elementoGrupo = (Element) nodegrupo;
                                                    String salario = elementoGrupo.getElementsByTagName("salario_base").item(0).getTextContent();
                                                    String catProf = elementoGrupo.getElementsByTagName("categoria").item(0).getTextContent();
                                                    nom.setCatTrabajadorNomina(catProf);
                                                    Double SalBase = Double.parseDouble(salario);
                                                    nom.setResultadoSalarioBase(SalBase);
                                                }
                                            }

                                        }
                                    }
                                }
                            }

                        }

                    }

                }

            } catch (ParserConfigurationException | SAXException | IOException ex) {
                System.err.println("Ups, Something  wrong! " + ex.getMessage());

            }
            System.out.println(nom.getId());
            repositoryn.save(nom);

        }
        return nom;
    }
}
