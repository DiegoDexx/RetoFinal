/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retofinal.retofinal;

import java.io.*;
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
@Table(name = "nomina")
public class Nomina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idempleado")
    private Long idEmpleado;

    // CABECERA //
    // EMPRESA //
    @Column(name = "nomempresanomina")
    private String nombreEmpresaNomina;

    @Column(name = "direccionempresa")
    private String domicilioEmpresaNomina;

    @Column(name = "cifempresa")
    private String cifEmpresaNomina;

    @Column(name = "cccempresa")
    private String cccEmpresaNomina;

    // EMPLEADO //
    @Column(name = "nomtrabnomina")
    private String nombreTrabajadorNomina;

    @Column(name = "niftrabnomina")
    private String nifTrabajadorNomina;

    @Column(name = "nuftrabnomina")
    private String nusTrabajadorNomina;

    @Column(name = "cattrabnomina")
    private String catTrabajadorNomina;

    @Column(name = "grupcottrabnomina")
    private String grupoCotizacionTrabajadorNomina;

    @Column(name = "fecininomina")
    private String fechaInicioNomina;

    @Column(name = "fecfinnomina")
    private String fechaFinNomina;

    @Column(name = "ndiasnomina")
    private int numeroDiasNomina;
    //-------------------------//

    // DEVENGOS //
    @Column(name = "ressalbase")
    private double resultadoSalarioBase;

    @Column(name = "reshorascomp")
    private double resultadoHorasComplementarias;

    @Column(name = "reshorasextra")
    private double resultadoHorasExtra;

    @Column(name = "reshorasextramayor")
    private double resultadoHorasExtraMayor;

    @Column(name = "resayudaespecial")
    private double resultadoAyudaEspecial;

    @Column(name = "restransporte")
    private double resultadoTransporte;

    @Column(name = "resteletrabajo")
    private double resultadoTeletrabajo;

    @Column(name = "resdieta")
    private double resultadoDieta;

    @Column(name = "resdietamedia")
    private double resultadoDietaMedia;

    @Column(name = "restotaldevengado")
    private double resultadoTotalDevengado;
    //-------------------------//

    // DEDUCCIONES //
    // CONTINGENCIAS COMUNES //
    @Column(name = "contingenciascomunes")
    private double contingenciasComunes;

    @Column(name = "porcontingenciascomunes")
    private double porcentajeContingenciasComunes;

    @Column(name = "rescontingenciascomunes")
    private double resultadoContingenciasComunes;

    // DESEMPLEO //
    @Column(name = "desempleo")
    private double desempleo;

    @Column(name = "pordesempleo")
    private double porcentajeDesempleo;

    @Column(name = "resdesempleo")
    private double resultadoDesempleo;

    // FORMACION PROFESIONAl //
    @Column(name = "fptrab")
    private double fpTrabajador;

    @Column(name = "porfp")
    private double porcentajeFp;

    @Column(name = "resfp")
    private double resultadoFp;

    // HORAS EXTRA //
    @Column(name = "horasextradeduc")
    private double horasExtraDeducciones;

    @Column(name = "porhorasextradeduc")
    private double porcentajeHorasExtraDeducciones;

    @Column(name = "reshorasextradeduc")
    private double resultadoHorasExtraDeducciones;

    // HORAS EXTRA FUERZA MAYOR //
    @Column(name = "horasextramayordeduc")
    private double horasExtraMayorDeducciones;

    @Column(name = "porhorasextramayordeduc")
    private double porcentajeHorasExtraMayorDeducciones;

    @Column(name = "reshorasextramayordeduc")
    private double resultadoHorasExtramayorDeducciones;

    // TOTAL APORTACIONES //
    @Column(name = "restotalaportaciones")
    private double resultadoTotalAportaciones;

    // IRPF //
    @Column(name = "irpf")
    private double irpf;

    @Column(name = "porirpf")
    private int porcentajeIrpf;

    @Column(name = "resirpf")
    private double resultadoIrpf;

    // TOTAL DEDUCIR //
    @Column(name = "restotaldeducir")
    private double resultadoTotalDeducir;
    //-------------------------//

    // TOTAL PERCIBIR //
    @Column(name = "restotalpercibir")
    private double resultadoTotalPercibir;
    //-------------------------//

    // APORTACIONES EMPRESA //
    // PUNTO 1 //
    @Column(name = "resremuneracionmensual")
    private double resultadoRemuneracionMensual;

    @Column(name = "resprorratapagasextra")
    private double resultadoProrrataPagasExtra;

    @Column(name = "resbasecontingenciascomunes")
    private double resultadoBaseContingenciasComunes;

    @Column(name = "portipoempresa")
    private double porcentajeTipoEmpresa;

    @Column(name = "aportacionempresa")
    private double aportacionEmpresa;

    // PUNTO 2 //
    // AT Y EP //
    @Column(name = "atyep")
    private double accidenteTrabajoYEnfermedadProfesional;

    @Column(name = "poratyep")
    private double porcentajeAccidenteTrabajoYEnfermedadProfesional;

    @Column(name = "resatyep")
    private double resultadoAccidenteTrabajoYEnfermedadProfesional;

    // DESEMPLEO //
    @Column(name = "desempleoempresa")
    private double desempleoEmpresa;

    @Column(name = "pordesempleoempresa")
    private double porcentajeDesempleoEmpresa;

    @Column(name = "resdesempleoempresa")
    private double resultadoDesempleoEmpresa;

    // FP //
    @Column(name = "fp")
    private double fp;

    @Column(name = "porfpempresa")
    private double porcentajeFpEmpresa;

    @Column(name = "resfpempresa")
    private double resultadoFpEmpresa;

    // FOGASA //
    @Column(name = "fogasa")
    private double fogasa;

    @Column(name = "porfogasa")
    private double porcentajeFogasa;

    @Column(name = "resfogasa")
    private double resultadoFogasa;

    // PUNTO 3 //
    @Column(name = "horasextra")
    private double horasExtraEmpresa;

    @Column(name = "porhorasextra")
    private double porcentajeHorasExtraEmpresa;

    @Column(name = "reshorasextraempresa")
    private double resultadoHorasExtraEmpresa;

    // PUNTO 4 //
    @Column(name = "horasextramayor")
    private double horasExtraMayorEmpresa;

    @Column(name = "porhorasextramayor")
    private double porcentajeHorasExtraMayorEmpresa;

    @Column(name = "reshorasextramayorempresa")
    private double resultadoHorasExtraMayorEmpresa;

    // PUNTO 5 //
    @Column(name = "baseirpf")
    private double baseIrpf;

    @Column(name = "totalaportacionempresa")
    private double totalAportacionesEmpresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpresaNomina() {
        return nombreEmpresaNomina;
    }

    public void setNombreEmpresaNomina(String nombreEmpresaNomina) {
        this.nombreEmpresaNomina = nombreEmpresaNomina;
    }

    public String getDomicilioEmpresaNomina() {
        return domicilioEmpresaNomina;
    }

    public void setDomicilioEmpresaNomina(String domicilioEmpresaNomina) {
        this.domicilioEmpresaNomina = domicilioEmpresaNomina;
    }

    public String getCifEmpresaNomina() {
        return cifEmpresaNomina;
    }

    public void setCifEmpresaNomina(String cifEmpresaNomina) {
        this.cifEmpresaNomina = cifEmpresaNomina;
    }

    public String getCccEmpresaNomina() {
        return cccEmpresaNomina;
    }

    public void setCccEmpresaNomina(String cccEmpresaNomina) {
        this.cccEmpresaNomina = cccEmpresaNomina;
    }

    public String getNombreTrabajadorNomina() {
        return nombreTrabajadorNomina;
    }

    public void setNombreTrabajadorNomina(String nombreTrabajadorNomina) {
        this.nombreTrabajadorNomina = nombreTrabajadorNomina;
    }

    public String getNifTrabajadorNomina() {
        return nifTrabajadorNomina;
    }

    public void setNifTrabajadorNomina(String nifTrabajadorNomina) {
        this.nifTrabajadorNomina = nifTrabajadorNomina;
    }

    public String getNusTrabajadorNomina() {
        return nusTrabajadorNomina;
    }

    public void setNusTrabajadorNomina(String nusTrabajadorNomina) {
        this.nusTrabajadorNomina = nusTrabajadorNomina;
    }

    public String getCatTrabajadorNomina() {
        return catTrabajadorNomina;
    }

    public void setCatTrabajadorNomina(String catTrabajadorNomina) {
        this.catTrabajadorNomina = catTrabajadorNomina;
    }

    public String getGrupoCotizacionTrabajadorNomina() {
        return grupoCotizacionTrabajadorNomina;
    }

    public void setGrupoCotizacionTrabajadorNomina(String grupoCotizacionTrabajadorNomina) {
        this.grupoCotizacionTrabajadorNomina = grupoCotizacionTrabajadorNomina;
    }

    public String getFechaInicioNomina() {
        return fechaInicioNomina;
    }

    public void setFechaInicioNomina(String fechaInicioNomina) {
        this.fechaInicioNomina = fechaInicioNomina;
    }

    public String getFechaFinNomina() {
        return fechaFinNomina;
    }

    public void setFechaFinNomina(String fechaFinNomina) {
        this.fechaFinNomina = fechaFinNomina;
    }

    public int getNumeroDiasNomina() {
        return numeroDiasNomina;
    }

    public void setNumeroDiasNomina(int numeroDiasNomina) {
        this.numeroDiasNomina = numeroDiasNomina;
    }

    public double getResultadoSalarioBase() {
        return resultadoSalarioBase;
    }

    public void setResultadoSalarioBase(double resultadoSalarioBase) {
        this.resultadoSalarioBase = resultadoSalarioBase;
    }

    public double getResultadoHorasComplementarias() {
        return resultadoHorasComplementarias;
    }

    public void setResultadoHorasComplementarias(double resultadoHorasComplementarias) {
        this.resultadoHorasComplementarias = resultadoHorasComplementarias;
    }

    public double getResultadoHorasExtra() {
        return resultadoHorasExtra;
    }

    public void setResultadoHorasExtra(double resultadoHorasExtra) {
        this.resultadoHorasExtra = resultadoHorasExtra;
    }

    public double getResultadoHorasExtraMayor() {
        return resultadoHorasExtraMayor;
    }

    public void setResultadoHorasExtraMayor(double resultadoHorasExtraMayor) {
        this.resultadoHorasExtraMayor = resultadoHorasExtraMayor;
    }

    public double getResultadoAyudaEspecial() {
        return resultadoAyudaEspecial;
    }

    public void setResultadoAyudaEspecial(double resultadoAyudaEspecial) {
        this.resultadoAyudaEspecial = resultadoAyudaEspecial;
    }

    public double getResultadoTransporte() {
        return resultadoTransporte;
    }

    public void setResultadoTransporte(double resultadoTransporte) {
        this.resultadoTransporte = resultadoTransporte;
    }

    public double getResultadoTeletrabajo() {
        return resultadoTeletrabajo;
    }

    public void setResultadoTeletrabajo(double resultadoTeletrabajo) {
        this.resultadoTeletrabajo = resultadoTeletrabajo;
    }

    public double getResultadoDieta() {
        return resultadoDieta;
    }

    public void setResultadoDieta(double resultadoDieta) {
        this.resultadoDieta = resultadoDieta;
    }

    public double getResultadoDietaMedia() {
        return resultadoDietaMedia;
    }

    public void setResultadoDietaMedia(double resultadoDietaMedia) {
        this.resultadoDietaMedia = resultadoDietaMedia;
    }

    public double getResultadoTotalDevengado() {
        return resultadoTotalDevengado;
    }

    public void setResultadoTotalDevengado(double resultadoTotalDevengado) {
        this.resultadoTotalDevengado = resultadoTotalDevengado;
    }

    public double getContingenciasComunes() {
        return contingenciasComunes;
    }

    public void setContingenciasComunes(double contingenciasComunes) {
        this.contingenciasComunes = contingenciasComunes;
    }

    public double getPorcentajeContingenciasComunes() {
        return porcentajeContingenciasComunes;
    }

    public void setPorcentajeContingenciasComunes(double porcentajeContingenciasComunes) {
        this.porcentajeContingenciasComunes = porcentajeContingenciasComunes;
    }

    public double getResultadoContingenciasComunes() {
        return resultadoContingenciasComunes;
    }

    public void setResultadoContingenciasComunes(double resultadoContingenciasComunes) {
        this.resultadoContingenciasComunes = resultadoContingenciasComunes;
    }

    public double getDesempleo() {
        return desempleo;
    }

    public void setDesempleo(double desempleo) {
        this.desempleo = desempleo;
    }

    public double getPorcentajeDesempleo() {
        return porcentajeDesempleo;
    }

    public void setPorcentajeDesempleo(double porcentajeDesempleo) {
        this.porcentajeDesempleo = porcentajeDesempleo;
    }

    public double getResultadoDesempleo() {
        return resultadoDesempleo;
    }

    public void setResultadoDesempleo(double resultadoDesempleo) {
        this.resultadoDesempleo = resultadoDesempleo;
    }

    public double getFpTrabajador() {
        return fpTrabajador;
    }

    public void setFpTrabajador(double fpTrabajador) {
        this.fpTrabajador = fpTrabajador;
    }

    public double getPorcentajeFp() {
        return porcentajeFp;
    }

    public void setPorcentajeFp(double porcentajeFp) {
        this.porcentajeFp = porcentajeFp;
    }

    public double getResultadoFp() {
        return resultadoFp;
    }

    public void setResultadoFp(double resultadoFp) {
        this.resultadoFp = resultadoFp;
    }

    public double getHorasExtraDeducciones() {
        return horasExtraDeducciones;
    }

    public void setHorasExtraDeducciones(double horasExtraDeducciones) {
        this.horasExtraDeducciones = horasExtraDeducciones;
    }

    public double getPorcentajeHorasExtraDeducciones() {
        return porcentajeHorasExtraDeducciones;
    }

    public void setPorcentajeHorasExtraDeducciones(double porcentajeHorasExtraDeducciones) {
        this.porcentajeHorasExtraDeducciones = porcentajeHorasExtraDeducciones;
    }

    public double getResultadoHorasExtraDeducciones() {
        return resultadoHorasExtraDeducciones;
    }

    public void setResultadoHorasExtraDeducciones(double resultadoHorasExtraDeducciones) {
        this.resultadoHorasExtraDeducciones = resultadoHorasExtraDeducciones;
    }

    public double getHorasExtraMayorDeducciones() {
        return horasExtraMayorDeducciones;
    }

    public void setHorasExtraMayorDeducciones(double horasExtraMayorDeducciones) {
        this.horasExtraMayorDeducciones = horasExtraMayorDeducciones;
    }

    public double getPorcentajeHorasExtraMayorDeducciones() {
        return porcentajeHorasExtraMayorDeducciones;
    }

    public void setPorcentajeHorasExtraMayorDeducciones(double porcentajeHorasExtraMayorDeducciones) {
        this.porcentajeHorasExtraMayorDeducciones = porcentajeHorasExtraMayorDeducciones;
    }

    public double getResultadoHorasExtramayorDeducciones() {
        return resultadoHorasExtramayorDeducciones;
    }

    public void setResultadoHorasExtramayorDeducciones(double resultadoHorasExtramayorDeducciones) {
        this.resultadoHorasExtramayorDeducciones = resultadoHorasExtramayorDeducciones;
    }

    public double getResultadoTotalAportaciones() {
        return resultadoTotalAportaciones;
    }

    public void setResultadoTotalAportaciones(double resultadoTotalAportaciones) {
        this.resultadoTotalAportaciones = resultadoTotalAportaciones;
    }

    public double getIrpf() {
        return irpf;
    }

    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }

    public int getPorcentajeIrpf() {
        return porcentajeIrpf;
    }

    public void setPorcentajeIrpf(int porcentajeIrpf) {
        this.porcentajeIrpf = porcentajeIrpf;
    }

    public double getResultadoIrpf() {
        return resultadoIrpf;
    }

    public void setResultadoIrpf(double resultadoIrpf) {
        this.resultadoIrpf = resultadoIrpf;
    }

    public double getResultadoTotalDeducir() {
        return resultadoTotalDeducir;
    }

    public void setResultadoTotalDeducir(double resultadoTotalDeducir) {
        this.resultadoTotalDeducir = resultadoTotalDeducir;
    }

    public double getResultadoTotalPercibir() {
        return resultadoTotalPercibir;
    }

    public void setResultadoTotalPercibir(double resultadoTotalPercibir) {
        this.resultadoTotalPercibir = resultadoTotalPercibir;
    }

    public double getResultadoRemuneracionMensual() {
        return resultadoRemuneracionMensual;
    }

    public void setResultadoRemuneracionMensual(double resultadoRemuneracionMensual) {
        this.resultadoRemuneracionMensual = resultadoRemuneracionMensual;
    }

    public double getResultadoProrrataPagasExtra() {
        return resultadoProrrataPagasExtra;
    }

    public void setResultadoProrrataPagasExtra(double resultadoProrrataPagasExtra) {
        this.resultadoProrrataPagasExtra = resultadoProrrataPagasExtra;
    }

    public double getResultadoBaseContingenciasComunes() {
        return resultadoBaseContingenciasComunes;
    }

    public void setResultadoBaseContingenciasComunes(double resultadoBaseContingenciasComunes) {
        this.resultadoBaseContingenciasComunes = resultadoBaseContingenciasComunes;
    }

    public double getPorcentajeTipoEmpresa() {
        return porcentajeTipoEmpresa;
    }

    public void setPorcentajeTipoEmpresa(double porcentajeTipoEmpresa) {
        this.porcentajeTipoEmpresa = porcentajeTipoEmpresa;
    }

    public double getAportacionEmpresa() {
        return aportacionEmpresa;
    }

    public void setAportacionEmpresa(double aportacionEmpresa) {
        this.aportacionEmpresa = aportacionEmpresa;
    }

    public double getAccidenteTrabajoYEnfermedadProfesional() {
        return accidenteTrabajoYEnfermedadProfesional;
    }

    public void setAccidenteTrabajoYEnfermedadProfesional(double accidenteTrabajoYEnfermedadProfesional) {
        this.accidenteTrabajoYEnfermedadProfesional = accidenteTrabajoYEnfermedadProfesional;
    }

    public double getPorcentajeAccidenteTrabajoYEnfermedadProfesional() {
        return porcentajeAccidenteTrabajoYEnfermedadProfesional;
    }

    public void setPorcentajeAccidenteTrabajoYEnfermedadProfesional(double porcentajeAccidenteTrabajoYEnfermedadProfesional) {
        this.porcentajeAccidenteTrabajoYEnfermedadProfesional = porcentajeAccidenteTrabajoYEnfermedadProfesional;
    }

    public double getResultadoAccidenteTrabajoYEnfermedadProfesional() {
        return resultadoAccidenteTrabajoYEnfermedadProfesional;
    }

    public void setResultadoAccidenteTrabajoYEnfermedadProfesional(double resultadoAccidenteTrabajoYEnfermedadProfesional) {
        this.resultadoAccidenteTrabajoYEnfermedadProfesional = resultadoAccidenteTrabajoYEnfermedadProfesional;
    }

    public double getDesempleoEmpresa() {
        return desempleoEmpresa;
    }

    public void setDesempleoEmpresa(double desempleoEmpresa) {
        this.desempleoEmpresa = desempleoEmpresa;
    }

    public double getPorcentajeDesempleoEmpresa() {
        return porcentajeDesempleoEmpresa;
    }

    public void setPorcentajeDesempleoEmpresa(double porcentajeDesempleoEmpresa) {
        this.porcentajeDesempleoEmpresa = porcentajeDesempleoEmpresa;
    }

    public double getResultadoDesempleoEmpresa() {
        return resultadoDesempleoEmpresa;
    }

    public void setResultadoDesempleoEmpresa(double resultadoDesempleoEmpresa) {
        this.resultadoDesempleoEmpresa = resultadoDesempleoEmpresa;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
    }

    public double getPorcentajeFpEmpresa() {
        return porcentajeFpEmpresa;
    }

    public void setPorcentajeFpEmpresa(double porcentajeFpEmpresa) {
        this.porcentajeFpEmpresa = porcentajeFpEmpresa;
    }

    public double getResultadoFpEmpresa() {
        return resultadoFpEmpresa;
    }

    public void setResultadoFpEmpresa(double resultadoFpEmpresa) {
        this.resultadoFpEmpresa = resultadoFpEmpresa;
    }

    public double getFogasa() {
        return fogasa;
    }

    public void setFogasa(double fogasa) {
        this.fogasa = fogasa;
    }

    public double getPorcentajeFogasa() {
        return porcentajeFogasa;
    }

    public void setPorcentajeFogasa(double porcentajeFogasa) {
        this.porcentajeFogasa = porcentajeFogasa;
    }

    public double getResultadoFogasa() {
        return resultadoFogasa;
    }

    public void setResultadoFogasa(double resultadoFogasa) {
        this.resultadoFogasa = resultadoFogasa;
    }

    public double getHorasExtraEmpresa() {
        return horasExtraEmpresa;
    }

    public void setHorasExtraEmpresa(double horasExtraEmpresa) {
        this.horasExtraEmpresa = horasExtraEmpresa;
    }

    public double getPorcentajeHorasExtraEmpresa() {
        return porcentajeHorasExtraEmpresa;
    }

    public void setPorcentajeHorasExtraEmpresa(double porcentajeHorasExtraEmpresa) {
        this.porcentajeHorasExtraEmpresa = porcentajeHorasExtraEmpresa;
    }

    public double getResultadoHorasExtraEmpresa() {
        return resultadoHorasExtraEmpresa;
    }

    public void setResultadoHorasExtraEmpresa(double resultadoHorasExtraEmpresa) {
        this.resultadoHorasExtraEmpresa = resultadoHorasExtraEmpresa;
    }

    public double getHorasExtraMayorEmpresa() {
        return horasExtraMayorEmpresa;
    }

    public void setHorasExtraMayorEmpresa(double horasExtraMayorEmpresa) {
        this.horasExtraMayorEmpresa = horasExtraMayorEmpresa;
    }

    public double getPorcentajeHorasExtraMayorEmpresa() {
        return porcentajeHorasExtraMayorEmpresa;
    }

    public void setPorcentajeHorasExtraMayorEmpresa(double porcentajeHorasExtraMayorEmpresa) {
        this.porcentajeHorasExtraMayorEmpresa = porcentajeHorasExtraMayorEmpresa;
    }

    public double getResultadoHorasExtraMayorEmpresa() {
        return resultadoHorasExtraMayorEmpresa;
    }

    public void setResultadoHorasExtraMayorEmpresa(double resultadoHorasExtraMayorEmpresa) {
        this.resultadoHorasExtraMayorEmpresa = resultadoHorasExtraMayorEmpresa;
    }

    public double getBaseIrpf() {
        return baseIrpf;
    }

    public void setBaseIrpf(double baseIrpf) {
        this.baseIrpf = baseIrpf;
    }

    public double getTotalAportacionesEmpresa() {
        return totalAportacionesEmpresa;
    }

    public void setTotalAportacionesEmpresa(double totalAportacionesEmpresa) {
        this.totalAportacionesEmpresa = totalAportacionesEmpresa;
    }


}
