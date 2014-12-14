package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Especialidad;
import logica.Hospital;
import excepciones.DAOExcepcion;

public class HospitalDAOImp implements IHospital{
	
	protected ConnectionManager connManager;
	
	public HospitalDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public void creaHospital(Hospital h)throws DAOExcepcion
	{
		try{
			connManager.connect();
			connManager.updateDB("insert into Hospital(nombreH, direccion, latitud, longitud) values ('"+h.getNombre()+"','"+h.getDireccion()+"','"+h.getLatitud()+"', '"+h.getLongitud()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);	}
	}
	
	public Hospital buscaHospital(String nombre)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from Hospital where NOMBREH= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
				return new Hospital(nombre,rs.getString("DIRECCION"), rs.getFloat("LATITUD"), rs.getFloat("LONGITUD"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
	
	public List <Hospital> listaHospitales() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from Hospital");						
			connManager.close();
	  	  
			List<Hospital> listaHospital=new ArrayList<Hospital>();
				
			try{				
				while (rs.next()){
					Hospital h = buscaHospital(rs.getString("NOMBREH"));	 
					listaHospital.add(h);
				}
				return listaHospital;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	
	public List <Especialidad> listaEspecialidad(String nombre) throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select DISTINCT E.codEsp from hospital A, especialidad E where A.nombreH='"+nombre+"' and  E.nombreH = A.nombreH ");						

			connManager.close();
	  	  
			List<Especialidad> listaEspecialidad=new ArrayList<Especialidad>();
				
			try{				
				while (rs.next()){
					Especialidad e= buscaEspecialidad(rs.getString("codEsp"));
					listaEspecialidad.add(e);
				}
				return listaEspecialidad;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	
	public Especialidad buscaEspecialidad(String nombre)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from Especialidad where codEsp= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
				return new Especialidad(nombre);
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
}
