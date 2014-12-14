DROP TABLE EMERGENCIA IF EXISTS;
DROP TABLE AMBULANCIA IF EXISTS;
DROP TABLE SINTOMA IF EXISTS;
DROP TABLE ESPECIALIDAD IF EXISTS;
DROP TABLE PACIENTE IF EXISTS;
DROP TABLE HOSPITAL IF EXISTS;

CREATE TABLE HOSPITAL (
       nombreH CHAR(20) NOT NULL
     , direccion CHAR(30)
     , latitud DOUBLE
     , longitud DOUBLE
     , PRIMARY KEY (nombreH)
);

CREATE TABLE PACIENTE (
       DNI CHAR(10) NOT NULL
     , nombre CHAR(20)
     , apellidos CHAR(30)
     , direccion CHAR(30)
     , telefono INT
     , int INT
     , sexo CHAR(1)
     , PRIMARY KEY (DNI)
);

CREATE TABLE ESPECIALIDAD (
       codEsp CHAR(20) NOT NULL
     , nombreH CHAR(20) NOT NULL
     , PRIMARY KEY (codEsp, nombreH)
     , CONSTRAINT FK_ESPECIALIDAD_1 FOREIGN KEY (nombreH)
                  REFERENCES HOSPITAL (nombreH)
);

CREATE TABLE SINTOMA (
       codSintoma CHAR(20) NOT NULL
     , estado CHAR(10)
     , descripcion CHAR(20)
     , duracion DOUBLE
     , codEsp CHAR(20)
     , nombreH CHAR(20)
     , PRIMARY KEY (codSintoma)
     , CONSTRAINT FK_SINTOMA_1 FOREIGN KEY (codEsp, nombreH)
                  REFERENCES ESPECIALIDAD (codEsp, nombreH)
);

CREATE TABLE AMBULANCIA (
       numRegistro INT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , tipo CHAR(1)
     , "compañia" CHAR(20)
     , disponibilidad BIT
     , nombreH CHAR(20)
     , PRIMARY KEY (numRegistro)
     , CONSTRAINT FK_AMBULANCIA_1 FOREIGN KEY (nombreH)
                  REFERENCES HOSPITAL (nombreH)
);

CREATE TABLE EMERGENCIA (
       codEmergencia CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , fecha CHAR(10)
     , hora CHAR(10)
     , codSintoma CHAR(20)
     , nombreH CHAR(20)
     , DNI CHAR(10)
     , CONSTRAINT FK_EMERGENCIA_1 FOREIGN KEY (codSintoma)
                  REFERENCES SINTOMA (codSintoma)
     , CONSTRAINT FK_EMERGENCIA_2 FOREIGN KEY (nombreH)
                  REFERENCES HOSPITAL (nombreH)
     , CONSTRAINT FK_EMERGENCIA_3 FOREIGN KEY (DNI)
                  REFERENCES PACIENTE (DNI)
);

