package logica;
import java.util.Iterator;
import java.util.List;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class Aplicacion {
	public static void main(String[] args) throws LogicaExcepcion{
		//Se crea el Servicio de Emergencias
		ServicioEmergencia emergencias = new ServicioEmergencia();

		System.out.println("Empezamos en cero,");
		// AQUÍ INCLUIR CADA UNA DE LAS EJECUCIONES
		//--------------------------------------------------------------------
		
		Iterator<Paciente> listaPac = emergencias.getPatients();
		
		while(listaPac.hasNext()){
			Paciente pac = listaPac.next();
			listaPac.remove();
			emergencias.borrar(pac);
			System.out.println(" DNI: "+pac.getDni()+
			" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
			" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
			" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
		}
		

		listaPac = emergencias.getPatients();
		while(listaPac.hasNext()){
			Paciente pac = listaPac.next();
			listaPac.remove();
			emergencias.borrar(pac);
			System.out.println(" DNI: "+pac.getDni()+
			" Nombre: "+pac.getNombre()+" Apellidos: "+pac.getApellidos()+
			" Dirección: "+pac.getDireccion()+" Teléf.: "+pac.getTelefono()+
			" Edad: "+pac.getEdad()+" Sexo: "+pac.getSexo());
		}
	}
}
