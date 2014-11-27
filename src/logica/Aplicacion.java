package logica;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import excepciones.DAOExcepcion;
import persistencia.IPacienteDAO;
import persistencia.PacienteDAOImp;

public class Aplicacion {
	public static void main(String[] args){
		try{
			//Se crea el Servicio de Emergencias
			TestPacientes emergencias = new TestPacientes();

			System.out.println("Empezamos en cero,");
			// AQUÍ INCLUIR CADA UNA DE LAS EJECUCIONES
			//--------------------------------------------------------------------
			
			//Se listan todos los pacientes
			List<Paciente> listaPacientes = emergencias.ListarPacientes();
			for(Paciente pac:listaPacientes)
			System.out.println(" DNI: "+pac.getDni()+
			" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
			" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
			" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
			
			
			
					

			//--------------------------------------------------------------------
		}catch (DAOExcepcion e){
			System.out.print("DAOExcepcion: "+e);
		}
	}
}
