package logica;

import java.util.ArrayList;

public class Emergencia {
	private int codEmergencia;
	private double longitud;
	private double latitud;
	private String hora;
	private String fecha;
	
	
	ArrayList<Sintoma> sintomas;
	private Paciente paciente;
	private Hospital hospital;
	private Ambulancia ambulancia;
	
	
	public Emergencia(int cod, double lon, double lat, String hora, String fecha){
		this.longitud=lon;
		this.latitud=lat;
		this.codEmergencia = cod;
		this.hora=hora;
		this.fecha=fecha;
		
		this.sintomas = new ArrayList<Sintoma>();
		this.hospital = null;
		this.ambulancia = null;
		this.paciente = null;
	}
	public int getCodEmergencia(){
		return this.codEmergencia;
	}
	public void setHora(String hora){
		this.hora = hora;
	}
	public void setFecha(String fecha){
		this.fecha = fecha;
	}
	public String getHora(){
		return this.hora;
	}
	public String getFecha(){
		return this.fecha;
	}
	public void setAmb(Ambulancia a){
		this.ambulancia = a;
	}
	public Ambulancia getAmb(){
		return this.ambulancia;
	}
	
	public void setHosp(Hospital h){
		this.hospital = h;
	}
	public Hospital getHosp(){
		return this.hospital;
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
