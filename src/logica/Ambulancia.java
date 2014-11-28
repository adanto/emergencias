package logica;

public class Ambulancia {
	
	
private int numRegistro;
private String equipo;
private double latitud;
private double longitud;


public Ambulancia(int numRegistro, String equipo, double latitud, double longitud){
	this.numRegistro=numRegistro;
	this.equipo=equipo;
	this.latitud=latitud;
	this.longitud=longitud;
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
public void cambiarCoordenadas(int numero, float latitud2, float longitud2) {
	this.latitud=latitud2;
	this.longitud=longitud2;
	
}
}
