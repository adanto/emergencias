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
		ResultSet rs=connManager.queryDB("select * from AMBULANCIA where numregistro= "+numero+"");
		connManager.close();
	
		if (rs.next()){
			Ambulancia a = new Ambulancia(rs.getInt("NUMREGISTRO"),rs.getString("EQUIPO"),rs.getFloat("LATITUD"),rs.getFloat("LONGITUD"),rs.getBoolean("DISPONIBILIDAD"));
			return a;
		}
		 
	}
	catch (SQLException e){	throw new DAOExcepcion(e);}	
		return null;
}

	public void crearAmbulancia (Ambulancia a)throws DAOExcepcion
	{
		try{
			connManager.connect();
			connManager.updateDB("insert into AMBULANCIA (numregistro,equipo,latitud,longitud,disponibilidad) values ('"+a.getNumRegistro()+"','"+a.getEquipo()+"','"+a.getLatitud()+"', '"+a.getLongitud()+"', '"+a.getDisp()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);	}
	}
	
	public void setDisp(int numero, boolean disp) throws DAOExcepcion{
		try{

			System.out.println("1");
			connManager.connect();

			System.out.println("2");
			connManager.updateDB("update AMBULANCIA set disponibilidad ='"+disp+"' where numregistro='"+numero+"'");
			
			System.out.println("3");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);
		}
		}
	
	
	public void cambiarCoor(int numero, float latitud, float longitud) throws DAOExcepcion{
		
		try{
			connManager.connect();
			connManager.updateDB("update into AMBULANCIA A (LATITUD, LONGITUD) values ('"+latitud+"','"+longitud+"') WHERE A.numregistro = '"+numero+"'");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);	}
		}

	}

