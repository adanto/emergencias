package logica;

import java.util.ArrayList;

public class Sintoma {
	
	private String estado="";
	private int duracion=0;
	private String descripcion="";
	private String nombre="";
	ArrayList<RegistroEmergencia> registros= new ArrayList<RegistroEmergencia>();
	
	public Sintoma(String nombre, String desc, int duracion, String est){
		this.nombre=nombre;
		this.descripcion=desc;
		this.duracion = duracion;
		this.estado=est;
	}
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	public int getDuracion() {return duracion;}
	public void setDuracion(int duracion) {this.duracion = duracion;}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public ArrayList<RegistroEmergencia> registrosSincotma(){
		return registros;
		}
	
	public void añadeRegistro(RegistroEmergencia nuevo){
		registros.add(nuevo);
	}
	
	public void borraRegistro(int i){
		registros.remove(i);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
