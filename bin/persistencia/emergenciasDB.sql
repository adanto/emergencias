DROP TABLE AMBULANCIA IF EXISTS;
DROP TABLE algo IF EXISTS;

CREATE TABLE algo (
       algo CHAR(10) NOT NULL
     , PRIMARY KEY (algo)
);

CREATE TABLE AMBULANCIA (
       numRegistro INT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , tipo CHAR(1)
     , "compañia" CHAR(20)
     , disponibilidad BIT
     , algo CHAR(10)
     , PRIMARY KEY (numRegistro)
     , CONSTRAINT FK_AMBULANCIA_1 FOREIGN KEY (algo)
                  REFERENCES algo (algo)
);

