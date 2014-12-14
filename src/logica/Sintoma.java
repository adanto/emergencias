package logica;


public class Sintoma {
	
	private String estado="";
	private int duracion=0;
	private String descripcion="";
	private String nombre="";
	
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

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
