/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import java.io.FileNotFoundException;
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
                    org.w3c.dom.Document xmlf = db.parse(new InputSource(new StringReader(contenidoFicheroXML)));

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
                                                String salario = "";
                                                for (int c = 0; c < grupo.getLength() && salir; c++) {
                                                    Node nodegrupo = grupo.item(c);

                                                    Element elementgrupo = (Element) nodegrupo;

                                                    String grupocot = elementgrupo.getAttribute("numero");
                                                    String grupoElegido = e.getGrupocot();
                                                    
                                                    if (grupocot.equals(grupoElegido)) {
                                                        salir = false;
                                                        Element elementoGrupo = (Element) nodegrupo;
                                                        salario = elementoGrupo.getElementsByTagName("salario_base").item(0).getTextContent();
                                                        String catProf = elementoGrupo.getElementsByTagName("categoria").item(0).getTextContent();
                                                        int div = Integer.parseInt(elementoGrupo.getAttribute("divide"));

                                                        nom.setCatTrabajadorNomina(catProf);
                                                        Double SalBase = (Double.parseDouble(salario)) / div;
                                                        nom.setResultadoSalarioBase(SalBase);
                                                    }
                                                }
                                                double salBaseHora = Double.parseDouble(salario) / 1790;

                                                nom.setResultadoHorasExtra((Math.random() * 10 + 1) * salBaseHora * 1.5);

                                                double percepcionesSalariales = nom.getResultadoSalarioBase() + nom.getResultadoHorasExtra() + nom.getResultadoHorasComplementarias() + nom.getResultadoHorasExtraMayor();
                                                double percepcionesNoSalariales = nom.getResultadoAyudaEspecial() + nom.getResultadoTransporte() + nom.getResultadoTeletrabajo() + nom.getResultadoDieta() + nom.getResultadoDietaMedia();
                                                nom.setResultadoTotalDevengado(percepcionesSalariales + percepcionesNoSalariales);

                                                //Deducciones
                                                nom.setContingenciasComunes(nom.getResultadoSalarioBase());
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
                                                nom.setResultadoHorasExtraDeducciones(nom.getHorasExtraDeducciones() * nom.getPorcentajeHorasExtraDeducciones() / 100);

                                                nom.setResultadoTotalDeducir(nom.getResultadoContingenciasComunes() + nom.getResultadoDesempleo() + nom.getResultadoFp() + nom.getResultadoHorasExtraDeducciones() + nom.getResultadoIrpf());

                                                //Total a percibir
                                                nom.setResultadoTotalPercibir(nom.getResultadoTotalDevengado() - nom.getResultadoTotalDeducir());

                                                //Aportaciones empresa
                                                nom.setResultadoBaseContingenciasComunes(((nom.getResultadoSalarioBase() * 2 / 12) + nom.getResultadoSalarioBase()) * 23.60 / 100);

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

                                                nom.setHorasExtraEmpresa(nom.getHorasExtraDeducciones());
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

    public Nomina getNominaById(Long idnomina) {

        Optional<Nomina> nomina = repositoryn.findById(idnomina);

        if (nomina.isPresent()) {
            return nomina.get();
        } else {
            System.err.println("No existe dicha nomina con ese id");
            return null;
        }

    }

    public void crearPdf() throws FileNotFoundException, DocumentException {
        List<Nomina> listaNominas = repositoryn.findAll();

        for (int i = 0; i < listaNominas.size(); i++) {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("/home/ismael/Escritorio/PDFNominas/Nomina" + listaNominas.get(i).getId() + ".pdf"));

            document.open();
            
            Font titulos = FontFactory.getFont(FontFactory.TIMES, 22, BaseColor.BLACK);
            Font encabezado = FontFactory.getFont(FontFactory.TIMES, 18, BaseColor.BLACK);
            Font texto = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);
            
            // CABECERA //
            Phrase frase = new Phrase();
            Paragraph p = new Paragraph();
            
            Chunk chunk = new Chunk("Datos de la empresa:\n\n" , titulos);
            frase.add(chunk);
            Chunk chunk2 = new Chunk("Nombre:      " + listaNominas.get(i).getNombreEmpresaNomina() + "\n", texto);
            frase.add(chunk2);
            Chunk chunk3 = new Chunk("Direccion:    " + listaNominas.get(i).getDomicilioEmpresaNomina() + "\n", texto);
            frase.add(chunk3);
            Chunk chunk4 = new Chunk("CIF:              " + listaNominas.get(i).getCifEmpresaNomina() + "\n", texto);
            frase.add(chunk4);
            Chunk chunk5 = new Chunk("CCC:            " + listaNominas.get(i).getCccEmpresaNomina() + "\n", texto);
            frase.add(chunk5);
            
            Chunk lineasSeparadoras = new Chunk("_______________________________\n\n", texto);
            frase.add(lineasSeparadoras);
            
            Chunk chunk6 = new Chunk("Datos del trabajador:\n\n", titulos);
            frase.add(chunk6);
            Chunk chunk7 = new Chunk("Nombre:                " + listaNominas.get(i).getNombreTrabajadorNomina() + "\n", texto);
            frase.add(chunk7);
            Chunk chunk8 = new Chunk("Nif:                        " + listaNominas.get(i).getNifTrabajadorNomina() + "\n", texto);
            frase.add(chunk8);
            Chunk chunk9 = new Chunk("Numero S.S:          " + listaNominas.get(i).getNusTrabajadorNomina() + "\n", texto);
            frase.add(chunk9);
            Chunk chunk10 = new Chunk("Categoria:              " + listaNominas.get(i).getCatTrabajadorNomina() + "\n", texto);
            frase.add(chunk10);
            Chunk chunk11 = new Chunk("Grupo cotizacion:  " + listaNominas.get(i).getGrupoCotizacionTrabajadorNomina() + "\n", texto);
            frase.add(chunk11);
            
            frase.add(lineasSeparadoras);
            
            // PERIODO //
            Chunk chunk12 = new Chunk("Periodo de liquidacion:    " + listaNominas.get(i).getFechaInicioNomina() + " - " + listaNominas.get(i).getFechaFinNomina() + "\nNº dias: " + listaNominas.get(i).getNumeroDiasNomina() + "\n\n", texto);
            frase.add(chunk12);
            
            Chunk chunk13 = new Chunk("I. Devengos:\n", encabezado);
            frase.add(chunk13);
            Chunk chunk14 = new Chunk("Salario base:                   " + listaNominas.get(i).getResultadoSalarioBase() + "€\n", texto);
            frase.add(chunk14);
            Chunk chunk15 = new Chunk("Horas extraordinarias:    " + listaNominas.get(i).getResultadoHorasExtra() +"€\n\n", texto);
            frase.add(chunk15);
            Chunk chunk16 = new Chunk("Total devengado:   " + listaNominas.get(i).getResultadoTotalDevengado() + "€\n\n\n", encabezado);
            frase.add(chunk16);
            
            Chunk chunk17 = new Chunk("II. Deducciones:\n", encabezado);
            frase.add(chunk17);
            Chunk chunk18 = new Chunk("Contingencias comunes:           " + listaNominas.get(i).getContingenciasComunes() + "€       "+ listaNominas.get(i).getPorcentajeContingenciasComunes() + "%       " + listaNominas.get(i).getResultadoContingenciasComunes() + "€\n", texto);
            frase.add(chunk18);
            Chunk chunk19 = new Chunk("Desempleo:                               " + listaNominas.get(i).getDesempleo() + "€       "+ listaNominas.get(i).getPorcentajeDesempleo() + "%       " + listaNominas.get(i).getResultadoDesempleo() + "€\n", texto);
            frase.add(chunk19);
            Chunk chunk20 = new Chunk("Formacion profesional:             " + listaNominas.get(i).getFpTrabajador() + "€       "+ listaNominas.get(i).getPorcentajeFp() + "%       " + listaNominas.get(i).getResultadoFp() + "€\n", texto);
            frase.add(chunk20);
            Chunk chunk21 = new Chunk("Horas extraordinarias:               " + listaNominas.get(i).getHorasExtraDeducciones() + "€           "+ listaNominas.get(i).getPorcentajeHorasExtraDeducciones() + "%       " + listaNominas.get(i).getResultadoHorasExtraDeducciones() + "€\n", texto);
            frase.add(chunk21);
            Chunk chunk22 = new Chunk("Irpf:                                           " + listaNominas.get(i).getIrpf() + "€       "+ listaNominas.get(i).getPorcentajeIrpf() + "%       " + listaNominas.get(i).getResultadoIrpf() + "€\n\n", texto);
            frase.add(chunk22);
            Chunk chunk23 = new Chunk("Total a deducir:                              " + listaNominas.get(i).getResultadoTotalDeducir() + "€\n\n", encabezado);
            frase.add(chunk23);
            
            frase.add(lineasSeparadoras);
            
            Chunk chunk24 = new Chunk("TOTAL A PERCIBIR:   " + listaNominas.get(i).getResultadoTotalPercibir() + "€\n", encabezado);
            frase.add(chunk24);
            
            frase.add(lineasSeparadoras);
            
            Chunk chunk25 = new Chunk("Aportacion de la empresa:\n", encabezado);
            frase.add(chunk25);
            Chunk chunk26 = new Chunk("Base contingencias comunes:           " + listaNominas.get(i).getContingenciasComunes() + "€       "+ "23.60%       " + listaNominas.get(i).getResultadoBaseContingenciasComunes() + "€\n", texto);
            frase.add(chunk26);
            Chunk chunk27 = new Chunk("AT y EP:                                           " + listaNominas.get(i).getAccidenteTrabajoYEnfermedadProfesional() + "€       "+  listaNominas.get(i).getPorcentajeAccidenteTrabajoYEnfermedadProfesional() + "%          " + listaNominas.get(i).getResultadoAccidenteTrabajoYEnfermedadProfesional() + "€\n", texto);
            frase.add(chunk27);
            Chunk chunk28 = new Chunk("Desempleo:                                       " + listaNominas.get(i).getDesempleoEmpresa() + "€       "+  listaNominas.get(i).getPorcentajeDesempleoEmpresa() + "%          " + listaNominas.get(i).getResultadoDesempleoEmpresa()  + "€\n", texto);
            frase.add(chunk28);
            Chunk chunk29 = new Chunk("FOGASA:                                         " + listaNominas.get(i).getFogasa() + "€       "+  listaNominas.get(i).getPorcentajeFogasa() + "%          " + listaNominas.get(i).getResultadoFogasa() + "€\n", texto);
            frase.add(chunk29);
            Chunk chunk30 = new Chunk("Horas extra :                                      " + listaNominas.get(i).getHorasExtraEmpresa() + "€       "+  listaNominas.get(i).getPorcentajeHorasExtraEmpresa() + "%           " + listaNominas.get(i).getResultadoHorasExtraEmpresa() + "€\n", texto);
            frase.add(chunk30);
            Chunk chunk31 = new Chunk("Total aportaciones empresa:               " + listaNominas.get(i).getTotalAportacionesEmpresa() + "€", encabezado);
            frase.add(chunk31);
            
            p.add(frase);
            document.add(p);
            document.close();
        }
    }

}
