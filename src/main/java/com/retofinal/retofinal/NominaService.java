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

    public Nomina getNominaInfo(Long idnomina, Long idempleado, String nomempresanomina, String direccionempresanomina, String cifempresanomina, String cccempresanomina, String nomtrabnomina, String niftrabnomina, String nuftrabnomina,
            String catTrabajadorNomina, String grupoCotizacionTrabajadorNomina, String fechaInicioNomina, String fechaFinNomina, int numeroDiasNomina, double resultadoSalarioBase,
            double resultadoHorasComplementarias, double resultadoHorasExtra, double resultadoHorasExtraMayor, double resultadoAyudaEspecial, double resultadoTransporte, double resultadoTeletrabajo,
            double resultadoDieta, double resultadoDietaMedia, double resultadoTotalDevengado, double contingenciasComunes, double porcentajeContingenciasComunes, double resultadoContingenciasComunes,
            double desempleo, double porcentajeDesempleo, double resultadoDesempleo, double fpTrabajador, double porcentajeFp, double resultadoFp, double horasExtraDeducciones, double porcentajeHorasExtraDeducciones,
            double resultadoHorasExtraDeducciones, double horasExtraMayorDeducciones, double porcentajeHorasExtraMayorDeducciones, double resultadoHorasExtramayorDeducciones, double resultadoTotalAportaciones,
            double irpf, int porcentajeIrpf, double resultadoIrpf, double resultadoTotalDeducir, double resultadoTotalPercibir, double resultadoRemuneracionMensual, double resultadoProrrataPagasExtra,
            double resultadoBaseContingenciasComunes, double porcentajeTipoEmpresa, double aportacionEmpresa, double accidenteTrabajoYEnfermedadProfesional, double porcentajeAccidenteTrabajoYEnfermedadProfesional,
            double resultadoAccidenteTrabajoYEnfermedadProfesional, double desempleoEmpresa, double porcentajeDesempleoEmpresa, double resultadoDesempleoEmpresa, double fp, double porcentajeFpEmpresa,
            double resultadoFpEmpresa, double fogasa, double porcentajeFogasa, double resultadoFogasa, double horasExtraEmpresa, double porcentajeHorasExtraEmpresa, double resultadoHorasExtraEmpresa,
            double horasExtraMayorEmpresa, double porcentajeHorasExtraMayorEmpresa, double resultadoHorasExtraMayorEmpresa, double baseIrpf, double totalAportacionesEmpresa, HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException {

        Nomina nom = new Nomina();

        // DATOS EMPRESA //
        nomempresanomina = empr.getnombreempresa();
        direccionempresanomina = empr.getDomicilio();
        cifempresanomina = empr.getCif();
        cccempresanomina = empr.getCcc();

        // DATOS TRABAJADOR //
        nomtrabnomina = em.getNombre() + em.getApellido1() + em.getApellido2();
        niftrabnomina = em.getNif();
        nuftrabnomina = em.getNus();
        /* catTrabajadorNomina = em.getCatProf();  Esto no va porque no hay un get de la categoria en el empleado*/
        grupoCotizacionTrabajadorNomina = em.getGrupocot();

        // DATOS NOMINA //
        // PERIODO //
        fechaInicioNomina = nom.getFechaInicioNomina();
        fechaFinNomina = nom.getFechaFinNomina();
        numeroDiasNomina = nom.getNumeroDiasNomina();

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
        String path = "C:\\Users\\MH-GAMES\\Desktop\\TrabajoRetoFinal\\";
        String file = "Reto_Nominas.xml";

        DocumentBuilderFactory dbuilder = DocumentBuilderFactory.newInstance();

        try {

            dbuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbuilder.newDocumentBuilder();
            Document xmlf = db.parse(new File(path + file));

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
