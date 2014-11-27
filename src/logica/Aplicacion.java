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
			// AQU� INCLUIR CADA UNA DE LAS EJECUCIONES
			//--------------------------------------------------------------------
			
			// Primera ejecuci�n
			//Se busca un paciente y se crea si no existe
			
			if (emergencias.buscarPaciente("10123456A")==null)
			emergencias.a�adirPaciente(new Paciente("10123456A", "Juan",
			"Martinez Gandia", "Calle Santiago, 4 Valencia", 123453250,
			50, 'H'));
			
			//Se busca un paciente y si lo encuentra se muestran sus datos
			Paciente p = emergencias.buscarPaciente("10123456A");
			if (p!=null)
			System.out.println(" DNI: "+p.getDni()+" Nombre: "+p.getNombre()+
			" Apellidos: "+p.getApellidos()+
			" Direcci�n: "+p.getDireccion()+" Tel�f.: "+p.getTelefono()+
			" Edad: "+p.getEdad()+" Sexo: "+p.getSexo());
			
			//Segunda ejecuci�n
			//Se busca un paciente y si lo encuentra se muestran sus datos
			Paciente p1 = emergencias.buscarPaciente("10123456A");
			if (p1!=null)
			System.out.println(" DNI: "+p1.getDni()+
			" Nombre: "+p1.getNombre()+" Apellidos: "+p1.getApellidos()+
			" Direcci�n: "+p1.getDireccion()+" Tel�f.: "+p1.getTelefono()+
			" Edad: "+p1.getEdad()+" Sexo: "+p1.getSexo());
			//Se crea un paciente
			emergencias.a�adirPaciente(new Paciente("10123457A", "Pedro",
			"Suecaz Santos", "Calle San Vicente, 4 Valencia", 123453251, 25,
			'H'));
			//Se listan todos los pacientes
			List<Paciente> listaPacientes = emergencias.ListarPacientes();
			for(Paciente pac:listaPacientes)
			System.out.println(" DNI: "+pac.getDni()+
			" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
			" Direcci�n: "+pac.getDireccion()+" Tel�f.: "+pac.getTelefono()+
			" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
			
			
			
					

			//--------------------------------------------------------------------
		}catch (DAOExcepcion e){
			System.out.print("DAOExcepcion: "+e);
		}
	}
}
