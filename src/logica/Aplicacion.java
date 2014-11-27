package logica;
import java.util.List;
import excepciones.DAOExcepcion;

public class Aplicacion {
	public static void main(String[] args){
		try{
			//Se crea el Servicio de Emergencias
			TestPacientes emergencias = new TestPacientes();

			System.out.println("Empezamos en cero,");
			// AQUÍ INCLUIR CADA UNA DE LAS EJECUCIONES
			//--------------------------------------------------------------------
			
			
			
			//Segunda ejecución
			//Se busca un paciente y si lo encuentra se muestran sus datos
			Paciente p1 = emergencias.buscarPaciente("10123456A");
			if (p1!=null)
			System.out.println(" DNI: "+p1.getDni()+
			" Nombre: "+p1.getNombre()+" Apellidos: "+p1.getApellidos()+
			" Dirección: "+p1.getDireccion()+" Teléf.: "+p1.getTelefono()+
			" Edad: "+p1.getEdad()+" Sexo: "+p1.getSexo());
			
			
			
			//Se crea un paciente
			emergencias.añadirPaciente(new Paciente("10123457A", "Pedro",
			"Suecaz Santos", "Calle San Vicente, 4 Valencia", 123453251, 25,
			'H'));
			
			
			
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
