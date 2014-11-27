package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.IPacienteDAO;
import persistencia.PacienteDAOImp;

public class ServicioEmergencia {
	private HashMap<String, Paciente> listaPacientes;
	
	//Objeto para la comunicación con persistencia
	private IPacienteDAO pacienteDAO; 
	
	public ServicioEmergencia() throws DAOExcepcion {
		this.listaPacientes = new HashMap<String, Paciente>();
		this.pacienteDAO= new PacienteDAOImp();
	}
	
	public void añadirPaciente(Paciente paciente) throws DAOExcepcion
	{
		//Buscamos el paciente
		if (buscarPaciente(paciente.getDni())==null)
		{
			//Si no lo tenemos lo añadimos
			this.listaPacientes.put(paciente.getDni(), paciente);
			pacienteDAO.crearPaciente(paciente);
		}	
	}
	
	public Paciente buscarPaciente(String dni) throws DAOExcepcion
	{		
		//Busco el paciente en memoria
		Paciente paciente = this.listaPacientes.get(dni);
		
		//El paciente no esta en memoria, se busca en la BD
		if (paciente==null)
		{
			paciente = pacienteDAO.buscarPaciente(dni);
			
			//Si lo he encontrado en la BD, lo añado a memoria porque no lo tenía
			if (paciente != null) this.listaPacientes.put(dni, paciente);
		}
		
		//Devuelvo el paciente que he encontrado o null en caso contrario
		return paciente;
	}
	
	public List<Paciente> ListarPacientes() throws DAOExcepcion
	{
		//Se obtienen los pacientes de la BD, porque no hay ninguna garantía de tenerlos todos en memoria
		List<Paciente> lista = pacienteDAO.listarPacientes();
		
		//Para cada paciente de la BD, si no esta en memoria lo añadimos
		for(Paciente p:lista)
			if (this.listaPacientes.containsKey(p.getDni())) this.listaPacientes.put(p.getDni(), p);
		return new ArrayList<Paciente>(listaPacientes.values());
	}
		
}
