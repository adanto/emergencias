package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Ambulancia;
import excepciones.DAOExcepcion;

public class AmbulanciaDAOImp implements IAmbulanciaDAO{
	
	protected ConnectionManager connManager;
	
	public AmbulanciaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergencias");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public Ambulancia buscarAmbulancia(int numero)throws DAOExcepcion
	{
	try{
		connManager.connect();
		ResultSet rs=connManager.queryDB("select * from AMBULANCIA where numero= '"+numero+"'");
		connManager.close();
	
		if (rs.next()){
			return new Ambulancia(rs.getInt("NUMREGISTRO"),rs.getString("EQUIPO"),rs.getFloat("LATITUD"), rs.getFloat("LONGITUD"));
		}
		 
	}
	catch (SQLException e){	throw new DAOExcepcion(e);}	
		return null;
}

	public void crearAmbulancia (Ambulancia a)throws DAOExcepcion
	{
		try{
			connManager.connect();
			connManager.updateDB("insert into AMBULANCIA (numregistro,equipo,latitud,longitud) values ('"+a.getNumRegistro()+"','"+a.getEquipo()+"','"+a.getLatitud()+"', '"+a.getLongitud()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);	}
	}
	
	public void cambiarCoor(int numero, float latitud, float longitud) throws DAOExcepcion
		{
		try{
			connManager.connect();
			connManager.updateDB("update into AMBULANCIA A (LATITUD, LONGITUD) values ('"+latitud+"','"+longitud+"') WHERE A.numregistro = '"+numero+"'");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);	}
		}

	}
