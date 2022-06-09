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
 * @author Diego, Laura e Ismael
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

            for (Integer mes = 1; mes < 6; mes++) {

                Integer primerDia = 1;
                Integer ultimoDia;
                switch (mes) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        ultimoDia = 31;
                        break;
                    case 2:
                        ultimoDia = 28;
                        break;
                    default:
                        ultimoDia = 30;
                        break;

                }

                if (mes < 10) {
                    nom.setFechaInicioNomina("2022/0" + mes.toString() + "/0" + primerDia.toString());
                    nom.setFechaFinNomina("2022/0" + mes.toString() + "/" + ultimoDia.toString());
                } else {
                    nom.setFechaInicioNomina("2022/" + mes + "/0" + primerDia);
                    nom.setFechaFinNomina("2022/" + mes + "/" + ultimoDia);
                }
                nom.setNumeroDiasNomina(ultimoDia);

                Empresa empr = repositoryEmpresa.findById(e.getIdempresa()).get();

                // DATOS EMPRESA //
                nom.setNombreEmpresaNomina(empr.getnombreempresa());
                nom.setDomicilioEmpresaNomina(empr.getDomicilio());
                nom.setCifEmpresaNomina(empr.getCif());
                nom.setCccEmpresaNomina(empr.getCcc());

                // DATOS TRABAJADOR //
                nom.setIdEmpleado(e.getId());
                nom.setNombreTrabajadorNomina(e.getNombre() + " " + e.getApellido1() + " " + e.getApellido2());
                nom.setNifTrabajadorNomina(e.getNif());
                nom.setNusTrabajadorNomina(e.getNus());
                nom.setGrupoCotizacionTrabajadorNomina(e.getGrupocot());

                // DATOS NOMINA //
                // PERIODO //
                if (nom.getId() == null) {
                    nom.setId(1L);
                } else {
                    nom.setId(nom.getId() + 1);
                }

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
                                                        int div = Integer.parseInt(elementoGrupo.getAttribute("divide"));

                                                        nom.setCatTrabajadorNomina(catProf);
                                                        Double SalBase = (Double.parseDouble(salario)) / div;
                                                        nom.setResultadoSalarioBase(SalBase);
                                                    }
                                                }
                                                double salBaseHora = nom.getResultadoSalarioBase() / 1790;

                                                nom.setResultadoHorasExtra((Math.random() * 10 + 1) * salBaseHora * 1.5);

                                                double percepcionesSalariales = nom.getResultadoSalarioBase() + nom.getResultadoHorasExtra() + nom.getResultadoHorasComplementarias() + nom.getResultadoHorasExtraMayor();
                                                double percepcionesNoSalariales = nom.getResultadoAyudaEspecial() + nom.getResultadoTransporte() + nom.getResultadoTeletrabajo() + nom.getResultadoDieta() + nom.getResultadoDietaMedia();
                                                nom.setResultadoTotalDevengado(percepcionesSalariales + percepcionesNoSalariales);

                                                //Deducciones
                                                nom.setContingenciasComunes(percepcionesSalariales + nom.getResultadoSalarioBase());
                                                nom.setPorcentajeContingenciasComunes(4.7);
                                                nom.setResultadoContingenciasComunes(nom.getContingenciasComunes() * nom.getPorcentajeContingenciasComunes() / 100);

                                                //Falta sacar los datos del porcentaje de desempleo del otro xml, por ahora, siempre toma el mismo valor.
                                                nom.setDesempleo(nom.getContingenciasComunes() + nom.getResultadoHorasExtra());
                                                nom.setPorcentajeDesempleo(1.55);
                                                nom.setResultadoDesempleo(nom.getDesempleo() * nom.getPorcentajeDesempleo() / 100);

                                                nom.setFpTrabajador(nom.getDesempleo());
                                                nom.setPorcentajeFp(0.1);
                                                nom.setResultadoFp(nom.getFpTrabajador() * nom.getPorcentajeFp() / 100);

                                                nom.setIrpf(nom.getDesempleo());
                                                nom.setPorcentajeIrpf(12); //Se ha establecido a 12, pero depende de las circunstancias del trabajador.
                                                nom.setResultadoIrpf(nom.getIrpf() * nom.getPorcentajeIrpf() / 100);

                                                nom.setHorasExtraDeducciones(nom.getResultadoHorasExtra());
                                                nom.setPorcentajeHorasExtraDeducciones(4.7);
                                                nom.setResultadoHorasExtraDeducciones(nom.getDesempleo() * nom.getPorcentajeDesempleo() / 100);

                                                nom.setResultadoTotalDeducir(nom.getResultadoContingenciasComunes() + nom.getResultadoDesempleo() + nom.getResultadoFp() + nom.getResultadoHorasExtraDeducciones() + nom.getResultadoIrpf());

                                                //Total a percibir
                                                nom.setResultadoTotalPercibir(nom.getResultadoTotalDevengado() - nom.getResultadoTotalDeducir());

                                                //Aportaciones empresa
                                                //La base sobre la que se calcula la parte que aporta las contingencias comunes, es la misma que aparece en nom.getContingenciasComunes();
                                                //El porcentaje en este caso, lo voy a añadir directamente por programacion, ya que no existe una variable para guardarlo
                                                nom.setResultadoBaseContingenciasComunes(nom.getContingenciasComunes() * 23.60 / 100);

                                                nom.setAccidenteTrabajoYEnfermedadProfesional(nom.getDesempleo());
                                                nom.setPorcentajeAccidenteTrabajoYEnfermedadProfesional(2); //Dependiendo la activaidad de la empresa, el numero varia.
                                                nom.setResultadoAccidenteTrabajoYEnfermedadProfesional(nom.getAccidenteTrabajoYEnfermedadProfesional() * nom.getResultadoAccidenteTrabajoYEnfermedadProfesional() / 100);

                                                nom.setDesempleoEmpresa(nom.getDesempleo());
                                                nom.setPorcentajeDesempleoEmpresa(5.5); //En determinadas circunstancias puede tomar otro valor, se toma del otro xml.
                                                nom.setResultadoDesempleoEmpresa(nom.getDesempleoEmpresa() * nom.getPorcentajeDesempleoEmpresa() / 100);

                                                nom.setFp(nom.getDesempleo());
                                                nom.setPorcentajeFpEmpresa(0.6);
                                                nom.setResultadoFpEmpresa(nom.getFp() * nom.getPorcentajeFpEmpresa() / 100);

                                                nom.setFogasa(nom.getDesempleo());
                                                nom.setPorcentajeFogasa(0.2);
                                                nom.setResultadoFogasa(nom.getFogasa() * nom.getPorcentajeFogasa() / 100);

                                                nom.setHorasExtraEmpresa(nom.getHorasExtraMayorDeducciones());
                                                nom.setPorcentajeHorasExtraEmpresa(23.60);
                                                nom.setResultadoHorasExtraEmpresa(nom.getHorasExtraEmpresa() * nom.getPorcentajeHorasExtraEmpresa() / 100);

                                                //Total aportaciones empresa
                                                nom.setTotalAportacionesEmpresa(nom.getResultadoBaseContingenciasComunes() + nom.getResultadoAccidenteTrabajoYEnfermedadProfesional() + nom.getResultadoDesempleoEmpresa() + nom.getResultadoFpEmpresa() + nom.getResultadoFogasa() + nom.getResultadoHorasExtraEmpresa());
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
                repositoryn.save(nom);
            }
        }
        return nom;
    }
}
