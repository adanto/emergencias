package persistencia;

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
	// Declaraci�n de los DAO
	IPacienteDAO pacienteDAO;
	IAmbulanciaDAO ambulanciaDAO;
	IHospital hospitalDAO;
	IEmergenciaDAO emergenciaDAO;
	// constructor privado
	private DAL() throws DAOExcepcion {
		this.pacienteDAO = new PacienteDAOImp();
	}
	// Patr�n Singleton
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
		throw new LogicaExcepcion("No se pudo crear el paciente.");
	}
	}
	
	public int ambMinima(double lon, double lat) throws DAOExcepcion{
		int amb = -1;
		try{
			emergenciaDAO = new EmergenciasDAOImp();
			amb = emergenciaDAO.ambMinima(lon, lat);
		}catch(DAOExcepcion e){
			throw new DAOExcepcion("No se pudo encontrar la ambulancia m�nima.");
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
		emergenciaDAO = new EmergenciasDAOImp();
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
	public Emergencia buscarEmergencia(int numero) throws LogicaExcepcion{
		try{
			emergenciaDAO = new EmergenciasDAOImp();
			return emergenciaDAO.buscarEmergencia(numero);
		}catch(DAOExcepcion e){
			throw new LogicaExcepcion("No se encuentra la emergencia");
		}
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
