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
	private HashMap <String,Paciente> pacientes;
	private ArrayList <Especialidad> especialidad;
	private ArrayList <Emergencia> emergencias;
	private DAL dal;
	
public ServicioEmergencia() throws LogicaExcepcion{
	this.pacientes = new HashMap<String,Paciente>();
	this.especialidad= new ArrayList<Especialidad>();
	this.ambulancias = new ArrayList<Ambulancia>();
	this.emergencias = new ArrayList<Emergencia>();
	this.hospitales=new ArrayList<Hospital>();
	this.dal = DAL.getSingleton();
}
public List <Emergencia> listaEmergencias() throws DAOExcepcion, LogicaExcepcion{
	List<Emergencia> emergencias = null;
	try{
		emergencias = dal.listarEmergencias();
	}catch(LogicaExcepcion e){
		  e.printStackTrace(); 
		  throw new LogicaExcepcion("La lista de emergencias no puede ser recuperada");
		
	}
	return emergencias;
}

//Método para obtener la ambulancia mas cercana a una posicion (x,y)
public int ambMinima(double d, double f) throws LogicaExcepcion{
	int amb = -1;
	try{
		amb = DAL.getSingleton().ambMinima(d, f);
	}catch(DAOExcepcion e){
		e.printStackTrace();
	};
	return amb;
}

//Método para obtener la ambulancia mas cercana a una emergencia
public int ambMinima(String cod) throws LogicaExcepcion{
	int amb = -1;
	try{
		amb = DAL.getSingleton().ambMinima(cod);
	}catch(DAOExcepcion e){
		e.printStackTrace();
	};
	return amb;
}

//Método para obtener el hospital mas cercano a una posicion (x,y)
public String hospMinimo(double d, double f) throws LogicaExcepcion{
	String amb = "-1";
	try{
		amb = DAL.getSingleton().hospMinimo(d, f);
	}catch(DAOExcepcion e){
		e.printStackTrace();
	};
	return amb;
}


//Método para obtener el hospital más cercano a una emergencia
public String hospMinimo(String cod) throws LogicaExcepcion{
	String amb = "-1";
	try{
		amb = DAL.getSingleton().hospMinimo(cod);
	}catch(DAOExcepcion e){
		e.printStackTrace();
	};
	return amb;
}

/** WARNING: El código que viene a continuación te hará llorar.
 * Este cerdito puede que le ayude a sobrellevarlo. 
 * 
 *                         _
 * _._ _..._ .-',     _.._(`))
 *'-. `     '  /-._.-'    ',/
 *   )         \            '.
 *  / _    _    |             \
 * |  a    a    /              |
 * \   .-.                     ;  
 *  '-('' ).-'       ,'       ;
 *     '-;           |      .'
 *        \           \    /
 *        | 7  .__  _.-\   \
 *        | |  |  ``/  /`  /
 *       /,_|  |   /,_/   /
 *          /,_/      '`-'
**/

