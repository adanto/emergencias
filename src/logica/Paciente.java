package logica;

public class Paciente {
	private String nombre="";
	private String apellidos="";
	private String dni="";
	private char sexo='I';
	private int edad=0;
	private int telefono=0;
	private String direccion="";
	
	public Paciente(String dni, String nombre, String apellidos, String direccion, int telefono, int edad, char sexo){
		this.dni=dni;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.telefono=telefono;
		this.edad=edad;
		this.sexo=sexo;
	}
	public String getNombre(){return this.nombre;}
	public String getDni(){return this.dni;}
	public String getApellidos(){return this.apellidos;}
	public char getSexo(){return this.sexo;}
	public int getEdad(){return this.edad;}
	public int getTelefono(){return this.telefono;}
	public String getDireccion(){return this.direccion;}
	
	public void setNombre(String nombre){this.nombre=nombre;}
	public void setApellidos(String apellidos){this.apellidos=apellidos;}
	public void setDni(String dni){this.dni=dni;}
	public void setSexo(char sexo){this.sexo=sexo;}
	public void setEdad(int edad){this.edad=edad;}
	public void setTelf(int telf){this.telefono=telf;}
	public void setDir(String dir){this.direccion=dir;}
}
