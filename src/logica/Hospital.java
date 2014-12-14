package logica;

import java.util.ArrayList;

public class Hospital {
	
	
	private String nombre;
	private String direccion;
	private double latitud;
	private double longitud;
	
	ArrayList<BHospital> misAmbulancias;
	
	ArrayList<Especialidad> misEspecialidades;
	
	
	public Hospital(String nombre, String direccion, double latitud, double longitud){
		this.nombre=nombre;
		this.direccion=direccion;
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	public ArrayList<BHospital> misAmbulancias(){return misAmbulancias;}
	
	public ArrayList<Especialidad> misEspecialidades(){return misEspecialidades;}
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