/**
 * Este método añade las emergencias de la siguiente forma:
 * 	1º Añade una emergencia con código y paciente
 * 	2º Añade los síntomas de esa emergencia
 * 	3º Se busca una ambulancia mínima y un hospital mínimo para añadirlos a la emergencia
 * 	4º Una vez lo tenemos, añadimos estos 2 a el objeto emergencia
 * 	5º Eliminamos los síntomas y la emergencia de la DB
 * 	6º Volvemos a añdir la emergenia (esta vez con su ambulancia y hospital) y los síntomas
 * 
 */
	public void anyadir(Emergencia em) throws LogicaExcepcion{
		em.setAmb(null);
		em.setHosp(null);
		ArrayList<Sintoma> sints = em.getSintomas();
		
		if(buscarEM(em.getCodEmergencia())==null) {

			try{
				DAL.getSingleton().crearEmergencia(em);
			}
			catch(DAOExcepcion e){	
				e.printStackTrace();
			};
			for (Sintoma s : sints){
				try {
					DAL.getSingleton().crearSintoma(s);
				} catch (DAOExcepcion e) {
					System.out.println("Imposible añadir sintoma");
					e.printStackTrace();
				}
			};
			
			Ambulancia amb = buscarA(ambMinima(em.getCodEmergencia()));
			if(amb!=null){
				em.setAmb(amb);
			}
			
			Hospital hosp = buscarH(hospMinimo(em.getCodEmergencia()));
			if(hosp!=null){
				em.setHosp(hosp);
			}
			for (Sintoma s : sints){
				try {
					DAL.getSingleton().deleteSintoma(s.getEmerg());
				} catch (DAOExcepcion e) {
					e.printStackTrace();
				}
			};
			
			
			try{
				DAL.getSingleton().deleteEmergencia(em.getCodEmergencia());
				DAL.getSingleton().crearEmergencia(em);
				emergencias.add(em);
			}
			catch(DAOExcepcion e){
				e.printStackTrace();
			};
			for (Sintoma s : sints){
				try {
					DAL.getSingleton().crearSintoma(s);
				} catch (DAOExcepcion e) {
					System.out.println("Imposible añadir sintoma");
					e.printStackTrace();
				}
			};
		}else{
			System.out.println("Emergencia con este codigo ya añadida");
		};
	}
		
			
	
	public Emergencia buscarEM(String text){
		boolean encontrado = false;
		Emergencia encont = null;
		Emergencia pasando = null;
		Iterator<Emergencia> iteratorcito = emergencias.listIterator();
		while(iteratorcito.hasNext() && !encontrado){
			pasando = iteratorcito.next();
			if(pasando.getCodEmergencia()==text){
				encontrado = true;
				encont = pasando;
			}
		}
		if(!encontrado){
				try{
					encont = DAL.getSingleton().buscarEmergencia(text);
				}catch(LogicaExcepcion e)
				{	
					e.printStackTrace();
					System.out.println(encont);
					return null;
				}
			}
	
		return encont;
	}
			
	
	public void borrar(Paciente p) throws LogicaExcepcion
	{
		if(this.pacientes.containsKey(p.getDni())){
			try{
				pacientes.remove(p.getDni());
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

	public void borrar(Hospital b)
	{
		hospitales.remove(b);
	}
	public void borrar(Ambulancia r)
	{
		ambulancias.remove(r);
	}
	
	

	public Hospital buscarH(String nombre)
	{
		boolean encontrado = false;
		Hospital encont = null;
		Hospital pasando = null;
		Iterator<Hospital> iteratorcito = hospitales.listIterator();
		while(iteratorcito.hasNext() && !encontrado){
			pasando = iteratorcito.next();
			if(pasando.getNombre()==nombre){
				encontrado = true;
				encont = pasando;
			}
		}
		if(!encontrado){
				try{
					encont = DAL.getSingleton().buscarHospital(nombre);
				}catch(LogicaExcepcion e)
				{
					e.printStackTrace();
				}
			}
	
		return encont;
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
	
	public Paciente buscarPNombre(String nombre) throws LogicaExcepcion
	{
		Paciente p = null;
			try{
				p = DAL.getSingleton().buscarPacienteNom(nombre);
				if(p!=null)
				{
					this.pacientes.put(p.getDni(), p);
				}
			}
			catch(LogicaExcepcion e)
			{
				e.printStackTrace();
			}
		
			return p;
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
	
	public List<Hospital> getHospitales() throws LogicaExcepcion{
		List<Hospital>dbHospital =null;
		try
		{
			dbHospital = dal.listarHospitales();
		}
		catch(LogicaExcepcion e)
		{
		  e.printStackTrace(); 
		  throw new LogicaExcepcion("La lista de hospitales no puede ser recuperada");
		}
		return dbHospital;
	}

	public void nombHospitales() throws LogicaExcepcion{
		List<Hospital> hospitales = getHospitales();
		for(Hospital h : hospitales){
			System.out.println(h.getNombre()+" "+h.getDireccion()+" "+h.getLatitud()+" "+h.getLongitud());
		}
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
	
	public List<Hospital> listarHospital(){
		
		try
		{
			List<Hospital>dbHospital = dal.listarHospitales();
		for (Hospital p : dbHospital)
			if(!this.hospitales.contains(p.getNombre()))
			{
				this.hospitales.add(p);
			}
		}
		catch(LogicaExcepcion e)
		{
		  e.printStackTrace(); 
		 
		}
		return this.hospitales;
	}
}
