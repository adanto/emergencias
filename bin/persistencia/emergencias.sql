DROP TABLE Privada IF EXISTS;
DROP TABLE BHospital IF EXISTS;
DROP TABLE Paciente IF EXISTS;
DROP TABLE Sintoma IF EXISTS;
DROP TABLE ServicioEmergencia IF EXISTS;
DROP TABLE RegistroEmergencia IF EXISTS;
DROP TABLE Especialidad IF EXISTS;
DROP TABLE Hospital IF EXISTS;
DROP TABLE Ambulancia IF EXISTS;

CREATE TABLE Ambulancia (
       numRegistro INT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , id INT NOT NULL
     , PRIMARY KEY (numRegistro)
);

CREATE TABLE Hospital (
       nombre CHAR(20) NOT NULL
     , direccion CHAR(20)
     , latitud DOUBLE
     , longitud DOUBLE
     , PRIMARY KEY (nombre)
);

CREATE TABLE Especialidad (
       nombre CHAR(20) NOT NULL
     , PRIMARY KEY (nombre)
);

CREATE TABLE RegistroEmergencia (
       latitud DOUBLE
     , logitud DOUBLE
     , fecha CHAR(11)
     , hora CHAR(5)
     , id INT NOT NULL
     , PRIMARY KEY (id)
);

CREATE TABLE ServicioEmergencia (
);

CREATE TABLE Sintoma (
       estado CHAR(10)
     , descripcion CHAR(30)
     , duracion INT
     , nombre CHAR(20) NOT NULL
     , PRIMARY KEY (nombre)
);

CREATE TABLE Paciente (
       DNI CHAR(10) NOT NULL
     , NOMBRE CHAR(20)
     , APELLIDOS CHAR(30)
     , DIRECCION CHAR(30)
     , TELEFONO INT
     , EDAD INT
     , SEXO CHAR(1)
     , PRIMARY KEY (DNI)
);

CREATE TABLE BHospital (
       numRegistro INT NOT NULL
     , nombre CHAR(20) NOT NULL
     , CONSTRAINT FK_BHospital_1 FOREIGN KEY (numRegistro)
                  REFERENCES Ambulancia (numRegistro)
     , CONSTRAINT FK_BHospital_2 FOREIGN KEY (nombre)
                  REFERENCES Hospital (nombre)
);

CREATE TABLE Privada (
       "compañia" CHAR(20) NOT NULL
     , numRegistro INT NOT NULL
     , PRIMARY KEY ("compañia")
     , CONSTRAINT FK_Privada_1 FOREIGN KEY (numRegistro)
                  REFERENCES Ambulancia (numRegistro)
);

