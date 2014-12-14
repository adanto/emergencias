DROP TABLE Emergencia IF EXISTS;
DROP TABLE Sintoma IF EXISTS;
DROP TABLE Especialidad IF EXISTS;
DROP TABLE Ambulancia IF EXISTS;
DROP TABLE Paciente IF EXISTS;
DROP TABLE Hospital IF EXISTS;

CREATE TABLE Hospital (
       nombre CHAR(20) NOT NULL
     , direccion CHAR(20)
     , latitud DOUBLE
     , longitud DOUBLE
     , PRIMARY KEY (nombre)
);

CREATE TABLE Paciente (
       dni CHAR(10) NOT NULL
     , nombre CHAR(20)
     , apellidos CHAR(30)
     , direccion CHAR(30)
     , telefono INT
     , edad INT
     , sexo CHAR(1)
     , PRIMARY KEY (dni)
);

CREATE TABLE Ambulancia (
       numRegistro INT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , disponibilidad BIT
     , tipo CHAR(1)
     , "compañia" CHAR(10)
     , Nombre CHAR(20) NOT NULL
     , PRIMARY KEY (numRegistro)
     , CONSTRAINT FK_Ambulancia_1 FOREIGN KEY (Nombre)
                  REFERENCES Hospital (nombre)
);

CREATE TABLE Especialidad (
       id CHAR(10) NOT NULL
     , nombre CHAR(20) NOT NULL
     , PRIMARY KEY (id, nombre)
     , CONSTRAINT FK_Especialidad_1 FOREIGN KEY (nombre)
                  REFERENCES Hospital (nombre)
);

CREATE TABLE Sintoma (
       codSintoma CHAR(10) NOT NULL
     , estado CHAR(10)
     , descripcion CHAR(30)
     , duracion DOUBLE
     , id CHAR(10)
     , nombre CHAR(20) NOT NULL
     , PRIMARY KEY (codSintoma)
     , CONSTRAINT FK_Sintoma_1 FOREIGN KEY (id, nombre)
                  REFERENCES Especialidad (id, nombre)
);

CREATE TABLE Emergencia (
       id CHAR(10) NOT NULL
     , longitud DOUBLE
     , latitud DOUBLE
     , fecha CHAR(10)
     , hora CHAR(10)
     , codSintoma CHAR(10) NOT NULL
     , nombre CHAR(20) NOT NULL
     , numRegistro INT NOT NULL
     , dni CHAR(10) NOT NULL
     , PRIMARY KEY (id)
     , CONSTRAINT FK_Emergencia_2 FOREIGN KEY (nombre)
                  REFERENCES Hospital (nombre)
     , CONSTRAINT FK_Emergencia_3 FOREIGN KEY (numRegistro)
                  REFERENCES Ambulancia (numRegistro)
     , CONSTRAINT FK_Emergencia_1 FOREIGN KEY (codSintoma)
                  REFERENCES Sintoma (codSintoma)
     , CONSTRAINT FK_Emergencia_4 FOREIGN KEY (dni)
                  REFERENCES Paciente (dni)
);

