package persistencia;

import java.util.List;
import logica.Especialidad;

import logica.Ambulancia;
import logica.Paciente;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class DAL {
	private static DAL dal;
	// Declaración de los DAO
	IPacienteDAO pacienteDAO;
	IAmbulanciaDAO ambulanciaDAO;
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
		throw new LogicaExcepcion("No se pudo crear el paciente.");
	}
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
	
	public void cambiarCoor(int num, float latitud, float longitud) throws DAOExcepcion 
	{
		try
		{
		ambulanciaDAO = new AmbulanciaDAOImp();
		ambulanciaDAO.cambiarCoor(num, latitud, longitud);
		}catch(DAOExcepcion e)
		{
			throw new DAOExcepcion("No se pudo crear la Ambulancia.");
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
