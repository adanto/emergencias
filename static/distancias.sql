SELECT A.numRegistro, ((A.latitud-'"+lon+"')*(A.latitud-'"+lon+"')+(A.longitud-'"+lat+"')*(A.longitud-'"+lat+"')+(H.latitud-'"+lon+"')*(H.latitud-'"+lon+"')+(H.longitud-'"+lat+"')*(H.longitud-'"+lat+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE ORDER BY Distancia



SELECT A.numRegistro, H.nombreH, ((A.latitud-'+lon+')*(A.latitud-'+lon+')+(A.longitud-1)*(A.longitud-1)+(H.latitud-'+lon+')*(H.latitud-'+lon+')+(H.longitud-1)*(H.longitud-1)) AS Distancia 
FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH 
WHERE (A.tipo = 'B' AND A.disponibilidad = TRUE)
ORDER BY Distancia

lon=1;
lat=2;

SELECT A.numRegistro, H.nombreH, ((H.longitud-1)*(H.longitud-1)+(H.latitud-2)*(H.latitud-2))+((A.longitud-1)*(A.longitud-1)+(A.latitud-2)*(A.latitud-2)) AS Longitud
FROM Ambulancia A, Hospital H
WHERE A.tipo = 'P' AND A.disponibilidad = TRUE
AND ((H.longitud-1)*(H.longitud-1)+(H.latitud-2)*(H.latitud-2))=(
   SELECT MIN((H1.longitud-1)*(H1.longitud-1)+(H1.latitud-2)*(H1.latitud-2))
   FROM Hospital H1)
AND ((A.longitud-1)*(A.longitud-1)+(A.latitud-2)*(A.latitud-2))=(
   SELECT MIN((A1.longitud-1)*(A1.longitud-1)+(A1.latitud-2)*(A1.latitud-2))
   FROM Ambulancia A1
   WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE
   )

-------------------------------> Transformación!!

SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud
FROM Ambulancia A, Hospital H
WHERE A.tipo = 'P' AND A.disponibilidad = TRUE
AND ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))=(
   SELECT MIN((H1.longitud-'"+lon+"')*(H1.longitud-'"+lon+"')+(H1.latitud-'"+lat+"')*(H1.latitud-'"+lat+"'))
   FROM Hospital H1)
AND ((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"'))=(
   SELECT MIN((A1.longitud-'"+lon+"')*(A1.longitud-'"+lon+"')+(A1.latitud-'"+lat+"')*(A1.latitud-'"+lat+"'))
   FROM Ambulancia A1
   WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE
   )

-------------------------------> Todo una linea

SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))=(SELECT MIN((H1.longitud-'"+lon+"')*(H1.longitud-'"+lon+"')+(H1.latitud-'"+lat+"')*(H1.latitud-'"+lat+"')) FROM Hospital H1) AND ((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"'))=(SELECT MIN((A1.longitud-'"+lon+"')*(A1.longitud-'"+lon+"')+(A1.latitud-'"+lat+"')*(A1.latitud-'"+lat+"')) FROM Ambulancia A1 WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE)


SELECT CODEMERGENCIA, E.LATITUD AS LATITUDEME, E.LONGITUD AS LONGITUDEME, FECHA, HORA, NOMBREH, DNI, NUMREGISTRO, NOMBRE, APELLIDOS, P.DIRECCION AS DIRECCIONPAC, TELEFONO, EDAD, SEXO, H.DIRECCION AS DIRECCIONHOSP, H.LATITUD AS LATITUDHOSP, H.LONGITUD AS LONGITUDHOSP, EQUIPO, A.LATITUD AS LATITUDAMB, A.LONGITUD AS LONGITUDAMB, DISPONIBILIDAD FROM Emergencia E, Paciente P, Hospital H, Ambulancia A WHERE E.dni = P.dni AND E.nombreH = H.nombreH AND E.numRegistro = A.numRegistro



INSERT INTO EMERGENCIA VALUES ('"+em.getCodEmergencia()+"', '"+em.getLat()+"', '"+em.getLong()+"', '"+em.getFecha()+"', '"+em.getHora()+"', '"+em.getHosp().getNombre()+"', '"+em.getPaciente().getDni()+"', '"+em.getAmb().getNumRegistro()+"')


SELECT *
FROM Emergencia E, Ambulancia A, Paciente P, Hospital H, Sintoma S, Especialidad ES
WHERE E.DNI = P.DNI 
   AND E.numRegistro = A.numRegistro
   AND E.nombreH = E.nombreH
   AND E.codEmergencia = S.codEmergencia
   AND H.nombreH = E.nombreH
   AND H.nombreH IN (
   /* Hospitales que cubren todas los sintomas */
WHERE H.nombreH IN (
   SELECT H1.nombreH
      FROM Hospital H1, Especialidad ES1
      WHERE H1.nombreH = ES1.nombreH 
         AND       
            NOT EXISTS (
               SELECT *
               FROM Sintoma S1
               WHERE ES1.codEsp != S1.codEsp
         )
    )

   
   AND S.codEsp = ES.codEsp
   AND ES.nombreH = H.nombreH


SELECT * 
FROM Hospital H
   /* Hospitales que cubren todas los sintomas */




UPDATE sintoma SET codEsp='Cirugia' WHERE codSintoma = 'Me pica1'