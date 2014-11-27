package logica;

import java.util.ArrayList;

public class RegistroEmergencia {
	
	private double latitud=0.0;
	private double longitud=0.0;
	private String fecha="";
	private String hora="";
	private int id;
	Paciente p=null;
	ArrayList<Sintoma> sintoma= new ArrayList<Sintoma>();
	
	public double getLatitud() {return latitud;}
	public void setLatitud(double latitud) {this.latitud = latitud;}
	public double getLongitud() {return longitud;}
	public void setLongitud(double longitud) {this.longitud = longitud;}
	public String getFecha() {return fecha;}
	public void setFecha(String fecha) {this.fecha = fecha;}
	public String getHora() {return hora;}
	public void setHora(String hora) {this.hora = hora;}
	
	public ArrayList<Sintoma> sintomasPaciente(){
		return sintoma;
		}
	
	public void añadeSintoma(Sintoma nuevo){
		sintoma.add(nuevo);
	}
	
	public void borraSintoma(int i){
		sintoma.remove(i);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
} 
	


