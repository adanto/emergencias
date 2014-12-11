DROP TABLE Ambulancia IF EXISTS;

CREATE TABLE Ambulancia (
       numRegistro BIT NOT NULL
     , equipo CHAR(10)
     , latitud DOUBLE
     , longitud DOUBLE
     , id INT NOT NULL
     , disponibilidad CHAR
     , PRIMARY KEY (numRegistro)
);

