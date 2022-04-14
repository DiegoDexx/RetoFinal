DROP DATABASE IF EXISTS reto_nomina;
CREATE DATABASE reto_nomina CHARACTER SET utf8 COLLATE utf8_spanish_ci;


USE reto_nomina;

CREATE TABLE Convenio(
    IdConvenio INT PRIMARY KEY auto_increment,
    nombre_conv VARCHAR(200),
    tipo_conv VARCHAR(100) 

);

CREATE TABLE Empleado(
    IdEmpleado INT PRIMARY KEY auto_increment,
    nombre_emp VARCHAR(100) NOT NULL,
    apellido1_emp VARCHAR(100) NOT NULL,
    apellido2_emp VARCHAR(100),
    nif_empleado VARCHAR(9) UNIQUE NOT NULL,
    nus_empleado VARCHAR(12) UNIQUE NOT NULL,
    Grupo_profesional VARCHAR(100),
    Grupo_cotizacion VARCHAR(100),
    tipo_contrato ENUM('indefinido','temporal','formacion','practicas') NOT NULL,
    tipo_conv VARCHAR(100)

);

ALTER TABLE Empleado
    ADD CONSTRAINT FK_tipoconv FOREIGN KEY(tipo_conv) REFERENCES Convenio(tipo_conv); /*en caso de que no os vaya probarlo desde el terminal de ubuntu */
    



CREATE TABLE Empresa(
    IDEmpresa INT PRIMARY KEY auto_increment,
    nombre_empresa VARCHAR(30) NOT NULL,
    Domicilio VARCHAR(100),
    CIF_empresa INT(20) UNIQUE NOT NULL,
    CCC INT(11) UNIQUE NOT NULL
);

CREATE TABLE Nomina(
    IdEmpleado INT PRIMARY KEY auto_increment,  /* TODO SEPARADO EN BLOQUES SEGUN LA ESTRUCTURA DE UNA NOMINA */
    salarioBase DECIMAL(7,2) NOT NULL,
    HorasEx INT(5),                         /* NO ME DEJA IMPORTAR LA FOREIGN KEY IdEmpresa ya que no se pueden poner dos valores auto_increment en una tabla, 
                                                SQL NO LO DEJA POR DEFECTO, LO HE INTENTADO 9999 VECES, es imposible*/
    Complementos VARCHAR(200),
    TotalDevengado DECIMAL (7,2) NOT NULL,
    TotalDeducir DECIMAL(7,2),
    TotalPercibir DECIMAL(7,2),
   
    BCC DECIMAL(7,2),
    IRPF DECIMAL NOT NULL,
    Plus_convenio DECIMAL,

    FechaInicio DATE NOT NULL,
    FechaFin DATE NOT NULL,
    FechaFirmaIncio DATE NOT NULL,
    FechaFirmaFinal DATE NOT NULL,
	
);

ALTER TABLE Nomina
	 ADD CONSTRAINT FK_IdEmpleado FOREIGN KEY(IdEmpleado) REFERENCES Empleado(IdEmpleado);
     

CREATE TABLE Contrato(
    IdEmpleado INT PRIMARY KEY auto_increment,
    FechaInicioCont DATE NOT NULL,
    FechaFinCont DATE NOT NULL,
	

);

ALTER TABLE Contrato 
	 ADD CONSTRAINT FK_IdEmpleado2 FOREIGN KEY(IdEmpleado) REFERENCES Empleado(IdEmpleado);