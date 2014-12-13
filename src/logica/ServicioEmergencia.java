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
	public void anyadir(Ambulancia r) throws LogicaExcepcion
	{
		if(buscarA(r.getNumRegistro())==null){
			try{
				DAL.getSingleton().crearAmbulancia(r);
				ambulancias.add(r);
			}
			catch(DAOExcepcion e)
			{
				e.printStackTrace();
			}
		}
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
	
	public void listaAmbulancias(){
		for(int i = 0; i<ambulancias.size(); i++){
			Ambulancia A = ambulancias.get(i);
			if(A!=null){
				System.out.println("Numero = "+A.getEquipo()+" y numRegistro "+A.getNumRegistro());
			}
		}
	}
	public void listAmbulancias() throws LogicaExcepcion{

		Ambulancia pasando = null;
		Iterator<Ambulancia> iteratorcito = ambulancias.listIterator();
		while(iteratorcito.hasNext()){
			pasando=iteratorcito.next();
			System.out.println("Numero: "+pasando.getNumRegistro()+" Disponibilidad: "+pasando.getDisp()+" Lat: "+pasando.getLatitud()+" Long: "+pasando.getLongitud());
		}
	}
	
	public Ambulancia buscarA(int A) throws LogicaExcepcion
	{
		boolean encontrado = false;
		Ambulancia encont = null;
		Ambulancia pasando = null;
		Iterator<Ambulancia> iteratorcito = ambulancias.listIterator();
		while(iteratorcito.hasNext() && !encontrado){
			pasando = iteratorcito.next();
			if(pasando.getNumRegistro()==A){
				encontrado = true;
				encont = pasando;
			}
		}
		if(!encontrado){
				try{
					encont = DAL.getSingleton().buscarAmbulancia(A);
				}catch(LogicaExcepcion e)
				{
					e.printStackTrace();
				}
			}
	
		return encont;
	}
	
	public void cambiarCoor(int numero, float latitud, float longitud) throws LogicaExcepcion, DAOExcepcion
	{
		Ambulancia db = null;
			try{
				db = DAL.getSingleton().buscarAmbulancia(numero);
				if(db != null)
				{
					DAL.getSingleton().cambiarCoor(numero, latitud, longitud);
				}
			}catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
		
	}
	public void setDisp(int numero, boolean disp) throws LogicaExcepcion, DAOExcepcion
	{
		Ambulancia db = null;
		//Ambulancia a = this.ambulancias.(numero);

			try{
				db = DAL.getSingleton().buscarAmbulancia(numero);

				if(db != null)
				{
					try{

						DAL.getSingleton().setDisp(db.getNumRegistro(), disp);
					}catch(DAOExcepcion e){
						e.printStackTrace();
					}
				}
			}catch(LogicaExcepcion e)
			{
				e.printStackTrace();
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
	public void especialidadesLocal(){

		for(int i = 0; i<especialidad.size(); i++){
			Especialidad A = especialidad.get(i);
			if(A!=null){
				System.out.println(A.getNombre());
			}
		}
	}
}
