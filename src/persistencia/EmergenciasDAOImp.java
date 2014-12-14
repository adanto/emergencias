package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Emergencia;
import excepciones.DAOExcepcion;

public class EmergenciasDAOImp {
	protected ConnectionManager connManager;

	public EmergenciasDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	public void crearEmergencia(Emergencia em) throws DAOExcepcion {
	// TODO Auto-generated method stub			
		try{
			connManager.connect();
			//connManager.updateDB();
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}

}
