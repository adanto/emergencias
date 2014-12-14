package logica;

import java.util.ArrayList;

public class Emergencia {
	private Paciente paciente;
	private double longitud;
	private double latitud;
	ArrayList<Sintoma> sintomas;
	
	
	public Emergencia(Paciente p, double lon, double lat){
		this.paciente=p;
		this.longitud=lon;
		this.latitud=lat;
		this.sintomas = new ArrayList<Sintoma>();
	}
	public double getLong(){
		return this.longitud;
	}	
	public double getLat(){
		return this.latitud;
	}
	public void setLong(double longitud){
		this.longitud=longitud;
	}
	public void setLat(double latitud){
		this.latitud=latitud;
	}
	public Paciente getPaciente(){
		return this.paciente;
	}
	public void setPaciente(Paciente p){
		this.paciente=p;
	}
	public void addSintoma(Sintoma s){
		sintomas.add(s);
	}
	public ArrayList<Sintoma> getSintomas(){
		return sintomas;
	}
	
}
