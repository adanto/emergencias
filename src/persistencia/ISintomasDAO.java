package persistencia;

import java.util.List;

import logica.Sintoma;
import excepciones.DAOExcepcion;

public interface ISintomasDAO {
	public Sintoma buscarSintoma(String nombre)throws DAOExcepcion;
	public void crearSintoma (Sintoma p)throws DAOExcepcion;
	public List <Sintoma> listarSintoma() throws DAOExcepcion;
	public void deleteSintoma(String cod) throws DAOExcepcion;
}
