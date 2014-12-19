package persistencia;

//import HospitalDAOImp;

import java.util.List;

import logica.Emergencia;
import logica.Especialidad;
import logica.Ambulancia;
import logica.Hospital;
import logica.Paciente;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class DAL {
	private static DAL dal;
	// Declaración de los DAO
	IPacienteDAO pacienteDAO;
	IAmbulanciaDAO ambulanciaDAO;
	IHospital hospitalDAO;
	IEmergenciaDAO emergenciaDAO;
	// constructor privado
	private DAL() throws DAOExcepcion {
		this.pacienteDAO = new PacienteDAOImp();
	}
	// Patrón Singleton
	public static DAL getSingleton() throws LogicaExcepcion
	{
		if(dal == null) { try {
			dal = new DAL();
		} catch (DAOExcepcion e) {
			throw new LogicaExcepcion("Error");
		} }
		return dal;
	}
	//*****************************************************************************
	// Servicios para el C.U. Alta Paciente
	//*****************************************************************************


	public Paciente buscarPaciente(String dni) throws LogicaExcepcion 
	{
		try{
	pacienteDAO = new PacienteDAOImp();
	return pacienteDAO.buscarPaciente(dni);
	}catch(DAOExcepcion e)
	{
		throw new LogicaExcepcion("No encontrado.");
	}
	}
	public Paciente buscarPacienteNom(String nombre) throws LogicaExcepcion 
	{
		try{
	pacienteDAO = new PacienteDAOImp();
	return pacienteDAO.buscarPacienteNom(nombre);
	}catch(DAOExcepcion e)
	{
		throw new LogicaExcepcion("No encontrado.");
	}
	}

	public int ambMinima(double lon, double lat) throws DAOExcepcion{
		int amb = -1;
		try{
			emergenciaDAO = new EmergenciaDAOImp();
			amb = emergenciaDAO.ambMinima(lon, lat);
		}catch(DAOExcepcion e){
			throw new DAOExcepcion("No se pudo encontrar la ambulancia mínima.");
		}
		return amb;
	}
	public String hospMinimo(double lon, double lat) throws DAOExcepcion{
		String amb = "-1";
		try{
			emergenciaDAO = new EmergenciaDAOImp();
			amb = emergenciaDAO.hospMinimo(lon, lat);
		}catch(DAOExcepcion e){
			throw new DAOExcepcion("No se pudo encontrar el hospital mínimo.");
		}
		return amb;
	}
	public void crearPaciente(Paciente p) throws DAOExcepcion 
	{
		try
		{
		pacienteDAO = new PacienteDAOImp();
		pacienteDAO.crearPaciente(p);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo crear el paciente.");
		}
	}
	public void crearEmergencia(Emergencia em) throws DAOExcepcion 
	{
		try
		{

		emergenciaDAO = new EmergenciaDAOImp();
		emergenciaDAO.crearEmergencia(em);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo crear la emergencia.");
		}
	}

	public void borrarPaciente(Paciente p) throws DAOExcepcion 
	{
		try
		{
		pacienteDAO = new PacienteDAOImp();
		pacienteDAO.borrarPaciente(p);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo borrar el paciente.");
		}
	}

	public List<Paciente> listarPacientes() throws LogicaExcepcion
	{
		try
		{
			return(new PacienteDAOImp()).listarPacientes();
		}
		catch (DAOExcepcion e)
		{
			throw new LogicaExcepcion("No se pudo listar los pacientes.");
		}
	}
	public List<Emergencia> listarEmergencias() throws LogicaExcepcion
	{
		List<Emergencia> devolver = null;
		try
		{
			EmergenciaDAOImp daito = new EmergenciaDAOImp();

			devolver = daito.listaEmergencias();

		}
		catch (DAOExcepcion e)
		{
			throw new LogicaExcepcion("No se pudo listar las emergencias.");
		}
		return devolver;
	}
	
	public void crearAmbulancia(Ambulancia a) throws DAOExcepcion 
	{
		try
		{
		ambulanciaDAO = new AmbulanciaDAOImp();
		ambulanciaDAO.crearAmbulancia(a);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo crear la Ambulancia.");
		}
	}
	
	public Ambulancia buscarAmbulancia(int numero) throws LogicaExcepcion 
	{
		try{
			ambulanciaDAO = new AmbulanciaDAOImp();

			return ambulanciaDAO.buscarAmbulancia(numero);
		}catch(DAOExcepcion e)
		{
			throw new LogicaExcepcion("No se pudo encontrar tu ambulancia.");
		}
	}
	public Hospital buscarHospital(String nombre) throws LogicaExcepcion 
	{
		try{
			hospitalDAO = new HospitalDAOImp();

			return hospitalDAO.buscaHospital(nombre);
		}catch(DAOExcepcion e)
		{
			throw new LogicaExcepcion("No se pudo encontrar tu ambulancia.");
		}
	}
	public Emergencia buscarEmergencia(String text) throws LogicaExcepcion{
		Emergencia emer = null;
		try{
			emergenciaDAO = new EmergenciaDAOImp();
			
			emer = emergenciaDAO.buscarEmergencia(text);
		}catch(DAOExcepcion e){
			throw new LogicaExcepcion("No se encuentra la emergencia por aquí abajo :I");
		}
		return emer;
	}
	
	public void cambiarCoor(int num, float latitud, float longitud) throws DAOExcepcion 
	{
		try
		{
		ambulanciaDAO = new AmbulanciaDAOImp();
		ambulanciaDAO.cambiarCoor(num, latitud, longitud);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo cambiar las coordenadas a la Ambulancia.");
		}
	}
	public void setDisp(int num, boolean disp) throws DAOExcepcion 
	{

		try
		{
		ambulanciaDAO = new AmbulanciaDAOImp();
		ambulanciaDAO.setDisp(num, disp);

		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo modificar disp.");
		}
	}
	public List<Hospital> listarHospitales() throws LogicaExcepcion{
		try{
			return(new HospitalDAOImp().listaHospitales());
		}catch(DAOExcepcion e){
			throw new LogicaExcepcion("No se puedo listar hospitales");
		}
	}
	
	public List<Especialidad> listarEspecialidad(String nombre) throws LogicaExcepcion
	{
		try
		{
			return(new HospitalDAOImp()).listaEspecialidad(nombre);
		}
		catch (DAOExcepcion e)
		{
			throw new LogicaExcepcion("No se pudo listar los pacientes.");
		}
	}
}
