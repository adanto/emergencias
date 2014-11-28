package persistencia;

import java.util.List;

import logica.Hospital;
import logica.Especialidad;
import excepciones.DAOExcepcion;

public interface IHospital {

	public Hospital buscaHospital(String nombre)throws DAOExcepcion;
	public void creaHospital (Hospital h)throws DAOExcepcion;
	public List <Hospital> listaHospitales() throws DAOExcepcion;
	public List <Especialidad> listaEspecialidad(String nombre)throws DAOExcepcion;
}
