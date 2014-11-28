package logica;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class Aplicacion {
	public static void main(String[] args) throws LogicaExcepcion{
		//Se crea el Servicio de Emergencias
		ServicioEmergencia emergencias = new ServicioEmergencia();

		System.out.println("Running app...");
		// AQUÍ INCLUIR CADA UNA DE LAS EJECUCIONES
		//--------------------------------------------------------------------
		
		Iterator<Paciente> listaPac = emergencias.getPatients();
		int sel = 1;
		Scanner keyboard = new Scanner(System.in);
		while(sel!=0){
			System.out.println("\nSeleccione su opción\n");
			System.out.println("1 - Buscar paciente, si no está lo crea y luego lo busca");
			System.out.println("2 - Busca paciente, si está lo muestra, añade un paciente y los imprime todos");
			System.out.println("3 - Añade paciente y los lista todos\n");
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
			}
		}

		
	}
}
