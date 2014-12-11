package logica;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;
import persistencia.DAL;


public class ServicioEmergencia {

	private ArrayList <Hospital> hospitales;
	private ArrayList <Ambulancia> ambulancias;
	private ArrayList <RegistroEmergencia> registros;
	private HashMap <String,Paciente> pacientes;
	private ArrayList <Especialidad> especialidad;
	private DAL dal;
	
public ServicioEmergencia() throws LogicaExcepcion
{
	this.pacientes = new HashMap<String,Paciente>();
	this.especialidad=new ArrayList<Especialidad>();
	this.ambulancias = new ArrayList<Ambulancia>();
	this.dal = DAL.getSingleton();
}
	
	public void anyadir(Hospital b)
	{
		hospitales.add(b);
	}
	public void anyadir(Paciente p) throws LogicaExcepcion
	{
		if(!this.pacientes.containsKey(p.getDni()))
		try
		{
			DAL.getSingleton().crearPaciente(p);
			pacientes.put(p.getDni(),p);
		}catch(DAOExcepcion e)
		{
			e.printStackTrace();
		}
	}
	public void listaAmbulancias(){
		for(int i = 0; i<ambulancias.size(); i++){
			Ambulancia A = ambulancias.get(i);
			if(A!=null){
				System.out.println("Numero = "+A.getEquipo()+" y numRegistro "+A.getNumRegistro());
			}
		}
	}
	
	public void borrar(Paciente p) throws LogicaExcepcion
	{
		if(this.pacientes.containsKey(p.getDni())){
			try{
				System.out.println("Hemos llegado a borrar");
				DAL.getSingleton().borrarPaciente(p);
				pacientes.remove(p);
			}catch(DAOExcepcion e)
			{	
				e.printStackTrace();
			}
		}
	}
	public void anyadir(Ambulancia r)
	{
		ambulancias.add(r);
	}
	public void anyadir(RegistroEmergencia q)
	{
		registros.add(q);
	}

	public void borrar(Hospital b)
	{
		hospitales.remove(b);
	}
	public void borrar(Ambulancia r)
	{
		ambulancias.remove(r);
	}
	
	
	public void borrar(RegistroEmergencia h)
	{
		registros.remove(h);
	}
	

	public Hospital buscarH(String nombre)
	{
		Hospital salida = null;
		for(Hospital elemento: hospitales)
		{
			if(elemento.getNombre().equals(nombre)) { salida = elemento; }
		}
		return salida;		
	}
	
	public Ambulancia buscarA(int A) throws LogicaExcepcion
	{
		Ambulancia amb = null;
		if(!ambulancias.contains(A))
		{
			System.out.println(ambulancias.contains(A));
			try{
				amb = DAL.getSingleton().buscarAmbulancia(A.getNumRegistro());
				if(amb == null)
				{
					this.ambulancias.add(A);
				}
			}catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
		}
		return amb;
	}
	
	public void cambiarCoor(int numero, float latitud, float longitud)
	{
		Ambulancia a = this.ambulancias.get(numero);
		if(a == null)
		{
			try{
				a = DAL.getSingleton().buscarAmbulancia(numero);
				if(a != null)
				{
					a.cambiarCoordenadas(numero, latitud, longitud);
				}
			}catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
		}
	}
	public void setDisp(int numero, boolean disp) throws LogicaExcepcion
	{
		//Ambulancia a = this.buscarA(numero);
		Ambulancia a = this.ambulancias.get(numero);
		if(a == null)
		{
			try{
				a = DAL.getSingleton().buscarAmbulancia(numero);
				if(a != null)
				{
					a.setDisp(numero, disp);
				}
			}catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
		}
	}
	public void numeroAmbulancias(){
		System.out.print(this.ambulancias.size());
	}
	
	public Paciente buscarP(String dni) throws LogicaExcepcion
	{
		Paciente p = this.pacientes.get(dni);

		if(p==null)
		{
			try{
				p = DAL.getSingleton().buscarPaciente(dni);
			if(p!=null)
			{
				this.pacientes.put(dni, p);
			}
		}
			catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
	}
		return p;
}
	
	public Iterator<Paciente> getPatients() throws LogicaExcepcion
	{
		try
		{
			List<Paciente>dbPacientes = dal.listarPacientes();
		for (Paciente p : dbPacientes)
			if(!this.pacientes.containsKey(p.getDni()))
			{
				this.pacientes.put(p.getDni(),p);
			}
		}
		catch(LogicaExcepcion e)
		{
		  e.printStackTrace(); 
		  throw new LogicaExcepcion("La lista no puede ser recuperada");
		}
		return this.pacientes.values().iterator();
	}
	
	
	public RegistroEmergencia buscar(float latitud, float longitud)
	{
		RegistroEmergencia salida = null;
		for(RegistroEmergencia elemento: registros)
		{
			if(elemento.getLatitud()==latitud && elemento.getLongitud()==longitud) 
			{ salida = elemento; }
		}
		return salida;
	}
	public List<Especialidad> listarEspecialidad(String nombre){
		
		try
		{
			List<Especialidad>dbEspecialidad = dal.listarEspecialidad(nombre);
		for (Especialidad p : dbEspecialidad)
			if(!this.especialidad.contains(p.getNombre()))
			{
				this.especialidad.add(p);
			}
		}
		catch(LogicaExcepcion e)
		{
		  e.printStackTrace(); 
		 
		}
		return this.especialidad;
	}
}
