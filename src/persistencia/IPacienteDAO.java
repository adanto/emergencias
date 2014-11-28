//IPacienteDAO
package persistencia;
import java.util.List;

import excepciones.*;
import logica.Paciente;;

public interface IPacienteDAO {
	public Paciente buscarPaciente(String dni)throws DAOExcepcion;
	public void crearPaciente (Paciente p)throws DAOExcepcion;
	public List <Paciente> listarPacientes() throws DAOExcepcion;
	public void borrarPaciente(Paciente p) throws DAOExcepcion;
}
