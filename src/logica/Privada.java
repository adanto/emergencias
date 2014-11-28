package logica;

public class Privada {
private int numRegistro;
private String equipo;
private double latitud;
private double longitud;
private String compañia;

public Privada(String compañia, String equipo, int numero, float latitud, float longitud){
	this.compañia = compañia;
	this.equipo = equipo;
	this.numRegistro = numero;
	this.latitud = latitud;
	this.longitud = longitud;
}

public String getEquipo() {
return equipo;
}
public void setEquipo(String equipo) {
this.equipo = equipo;
}
public int getNumRegistro() {
return numRegistro;
}
public void setNumRegistro(int numRegistro) {
this.numRegistro = numRegistro;
}
public double getLatitud() {
return latitud;
}
public void setLatitud(double latitud) {
this.latitud = latitud;
}
public double getLongitud() {
return longitud;
}
public void setLongitud(double longitud) {
this.longitud = longitud;
}
}
