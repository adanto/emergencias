//IAmbulanciaDAO
package persistencia;

import excepciones.*;
import logica.Ambulancia;

public interface IAmbulanciaDAO {
	public Ambulancia buscarAmbulancia(int numero)throws DAOExcepcion;
	public void crearAmbulancia (Ambulancia a)throws DAOExcepcion;
	public void cambiarCoor(int numero, float latitud, float longitud) throws DAOExcepcion;
	public void setDisp(int numero, boolean disp) throws DAOExcepcion;
}
