DROP DATABASE IF EXISTS reto_nomina;
CREATE DATABASE reto_nomina CHARACTER SET latin1 COLLATE latin1_spanish_ci;


USE reto_nomina;


CREATE TABLE empresa(
    id INT PRIMARY KEY auto_increment,
    nomempresa VARCHAR(100) NOT NULL,
    direccion VARCHAR(100),
    convenio ENUM('CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','CONVENIO COLECTIVO ESTATAL DE LA INDUSTRIA, LAS NUEVAS TECNOLOGÍAS Y LOS SERVICIOS DEL SECTOR DEL METAL') NOT NULL,
    cif VARCHAR(20) UNIQUE NOT NULL,
    ccc VARCHAR(11) UNIQUE NOT NULL
);

CREATE TABLE empleado(
    id INT PRIMARY KEY auto_increment,
    nombretrab VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    nif VARCHAR(9) UNIQUE NOT NULL,
    nus VARCHAR(12) UNIQUE NOT NULL,
    tipocontrato ENUM('indefinido','temporal','formacion','practicas') NOT NULL, 
    fecinicontrato DATE NOT NULL, 
    fecfincontrato DATE, 
    idempresa INT,
    CONSTRAINT fk_empresa_empleado FOREIGN KEY (idempresa) REFERENCES empresa (id) ON DELETE NO ACTION ON UPDATE CASCADE,
    grupocot VARCHAR(200), 
    irpf INT NOT NULL DEFAULT 2
);

CREATE TABLE nomina(
    id INT PRIMARY KEY auto_increment,
    idempleado INT,
    CONSTRAINT fk_nomina_empleado FOREIGN KEY (idempleado) REFERENCES empleado (id) ON DELETE NO ACTION ON UPDATE CASCADE,

    -- EMPRESA --
    nomempresanomina VARCHAR(100) NOT NULL,
    direccionempresanomina VARCHAR(100) NOT NULL,
    cifempresanomina VARCHAR(20) NOT NULL,
    cccempresanomina VARCHAR(11) NOT NULL,

    -- EMPLEADO --
    nomtrabnomina VARCHAR(100) NOT NULL,
    niftrabnomina VARCHAR(9) NOT NULL,
    nuftrabnomina VARCHAR(12) NOT NULL,
    cattrabnomina VARCHAR(200) NOT NULL,
    grupcottrabnomina VARCHAR(200) NOT NULL,

    -- PERIODO --
    fecininomina DATE NOT NULL,
    fecfinnomina DATE NOT NULL,
    ndiasnomina INT NOT NULL,

    -- DEVENGOS --
    ressalbase DECIMAL(7,2) NOT NULL,

    -- Percepciones salariales --
    reshorascomp DECIMAL(7,2),
    reshorasextra DECIMAL(7,2),
    reshorasextramayor DECIMAL(7,2),
    -- Percepciones no salariales --
    resayudaespecial DECIMAL(7,2),
    restransporte DECIMAL(7,2),
    resteletrabajo DECIMAL(7,2),
    resdieta DECIMAL(7,2),
    resdietamedia DECIMAL(7,2),

    restotaldevengado DECIMAL(7,2) NOT NULL,
  
    -- DEDUCCIONES --

    -- APORTACIONES TRABAJADOR A LA S.S --
    contingenciascomunes DECIMAL(7,2) NOT NULL,
    porcontingenciascomunes DECIMAL(7,2) NOT NULL,
    rescontingenciascomunes DECIMAL(7,2) NOT NULL,

    desempleo DECIMAL(7,2) NOT NULL,
    pordesempleo DECIMAL(7,2) NOT NULL,
    resdesempleo DECIMAL(7,2) NOT NULL,

    fptrab DECIMAL(7,2) NOT NULL,
    porfp DECIMAL(7,2) NOT NULL,
    resfp DECIMAL(7,2) NOT NULL,

    horasextradeduc DECIMAL(7,2),
    porhorasextradeduc DECIMAL(7,2) NOT NULL,
    reshorasextradeduc DECIMAL(7,2),

    horasextramayordeduc DECIMAL(7,2),
    porhorasextramayordeduc DECIMAL(7,2) NOT NULL,
    reshorasextramayordeduc DECIMAL(7,2),

    restotalaportaciones DECIMAL(7,2) NOT NULL,
    

    irpf DECIMAL(7,2) NOT NULL,
    porirpf INT NOT NULL,
    resirpf DECIMAL(7,2) NOT NULL,

    restotaldeducir DECIMAL(7,2) NOT NULL,

    -- TOTAL A PERCIBIR --
    restotalpercibir DECIMAL(7,2) NOT NULL,
    
    
    -- APORTACION EMPRESA --
    -- Base de cotización por Contingencias Comunes --
    resremuneracionmensual DECIMAL(7,2),
    resprorratapagasextra DECIMAL(7,2),
    resbasecontingenciascomunes DECIMAL (7,2),

    portipoempresa DECIMAL(7,2),
    aportacionempresa DECIMAL(7,2),

    -- Base de contingencias profesionales --
    atyep DECIMAL(7,2),
    poratyep DECIMAL(7,2),
    resatyep DECIMAL(7,2),

    desempleoempresa DECIMAL(7,2),
    pordesempleoempresa DECIMAL(7,2),
    resdesempleoempresa DECIMAL(7,2),

    fp DECIMAL(7,2),
    porfpempresa DECIMAL(7,2),
    resfpempresa DECIMAL(7,2),

    fogasa DECIMAL(7,2),
    porfogasa DECIMAL(7,2),
    resfogasa DECIMAL(7,2),

    horasextra DECIMAL(7,2),
    porhorasextra DECIMAL(7,2),
    reshorasextraempresa DECIMAL(7,2),

    horasextramayor DECIMAL(7,2),
    porhorasextramayor DECIMAL(7,2),
    reshorasextramayorempresa DECIMAL(7,2),

    baseirpf DECIMAL(7,2),

    -- TOTAL --
    totalaportacionempresa DECIMAL(7,2)
);

INSERT INTO empresa
VALUES (NULL,'Abogados Lopez','Illueca 8','CONVENIO COLECTIVO PROVINCIAL DE OFICINAS Y DESPACHOS DE ALICANTE','48954456545446531878','23746934321'),
       (NULL,'Metales Paco e Hijos','Petanca 62','CONVENIO COLECTIVO ESTATAL DE LA INDUSTRIA, LAS NUEVAS TECNOLOGÍAS Y LOS SERVICIOS DEL SECTOR DEL METAL','48954456545426531878','23756934321')
;

INSERT INTO empleado
VALUES (NULL,'Ismael','Molina','Ybarra','74439236S','123456789123','indefinido','2022/05/25',NULL,'1','4 2 A','4'),
       (NULL,'Daniel','Soler','Alcala','33478997J','123789456123','practicas','2022/07/01',NULL,'1','3 1 B','5'),
       (NULL,'Laura','Orts','Ramon','48756923J','789465132789','temporal','2022/05/25','2022/07/01','2','1 1',DEFAULT)
;
