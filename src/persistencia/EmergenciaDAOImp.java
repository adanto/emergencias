package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.*;
import logica.Emergencia;
import logica.Hospital;
import excepciones.DAOExcepcion;

public class EmergenciaDAOImp implements IEmergenciaDAO{
	protected ConnectionManager connManager;

	public EmergenciaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	

	public List <Emergencia> listaEmergencias() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("SELECT * FROM Emergencia E, Paciente P, Hospital H, Ambulancia A WHERE E.dni = P.dni AND E.nombreH = H.nombreH AND E.numRegistro = A.numRegistro");						
			connManager.close();
	  	  
			List<Emergencia> listaEmergencias=new ArrayList<Emergencia>();
				
			try{				
				while (rs.next()){					
					Emergencia a = new Emergencia(rs.getInt("E.CODEMERGENCIA"), rs.getDouble("E.LATITUD"), rs.getDouble("E.LONGITUD"), rs.getString("E.HORA"), rs.getString("E.FECHA")); 
					Ambulancia amb = new Ambulancia(rs.getInt("A.NUMREGISTRO"), rs.getString("A.EQUIPO"), rs.getDouble("A.LATITUD"), rs.getDouble("A.LONGITUD"), rs.getBoolean("A.DISPONIBILIDAD"));
					Paciente pac = new Paciente(rs.getString("P.DNI"), rs.getString("P.NOMBRE"), rs.getString("P.APELLIDOS"), rs.getString("P.DIRECCION"), rs.getInt("P.NUMERO"), rs.getInt("P.EDAD"), rs.getString("P.SEXO").charAt(0));
					Hospital hosp = new Hospital(rs.getString("H.NOMBREH"), rs.getString("H.DIRECCION"), rs.getDouble("H.LATITUD"), rs.getDouble("H.LONGITUD"));
					a.setAmb(amb);
					a.setPaciente(pac);
					a.setHosp(hosp);
					listaEmergencias.add(a);
				}
				return listaEmergencias;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	
	
	
	
	
	public int ambMinima(double lon, double lat) throws DAOExcepcion{
		int valB = -1;
		double distB = 0;
		int valP = -1;
		double distP = 0;
		int val = -1;
		//double dist = 0;
		
		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, ((A.latitud-'"+lon+"')*(A.latitud-'"+lon+"')+(A.longitud-'"+lat+"')*(A.longitud-'"+lat+"')+(H.latitud-'"+lon+"')*(H.latitud-'"+lon+"')+(H.longitud-'"+lat+"')*(H.longitud-'"+lat+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE ORDER BY Distancia";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valB = rs.getInt("numRegistro");
				distB = rs.getDouble("Distancia");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo");
			throw new DAOExcepcion(e);}
		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))=(SELECT MIN((H1.longitud-'"+lon+"')*(H1.longitud-'"+lon+"')+(H1.latitud-'"+lat+"')*(H1.latitud-'"+lat+"')) FROM Hospital H1) AND ((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"'))=(SELECT MIN((A1.longitud-'"+lon+"')*(A1.longitud-'"+lon+"')+(A1.latitud-'"+lat+"')*(A1.latitud-'"+lat+"')) FROM Ambulancia A1 WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE)";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valP = rs.getInt("numRegistro");
				distP = rs.getDouble("Longitud");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo");
			throw new DAOExcepcion(e);}

		System.out.println("\nAmbulancia Base: "+valB+" a distancia: RAIZ("+distB+")");
		System.out.println("Ambulancia Privada: "+valP+" a distancia: RAIZ("+distP+")");
		
		if(distP>=distB){
			val=valB;
			//dist=distB;
			
		}else{
			val=valP;
			//dist=distP;
		}
		
		return val;
	}

	public String hospMinimo(double lon, double lat) throws DAOExcepcion{
		String valB = "-1";
		double distB = 0;
		String valP = "-1";
		double distP = 0;
		String val = "-1";
		//double dist = 0;
		
		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, A.nombreH , ((A.latitud-'"+lon+"')*(A.latitud-'"+lon+"')+(A.longitud-'"+lat+"')*(A.longitud-'"+lat+"')+(H.latitud-'"+lon+"')*(H.latitud-'"+lon+"')+(H.longitud-'"+lat+"')*(H.longitud-'"+lat+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE ORDER BY Distancia";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valB = rs.getString("nombreH");
				distB = rs.getDouble("Distancia");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo");
			throw new DAOExcepcion(e);}
		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))=(SELECT MIN((H1.longitud-'"+lon+"')*(H1.longitud-'"+lon+"')+(H1.latitud-'"+lat+"')*(H1.latitud-'"+lat+"')) FROM Hospital H1) AND ((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"'))=(SELECT MIN((A1.longitud-'"+lon+"')*(A1.longitud-'"+lon+"')+(A1.latitud-'"+lat+"')*(A1.latitud-'"+lat+"')) FROM Ambulancia A1 WHERE A1.tipo = 'P' AND A1.disponibilidad = TRUE)";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valP = rs.getString("nombreH");
				distP = rs.getDouble("Longitud");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo");
			throw new DAOExcepcion(e);}

		System.out.println("\nAmbulancia Base: "+valB+" a distancia: RAIZ("+distB+")");
		System.out.println("Ambulancia Privada: "+valP+" a distancia: RAIZ("+distP+")");
		
		if(distP>=distB){
			val=valB;
			//dist=distB;
			
		}else{
			val=valP;
			//dist=distP;
		}
		
		return val;
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
