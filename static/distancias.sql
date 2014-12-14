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