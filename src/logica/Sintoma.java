package logica;


public class Sintoma {
	
	private String estado="";
	private int duracion=0;
	private String descripcion="";
	private String nombre="";
	private String codEmergencia;
	private String codEsp;
	
	public Sintoma(String nombre, String desc, int duracion, String est, String emergencia, String codEsp){
		this.nombre=nombre;
		this.descripcion=desc;
		this.duracion = duracion;
		this.estado=est;
		this.codEmergencia=emergencia;
		this.codEsp=codEsp;
	}
	public String getCodEsp(){
		return this.codEsp;
	}
	public String getEmerg(){
		return this.codEmergencia;
	}
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	public int getDuracion() {return duracion;}
	public void setDuracion(int duracion) {this.duracion = duracion;}
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
