package persistencia;
import java.util.List;
import excepciones.*;
import logica.Emergencia;

public interface IEmergenciaDAO {
	public Emergencia buscarEmergencia(String text) throws DAOExcepcion;
	public void crearEmergencia(Emergencia e) throws DAOExcepcion;
	public List<Emergencia> listarEmergencias() throws DAOExcepcion;

	public int ambMinima(String cod) throws DAOExcepcion;
	public int ambMinima(double lon, double lat) throws DAOExcepcion;
	public String hospMinimo(String cod) throws DAOExcepcion;
	public String hospMinimo(double lon, double lat) throws DAOExcepcion;
	
	public List <Emergencia> listaEmergencias() throws DAOExcepcion;
	
	public void deleteEmergencia(String cod) throws DAOExcepcion;
}
