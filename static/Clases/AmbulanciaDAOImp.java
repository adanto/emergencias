package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import persistencia.HospitalDAOImp;
import logica.Ambulancia;
import logica.BHospital;
import logica.Hospital;
import logica.Paciente;
import logica.Privada;
import excepciones.DAOExcepcion;

public class AmbulanciaDAOImp implements IAmbulanciaDAO{
	
	protected ConnectionManager connManager;
	
	public AmbulanciaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	//Acabar esta puta mierda
	public Ambulancia buscarAmbulancia(int numero)throws DAOExcepcion
	{
	try{
		connManager.connect();
		ResultSet rs=connManager.queryDB("select * from AMBULANCIA where numero= '"+numero+"'");
		ResultSet rs2=connManager.queryDB("select COUNT(*) from AMBULANCIA A, PRIVADA P where A.NUMREGISTRO = P.NUMREGISTRO");
		ResultSet rs3=connManager.queryDB("select * from HOSPITAL H, BHOSPITAL B where B.idhospital = H.nombre");
		connManager.close();
	
		if (rs.next()){
			if(rs2.equals(1))
			{
				return new Privada(rs.getString("compañia"),rs.getString("equipo"),rs.getInt(numero), rs.getFloat("latitud"), rs.getFloat("longitud"));
			}
			else if(rs2.equals(0))
			{
				return new BHospital(new Hospital(rs3.getString("nombre"), rs3.getString("direccion"),rs3.getFloat("LATITUD"), rs3.getFloat("LONGITUD")), rs.getString("equipo"),rs.getInt(numero), rs.getFloat("latitud"), rs.getFloat("longitud"));
			}
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
			if(a instanceof Privada) 
			{
				Privada amb = (Privada)a;
				connManager.updateDB("insert into PRIVADA (numregistro, compañia) values ('"+a.getNumRegistro()+"','"+amb.getCompanyia()+"')");
			}
			else 
			{
				BHospital amb = (BHospital)a;
				connManager.updateDB("insert into BHOSPITAL (numregistro, idhospital) values ('"+a.getNumRegistro()+"','"+amb.getNumRegistro()+"')");
			
			}
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
