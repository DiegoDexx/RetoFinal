DROP DATABASE IF EXISTS reto_nomina;
CREATE DATABASE reto_nomina CHARACTER SET latin1 COLLATE latin1_spanish_ci;


USE reto_nomina;


CREATE TABLE Empresa(
    IdEmpresa INT PRIMARY KEY auto_increment,
    nomEmpresa VARCHAR(100) NOT NULL,
    direccion VARCHAR(100),
    convenio ENUM('CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','CONVENIO COLECTIVO ESTATAL DE LA INDUSTRIA, LAS NUEVAS TECNOLOGÍAS Y LOS SERVICIOS DEL SECTOR DEL METAL') NOT NULL,
    cif VARCHAR(20) UNIQUE NOT NULL,
    ccc VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE Empleado(
    IdEmpleado INT PRIMARY KEY auto_increment,
    nombreTrab VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    nif VARCHAR(9) UNIQUE NOT NULL,
    nus VARCHAR(12) UNIQUE NOT NULL,
    tipoContrato ENUM('indefinido','temporal','formacion','practicas') NOT NULL, 
    fecIniContrato DATE NOT NULL, 
    fecFinContrato DATE, 
    IdEmpresa INT,
    CONSTRAINT fk_empresa_empleado FOREIGN KEY (IdEmpresa) REFERENCES Empresa (IdEmpresa) ON DELETE NO ACTION ON UPDATE CASCADE,
    catProf VARCHAR(200), 
    grupoCot VARCHAR(200), 
    irpf INT NOT NULL DEFAULT 2
);

CREATE TABLE Nomina(
    IdNomina INT PRIMARY KEY auto_increment,
    IdEmpleado INT,
    CONSTRAINT fk_nomina_empleado FOREIGN KEY (IdEmpleado) REFERENCES Empleado (IdEmpleado) ON DELETE NO ACTION ON UPDATE CASCADE,

    -- EMPRESA --
    nomEmpresaNomina VARCHAR(100) NOT NULL,
    direccionEmpresaNomina VARCHAR(100) NOT NULL,
    cifEmpresaNomina INT NOT NULL,
    cccEmpresaNomina INT NOT NULL,

    -- EMPLEADO --
    nomTrabNomina VARCHAR(100) NOT NULL,
    nifTrabNomina VARCHAR(9) NOT NULL,
    nufTrabNomina VARCHAR(12) NOT NULL,
    catTrabNomina VARCHAR(200) NOT NULL,
    grupCotTrabNomina VARCHAR(200) NOT NULL,

    -- PERIODO --
    fecIniNomina DATE NOT NULL,
    fecFinNomina DATE NOT NULL,
    nDiasNomina INT NOT NULL,

    -- DEVENGOS --
    resSalBase DECIMAL(7,2) NOT NULL,

    -- Percepciones salariales --
    resHorasComp DECIMAL(7,2),
    resHorasExtra DECIMAL(7,2),
    resHorasExtraMayor DECIMAL(7,2),
    -- Percepciones no salariales --
    resAyudaEspecial DECIMAL(7,2),
    resTransporte DECIMAL(7,2),
    resTeletrabajo DECIMAL(7,2),
    resDieta DECIMAL(7,2),
    resDietaMedia DECIMAL(7,2),

    resTotalDevengado DECIMAL(7,2) NOT NULL,
  
    -- DEDUCCIONES --

    -- APORTACIONES TRABAJADOR A LA S.S --
    contingenciasComunes DECIMAL(7,2) NOT NULL,
    porContingenciasComunes DECIMAL(7,2) NOT NULL,
    resContingenciasComunes DECIMAL(7,2) NOT NULL,

    desempleo DECIMAL(7,2) NOT NULL,
    porDesempleo DECIMAL(7,2) NOT NULL,
    resDesempleo DECIMAL(7,2) NOT NULL,

    fpTrab DECIMAL(7,2) NOT NULL,
    porFP DECIMAL(7,2) NOT NULL,
    resFP DECIMAL(7,2) NOT NULL,

    horasExtraDeduc DECIMAL(7,2),
    porHorasExtraDeduc DECIMAL(7,2) NOT NULL,
    resHorasExtraDeduc DECIMAL(7,2),

    horasExtraMayorDeduc DECIMAL(7,2),
    porHorasExtraMayorDeduc DECIMAL(7,2) NOT NULL,
    resHorasExtraMayorDeduc DECIMAL(7,2),

    resTotalAportaciones DECIMAL(7,2) NOT NULL,
    

    irpf DECIMAL(7,2) NOT NULL,
    porIRPF INT NOT NULL,
    resIRPF DECIMAL(7,2) NOT NULL,

    resTotalDeducir DECIMAL(7,2) NOT NULL,

    -- TOTAL A PERCIBIR --
    resTotalPercibir DECIMAL(7,2) NOT NULL,
    
    
    -- APORTACION EMPRESA --
    -- Base de cotización por Contingencias Comunes --
    resRemuneracionMensual DECIMAL(7,2),
    resProrrataPagasExtra DECIMAL(7,2),
    resBaseContingenciasComunes DECIMAL (7,2),

    porTipoEmpresa DECIMAL(7,2),
    aportacionEmpresa DECIMAL(7,2),

    -- Base de contingencias profesionales --
    ATyEP DECIMAL(7,2),
    porATyEP DECIMAL(7,2),
    resATyEP DECIMAL(7,2),

    desempleoEmpresa DECIMAL(7,2),
    porDesempleoEmpresa DECIMAL(7,2),
    resDesempleoEmpresa DECIMAL(7,2),

    FP DECIMAL(7,2),
    porFPEmpresa DECIMAL(7,2),
    resFPEmpresa DECIMAL(7,2),

    FOGASA DECIMAL(7,2),
    porFOGASA DECIMAL(7,2),
    resFPGASA DECIMAL(7,2),

    horasExtra DECIMAL(7,2),
    porHorasExtra DECIMAL(7,2),
    resHorasExtraEmpresa DECIMAL(7,2),

    horasExtraMayor DECIMAL(7,2),
    porHorasExtraMayor DECIMAL(7,2),
    resHorasExtraMayorEmpresa DECIMAL(7,2),

    baseIRPF DECIMAL(7,2),

    -- TOTAL --
    totalAportacionEmpresa DECIMAL(7,2)
);

INSERT INTO Empresa
VALUES (NULL,'Abogados Lopez','Illueca 8','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','48954456545446531878','23746934321'),
       (NULL,'Metales Paco e Hijos','Petanca 62','CONVENIO COLECTIVO ESTATAL DE LA INDUSTRIA, LAS NUEVAS TECNOLOGÍAS Y LOS SERVICIOS DEL SECTOR DEL METAL','48954456545426531878','23756934321')
;

INSERT INTO Empleado
VALUES (NULL,'Ismael','Molina','Ybarra','74439236S','123456789123','indefinido','2022/05/25',NULL,'1','XXX','3','4'),
       (NULL,'Daniel','Soler','Alcala','33478997J','123789456123','practicas','2022/07/01',NULL,'1','XXX2','2','5'),
       (NULL,'Laura','Orts','Ramon','48756923J','789465132789','temporal','2022/05/25','2022/07/01','2','XXX3','7',DEFAULT)
;