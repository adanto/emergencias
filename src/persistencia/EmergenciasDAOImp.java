package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Ambulancia;
import logica.Emergencia;
import excepciones.DAOExcepcion;

public class EmergenciasDAOImp implements IEmergenciaDAO{
	protected ConnectionManager connManager;

	public EmergenciasDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	
	public void crearEmergencia(Emergencia em) throws DAOExcepcion {	
		try{
			connManager.connect();
			connManager.updateDB("INSERT INTO EMERGENCIA (codemergencia, latitud, longitud, fecha, hora, nombreH, dni, numRegistro) VALUES ('"+em.getCodEmergencia()+"', '"+em.getLat()+"', '"+em.getLong()+"', '"+em.getFecha()+"', '"+em.getHora()+"', '"+em.getHosp().getNombre()+"', '"+em.getPaciente().getDni()+"', '"+em.getAmb().getNumRegistro()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}


	public Emergencia buscarEmergencia(int numero) throws DAOExcepcion {

		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from EMERGENCIA where codemergencia= "+numero+"");
			connManager.close();
		
			if (rs.next()){
				Emergencia a = new Emergencia(rs.getInt("CODEMERGENCIA"), rs.getDouble("LATITUD"), rs.getDouble("LONGITUD"), rs.getString("FECHA"), rs.getString("HORA"));
				//rs.getInt("NUMREGISTRO"),rs.getString("EQUIPO")
				return a;
			}
		}catch (SQLException e){	
			throw new DAOExcepcion(e);
		}	
		return null;
	}


	public List<Emergencia> listarEmergencias() throws DAOExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
