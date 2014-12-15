package logica;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class Aplicacion {
	private static Scanner keyboard;

	public static void main(String[] args) throws LogicaExcepcion, DAOExcepcion{
		//Se crea el Servicio de Emergencias
		ServicioEmergencia emergencias = new ServicioEmergencia();

		System.out.println("Running app...");
		// AQUÍ INCLUIR CADA UNA DE LAS EJECUCIONES
		//--------------------------------------------------------------------
		
		int sel = 1;
		int lon = 1, lat = 2;
		keyboard = new Scanner(System.in);
		while(sel!=0){
			System.out.println("\nSeleccione su opción\n");
			System.out.println("0 - SALIR");
			System.out.println("1 - Buscar paciente, si no está lo crea y luego lo busca");
			System.out.println("2 - Busca paciente, si está lo muestra, añade un paciente y los imprime todos");
			System.out.println("3 - Añade paciente y los lista todos");
			System.out.println("4 - Elimina el de la primera opción");
			System.out.println("5 - Mostrar todos listados");
			System.out.println("6 - Buscar ambulancia. Si no está la añade");
			System.out.println("7 - Número de ambulancias");
			System.out.println("8 - Lista ambulancias");
			System.out.println("9 - Cambia disponibilidad de la ambulancia 3");
			System.out.println("10- Cambia direccion de la ambulancia 3");
			System.out.println("11- Lista todas las especialidades del hospital de nombre Hospital1");
			System.out.println("12- Numero de hospitales totales en la DB");
			System.out.println("13- Información de todos los hospitales de la DB");
			System.out.println("14- Get Emergencia a lo pro");
			System.out.println("15- Obtener el numero de ambulancia más cercano a una posición");
			System.out.println("16- Query del caso de uso para obtener la mejor ambulancia - Testing strings");
			System.out.println("17- Obtener el nombre del hospital más cercano a una posición");
			System.out.println("18- Obtener la lista de emergencias\n");
			sel = keyboard.nextInt();
			System.out.println("");
			
			switch(sel){
			case 1: 
				// Primera ejecución
				//Se busca un paciente y se crea si no existe
				if (emergencias.buscarP("10123456A")==null)
				emergencias.anyadir(new Paciente("10123456A", "Juan",
				"Martinez Gandia", "Calle Santiago, 4 Valencia", 123453250,
				50, 'H'));
				//Se busca un paciente y si lo encuentra se muestran sus datos
				Paciente p = emergencias.buscarP("10123456A");
				if (p!=null)
					
				System.out.println(" DNI: "+p.getDni()+" Nombre: "+p.getNombre()+
				" Apellidos: "+p.getApellidos()+
				" Dirección: "+p.getDireccion()+" Teléf.: "+p.getTelefono()+
				" Edad: "+p.getEdad()+" Sexo: "+p.getSexo());
				
				
				break;
			case 2: 
				//Segunda ejecución
				//Se busca un paciente y si lo encuentra se muestran sus datos
				Paciente p1 = emergencias.buscarP("10123456A");
				if (p1!=null)
				System.out.println(" DNI: "+p1.getDni()+
				" Nombre: "+p1.getNombre()+" Apellidos: "+p1.getApellidos()+
				" Dirección: "+p1.getDireccion()+" Teléf.: "+p1.getTelefono()+
				" Edad: "+p1.getEdad()+" Sexo: "+p1.getSexo());
				//Se crea un paciente
				emergencias.anyadir(new Paciente("10123457A", "Pedro",
				"Suecaz Santos", "Calle San Vicente, 4 Valencia", 123453251, 25,
				'H'));
				//Se listan todos los pacientes
				Iterator<Paciente> listaPacientes = emergencias.getPatients();
				while(listaPacientes.hasNext()){
					Paciente pac = listaPacientes.next();
					listaPacientes.remove();
						System.out.println(" DNI: "+pac.getDni()+
									" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
									" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
									" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
				}
				break;
			case 3:
				//Tercera ejecución
				//Se crea un paciente
				emergencias.anyadir(new Paciente("10123458A", "Ana",
				"Bezo Tosa", "Calle Francia, 4 Valencia", 923453251, 34, 'M'));
				//Se listan todos los pacientes
				Iterator<Paciente> listaPacientes3 = emergencias.getPatients();
				while(listaPacientes3.hasNext()){
					Paciente pac = listaPacientes3.next();
					listaPacientes3.remove();
						System.out.println(" DNI: "+pac.getDni()+
									" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
									" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
									" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
				}
				break;
			case 4:
				//Cuarta ejecución

				if (emergencias.buscarP("10123456A")!=null)
				emergencias.borrar(new Paciente("10123456A", "Juan",
						"Martinez Gandia", "Calle Santiago, 4 Valencia", 123453250,
						50, 'H'));
				break;
			case 5:
				Iterator<Paciente> listaPacientes5 = emergencias.getPatients();
				while(listaPacientes5.hasNext()){
					Paciente pac = listaPacientes5.next();
					listaPacientes5.remove();
						System.out.println(" DNI: "+pac.getDni()+
									" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
									" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
									" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
				}
				break;
			case 6:
				if(emergencias.buscarA(3)==null){
					System.out.println("Añadiendo");
					emergencias.anyadir(new Ambulancia(3, "Equipo1", 12.3, 31.2, true));
				}else{
					System.out.println("El bicho ya está añadido");
				}
				
				break;
			case 7:
				emergencias.numeroAmbulancias();
				break;
			case 8:
				System.out.println("Este metodo no está implementedo (innecesario)");
				break;
			case 9:
				emergencias.setDisp(3, true);
				break;
			case 10:
				emergencias.cambiarCoor(3, 10, 11);
				break;
			case 11:
				emergencias.listarEspecialidad("Valencia del Mar");
				emergencias.especialidadesLocal();
				break;
			case 12:
				System.out.println(emergencias.getHospitales().size());
				break;
			case 13:
				emergencias.nombHospitales();
				break;
			case 14:
				Emergencia em = emergencias.buscarEM("1");
				if(em!=null){
					System.out.println("COD: "+em.getCodEmergencia()+", posición: ("+em.getLat()+","+em.getLong()+") a fecha de "+em.getFecha()+" "+em.getHora());
					System.out.println(em.getHosp());
				}else{
					System.out.println("No existe la emeregencia");
				}
				break;
			case 15:
				System.out.println("Ambulancia más cercana a (10, 10) es la numRegistro: "+emergencias.ambMinima(10,10));
				System.out.println("Ambulancia más cercana a (-10, 10) es la numRegistro: "+emergencias.ambMinima(-10,10));
				System.out.println("Ambulancia más cercana a (10, -10) es la numRegistro: "+emergencias.ambMinima(10,-10));
				System.out.println("Ambulancia más cercana a (-10, -10) es la numRegistro: "+emergencias.ambMinima(-10,-10));
				break;
			case 16:
				String query1 = "SELECT A.numRegistro, ((A.latitud-'"+lon+"')*(A.latitud-'"+lon+"')+(A.longitud-'"+lat+"')*(A.longitud-'"+lat+"')+(H.latitud-'"+lon+"')*(H.latitud-'"+lon+"')+(H.longitud-'"+lat+"')*(H.longitud-'"+lat+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE ORDER BY Distancia";
				String query2 = "SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))=(SELECT MIN((H1.longitud-'"+lon+"')*(H1.longitud-'"+lon+"')+(H1.latitud-'"+lat+"')*(H1.latitud-'"+lat+"')) FROM Hospital H1) AND ((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"'))=(SELECT MIN((A1.longitud-'"+lon+"')*(A1.longitud-'"+lon+"')+(A1.latitud-'"+lat+"')*(A1.latitud-'"+lat+"')) FROM Ambulancia A1 WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE)";
				System.out.println(query1);
				System.out.println(query2);
				break;

			case 17:
				System.out.println("Hospital más cercana a (10, 10) es la nombreH: "+emergencias.hospMinimo(10,10));
				System.out.println("Hospital más cercana a (-10, 10) es la nombreH: "+emergencias.hospMinimo(-10,10));
				System.out.println("Hospital más cercana a (10, -10) es la nombreH: "+emergencias.hospMinimo(10,-10));
				System.out.println("Hospital más cercana a (-10, -10) es la nombreH: "+emergencias.hospMinimo(-10,-10));
				break;
			case 18:
				List<Emergencia> emergenciasList = emergencias.listaEmergencias();
				for(Emergencia h : emergenciasList){
					System.out.println(h.getCodEmergencia()+" "+h.getFecha()+" "+h.getHora()+" "+h.getLat()+" "+h.getLong());
					Paciente pac1 = h.getPaciente();
					System.out.println(" DNI: "+pac1.getDni()+
							" Nombre: "+pac1.getNombre()+" Apellidos: "+pac1.getApellidos()+
							" Dirección: "+pac1.getDireccion()+" Teléf.: "+pac1.getTelefono()+
							" Edad: "+pac1.getEdad()+" Sexo: "+pac1.getSexo());
					System.out.println(h.getHosp().getNombre());
					System.out.println(h.getAmb().getNumRegistro());
					
				}
			}
		}
	}
}
