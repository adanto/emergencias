DROP TABLE SINTOMA IF EXISTS;
DROP TABLE EMERGENCIA IF EXISTS;
DROP TABLE AMBULANCIA IF EXISTS;
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

CREATE TABLE AMBULANCIA (
       numRegistro INT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , tipo CHAR(1)
     , "compa�ia" CHAR(20)
     , disponibilidad BIT
     , nombreH CHAR(20)
     , PRIMARY KEY (numRegistro)
     , CONSTRAINT FK_AMBULANCIA_1 FOREIGN KEY (nombreH)
                  REFERENCES HOSPITAL (nombreH)
);

CREATE TABLE EMERGENCIA (
       codEmergencia INT NOT NULL
     , latitud DOUBLE
     , longitud DOUBLE
     , fecha CHAR(10)
     , hora CHAR(10)
     , nombreH CHAR(20)
     , DNI CHAR(10)
     , numRegistro INT
     , PRIMARY KEY (codEmergencia)
     , CONSTRAINT FK_EMERGENCIA_2 FOREIGN KEY (nombreH)
                  REFERENCES HOSPITAL (nombreH)
     , CONSTRAINT FK_EMERGENCIA_3 FOREIGN KEY (DNI)
                  REFERENCES PACIENTE (DNI)
     , CONSTRAINT FK_EMERGENCIA_4 FOREIGN KEY (numRegistro)
                  REFERENCES AMBULANCIA (numRegistro)
);

CREATE TABLE SINTOMA (
       codSintoma CHAR(20) NOT NULL
     , estado CHAR(10)
     , descripcion CHAR(20)
     , duracion DOUBLE
     , codEsp CHAR(20)
     , nombreH CHAR(20)
     , codEmergencia INT
     , PRIMARY KEY (codSintoma)
     , CONSTRAINT FK_SINTOMA_1 FOREIGN KEY (codEsp, nombreH)
                  REFERENCES ESPECIALIDAD (codEsp, nombreH)
     , CONSTRAINT FK_SINTOMA_2 FOREIGN KEY (codEmergencia)
                  REFERENCES EMERGENCIA (codEmergencia)
);



INSERT INTO hospital VALUES ('Valencia del Mar', 'Carrer del Riu Tajo, 1', 39.4777, -0.3298)
INSERT INTO hospital VALUES ('La Fe', 'Avenida Fernando Abril', 39.4445, -0.3768)
INSERT INTO hospital VALUES ('Nou dOctubre', 'Vall de la Ballestera, 59', 39.4781, -0.4012)

INSERT INTO especialidad VALUES ('Traumatologia', 'Valencia del Mar')
INSERT INTO especialidad VALUES ('Traumatologia', 'La Fe')
INSERT INTO especialidad VALUES ('Cardiologia', 'Valencia del Mar')
INSERT INTO especialidad VALUES ('Cardiologia', 'Nou dOctubre')
INSERT INTO especialidad VALUES ('Cirugia', 'Nou dOctubre')
INSERT INTO especialidad VALUES ('Cirugia', 'Valencia del Mar')
INSERT INTO especialidad VALUES ('Cirugia', 'La Fe')
INSERT INTO especialidad VALUES ('Oncologia', 'La Fe')
INSERT INTO especialidad VALUES ('Pediatria', 'La Fe')
INSERT INTO especialidad VALUES ('Pediatria', 'Nou dOctubre')
INSERT INTO especialidad VALUES ('Diagnostico Radiologico', 'Valencia del Mar')
INSERT INTO especialidad VALUES ('Diagnostico Radiologico', 'La Fe')

INSERT INTO ambulancia VALUES (1234, 'Completo', 39.4747, -0.3416, 'P', 'AmbusulSL', true, null)
INSERT INTO ambulancia VALUES (5578, 'Resp Asis', 39.4695, -0.146, 'B', null, true, 'La Fe')
INSERT INTO ambulancia VALUES (9866, 'Reani.', 39.4597, -0.4865, 'B', null, true, 'La Fe')
INSERT INTO ambulancia VALUES (0283, 'Reani.', 39.5428, -0.3486, 'B', null, true, 'Nou dOctubre')
INSERT INTO ambulancia VALUES (1987, 'Completa', 39.972, -0.245, 'P', 'NISA', true, null)
INSERT INTO ambulancia VALUES (9803, 'Quemaduras', 39.2158, -0.16556, 'P', 'AlbulSL', true, null)
INSERT INTO ambulancia VALUES (6532, 'Samu', 39.6441, -0.1315, 'P', 'Nisa', true, null)
INSERT INTO ambulancia VALUES (4388, 'Samu', 39.5458, -0.132, 'B', null, true, 'La Fe')