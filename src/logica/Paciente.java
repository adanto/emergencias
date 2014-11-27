package logica;

public class Paciente {
	private String nombre="";
	private String apellidos="";
	private String dni="";
	private String sexo="";
	private int edad=0;
	private int telf=0;
	private String dir="";
	
	public String getNombre(){return this.nombre;}
	public String getDni(){return this.dni;}
	public String getApellidos(){return this.apellidos;}
	public String getSexo(){return this.sexo;}
	public int getEdad(){return this.edad;}
	public int getTelf(){return this.telf;}
	public String getDir(){return this.dir;}
	
	public void setNombre(String nombre){this.nombre=nombre;}
	public void setApellidos(String apellidos){this.apellidos=apellidos;}
	public void setDni(String dni){this.dni=dni;}
	public void setSexo(String sexo){this.sexo=sexo;}
	public void setEdad(int edad){this.edad=edad;}
	public void setTelf(int telf){this.telf=telf;}
	public void setDir(String dir){this.dir=dir;}
}
