DROP DATABASE IF EXISTS reto_nomina;
CREATE DATABASE reto_nomina CHARACTER SET utf8 COLLATE utf8_spanish_ci;


USE reto_nomina;



CREATE TABLE Empleado(
    IdEmpleado INT PRIMARY KEY auto_increment,
    nombre_emp VARCHAR(100) NOT NULL,
    apellido1_emp VARCHAR(100) NOT NULL,
    apellido2_emp VARCHAR(100),
    nif_empleado VARCHAR(9) UNIQUE NOT NULL,
    nus_empleado VARCHAR(12) UNIQUE NOT NULL,
    tipo_contrato ENUM('indefinido','temporal','formacion','practicas') NOT NULL
    
);


CREATE TABLE Empresa(
    IdEmpresa INT PRIMARY KEY auto_increment,
    nombre_empresa VARCHAR(30) NOT NULL,
    Domicilio VARCHAR(100),
    Convenio VARCHAR(100) NOT NULL,
    CIF_empresa INT(20) UNIQUE NOT NULL,
    CCC INT(11) UNIQUE NOT NULL
);

CREATE TABLE Nomina(
    idNomina INT  auto_increment,
    IdEmpresa INT,
    IdEmpleado INT,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,   
    res_Horas_comp DECIMAL(7,2),
    res_Horas_extra DECIMAL(7,2),
    res_h_extra_mayor DECIMAL(7,2),
    res_Transporte DECIMAL(7,2),
    res_Teletrabajo DECIMAL(7,2),
    res_Ayuda_especial DECIMAL(7,2),
    res_dieta_comp DECIMAL(7,2),
    res_dieta_media  DECIMAL(7,2),
    grupo_cot VARCHAR(100),
    grupo_prof_emp VARCHAR (100),
    PRIMARY KEY (idNomina, IdEmpresa, IdEmpleado, fecha_inicio, fecha_final)
 
	
);

ALTER TABLE Nomina
ADD CONSTRAINT FK_idEmpresa FOREIGN KEY(IdEmpresa) REFERENCES Empresa(IdEmpresa),
ADD CONSTRAINT FK_idEmpleado FOREIGN KEY(IdEmpleado) REFERENCES Empleado(IdEmpleado);

CREATE TABLE Informacion(
    IdEmpresa INT,
    IdEmpleado INT,
    fecha_inicio DATE,
    fecha_final DATE,   
    Horas_comp DECIMAL (7,2),
    Horas_extra DECIMAL (7,2),
    grupo_cot VARCHAR(100),
    grupo_prof_emp VARCHAR (100),
    horas_extra_mayor DECIMAL(7,2),
    Transporte DECIMAL(7,2),
    Teletrabajo DECIMAL(7,2),
    Ayuda_especial DECIMAL(7,2),
    dieta_comp DECIMAL(7,2),
    dieta_media  DECIMAL(7,2),
    PRIMARY KEY (IdEmpresa, IdEmpleado, fecha_inicio, fecha_final)

);

ALTER TABLE Informacion
ADD CONSTRAINT FK_idEmpresa_2 FOREIGN KEY(IdEmpresa) REFERENCES Empresa(IdEmpresa),
ADD CONSTRAINT FK_idEmpleado_2 FOREIGN KEY(IdEmpleado) REFERENCES Empleado(IdEmpleado);



