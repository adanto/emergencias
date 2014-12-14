package persistencia;
import java.util.List;

import excepciones.*;
import logica.Emergencia;

public interface IEmergenciaDAO {
	public Emergencia buscarEmergencia(int numero) throws DAOExcepcion;
	public void crearEmergencia(Emergencia e) throws DAOExcepcion;
	public List<Emergencia> listarEmergencias() throws DAOExcepcion;
}
