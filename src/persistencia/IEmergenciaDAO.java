package persistencia;
import java.util.ArrayList;
import java.util.List;

import excepciones.*;
import logica.Emergencia;
import logica.Sintoma;

public interface IEmergenciaDAO {
	public Emergencia buscarEmergencia(String text) throws DAOExcepcion;
	public void crearEmergencia(Emergencia e) throws DAOExcepcion;
	public List<Emergencia> listarEmergencias() throws DAOExcepcion;
	
	public int ambMinima(double lon, double lat) throws DAOExcepcion;
	public int ambMinima(double lon, double lat, ArrayList<Sintoma> sintomas) throws DAOExcepcion;
	public String hospMinimo(double lon, double lat) throws DAOExcepcion;
	public String hospMinimo(double lon, double lat, ArrayList<Sintoma> sintomas) throws DAOExcepcion;
	
	public List <Emergencia> listaEmergencias() throws DAOExcepcion;
}
