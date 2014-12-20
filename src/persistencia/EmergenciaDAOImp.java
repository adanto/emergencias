package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.*;
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
			ResultSet rs=connManager.queryDB("SELECT CODEMERGENCIA, E.LATITUD AS LATITUDEME, E.LONGITUD AS LONGITUDEME, FECHA, HORA, NOMBREH, DNI, NUMREGISTRO, NOMBRE, APELLIDOS, P.DIRECCION AS DIRECCIONPAC, TELEFONO, EDAD, SEXO, H.DIRECCION AS DIRECCIONHOSP, H.LATITUD AS LATITUDHOSP, H.LONGITUD AS LONGITUDHOSP, EQUIPO, A.LATITUD AS LATITUDAMB, A.LONGITUD AS LONGITUDAMB, DISPONIBILIDAD FROM Emergencia E, Paciente P, Hospital H, Ambulancia A WHERE E.dni = P.dni AND E.nombreH = H.nombreH AND E.numRegistro = A.numRegistro");						

			connManager.close();
	  	  
			List<Emergencia> listaEmergencias=new ArrayList<Emergencia>();

			try{				
				while (rs.next()){			
					Emergencia a = new Emergencia(rs.getString("CODEMERGENCIA"), rs.getDouble("LATITUDEME"), rs.getDouble("LONGITUDEME"), rs.getString("HORA"), rs.getString("FECHA")); 

					Ambulancia amb = new Ambulancia(rs.getInt("NUMREGISTRO"), rs.getString("EQUIPO"), rs.getDouble("LATITUDAMB"), rs.getDouble("LONGITUDAMB"), rs.getBoolean("DISPONIBILIDAD"));

					Paciente pac = new Paciente(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"), rs.getString("DIRECCIONPAC"), rs.getInt("TELEFONO"), rs.getInt("EDAD"), rs.getString("SEXO").charAt(0));

					Hospital hosp = new Hospital(rs.getString("NOMBREH"), rs.getString("DIRECCIONHOSP"), rs.getDouble("LATITUDHOSP"), rs.getDouble("LONGITUDHOSP"));

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

	public int ambMinima(String cod) throws DAOExcepcion{
		int valB = -1;
		double distB = 0;
		int valP = -1;
		double distP = 0;
		int val = -1;
		Emergencia EM = buscarEmergencia(cod);
		double lat = EM.getLat();
		double lon = EM.getLong();

		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, H.nombreH, ((A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')+(A.longitud -'"+lon+"')*(A.longitud -'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"')+(H.longitud -'"+lon+"')*(H.longitud -'"+lon+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE AND H.nombreH IN (SELECT H1.nombreH FROM Hospital H1 WHERE NOT EXISTS ( SELECT * FROM SINTOMA S WHERE S.codEmergencia = '"+cod+"' AND S.codEsp NOT IN (SELECT ES.codEsp FROM Especialidad ES WHERE ES.nombreH = H1.nombreH)))ORDER BY Distancia";

			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valB = rs.getInt("numRegistro");
				distB = rs.getDouble("Distancia");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo 1");
			throw new DAOExcepcion(e);}

		
		try{
			connManager.connect();
			String query ="SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND H.nombreH IN (SELECT H1.nombreH FROM Hospital H1 WHERE NOT EXISTS (SELECT * FROM SINTOMA S WHERE S.codEmergencia = '"+cod+"' AND S.codEsp NOT IN (SELECT ES.codEsp FROM Especialidad ES WHERE ES.nombreH = H1.nombreH)))";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valP = rs.getInt("numRegistro");
				distP = rs.getDouble("Longitud");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo 2");
			throw new DAOExcepcion(e);}

		
		if(distP>=distB && distB != 0){
			val=valB;

			
		}else 
			if(distP != 0){
				val=valP;

			}

		System.out.println(distB+" "+valB+" "+distP+" "+valP+" ---> "+val);
		return val;
	}

	public String hospMinimo(String cod) throws DAOExcepcion{
		String valB = "null";
		double distB = 0;
		String valP = "null";
		double distP = 0;
		String val = "null";
		Emergencia EM = buscarEmergencia(cod);
		double lat = EM.getLat();
		double lon = EM.getLong();

		try{
			connManager.connect();
			String query = "SELECT A.numRegistro, H.nombreH, ((A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')+(A.longitud -'"+lon+"')*(A.longitud -'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"')+(H.longitud -'"+lon+"')*(H.longitud -'"+lon+"')) AS Distancia FROM Ambulancia A LEFT JOIN Hospital H ON A.nombreH = H.nombreH WHERE A.tipo = 'B' AND A.disponibilidad = TRUE AND H.nombreH IN (SELECT H1.nombreH FROM Hospital H1 WHERE NOT EXISTS ( SELECT * FROM SINTOMA S WHERE S.codEmergencia = '"+cod+"' AND S.codEsp NOT IN (SELECT ES.codEsp FROM Especialidad ES WHERE ES.nombreH = H1.nombreH)))ORDER BY Distancia";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valB = rs.getString("nombreH");
				distB = rs.getDouble("Distancia");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo 1");
			throw new DAOExcepcion(e);}

		
		try{
			connManager.connect();
			String query ="SELECT A.numRegistro, H.nombreH, ((H.longitud-'"+lon+"')*(H.longitud-'"+lon+"')+(H.latitud-'"+lat+"')*(H.latitud-'"+lat+"'))+((A.longitud-'"+lon+"')*(A.longitud-'"+lon+"')+(A.latitud-'"+lat+"')*(A.latitud-'"+lat+"')) AS Longitud FROM Ambulancia A, Hospital H WHERE A.tipo = 'P' AND A.disponibilidad = TRUE AND H.nombreH IN (SELECT H1.nombreH FROM Hospital H1 WHERE NOT EXISTS (SELECT * FROM SINTOMA S WHERE S.codEmergencia = '"+cod+"' AND S.codEsp NOT IN (SELECT ES.codEsp FROM Especialidad ES WHERE ES.nombreH = H1.nombreH)))ORDER BY Longitud";
			ResultSet rs=connManager.queryDB(query);
			connManager.close();
			if (rs.next()){
				valP = rs.getString("nombreH");
				distP = rs.getDouble("Longitud");
			}
		}catch (Exception e){	
			System.out.println("Estamos en el fondo 2");
			throw new DAOExcepcion(e);}

		
		if(distP>=distB && distB != 0){
			val=valB;

			
		}else 
			if(distP != 0){
				val=valP;

			}

		System.out.println(distB+" "+valB+" "+distP+" "+valP+" ---> "+val);
		return val;
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
		String hospital;
		String ambulancia;
		if(em.getHosp()!=null){
			hospital = "'"+em.getHosp().getNombre()+"'";
		}else{
			hospital = null;
		}
		if(em.getAmb()!=null){
			ambulancia = "'"+em.getAmb().getNumRegistro()+"'";
		}else{
			ambulancia = null;
		}
		
		try{
			connManager.connect();
			connManager.updateDB("INSERT INTO EMERGENCIA VALUES ('"+em.getCodEmergencia()+"', "+em.getLat()+", "+em.getLong()+", '"+em.getFecha()+"', '"+em.getHora()+"', "+hospital+", '"+em.getPaciente().getDni()+"', "+ambulancia+")");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
	
	public void deleteEmergencia(String cod) throws DAOExcepcion{
		connManager.connect();
		connManager.queryDB("delete from EMERGENCIA where codemergencia= '"+cod+"'");
		connManager.close();	
	}


	public Emergencia buscarEmergencia(String numero) throws DAOExcepcion {

		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from EMERGENCIA where codemergencia= '"+numero+"'");
			connManager.close();
		
			if (rs.next()){
				Emergencia a = new Emergencia(rs.getString("CODEMERGENCIA"), rs.getDouble("LATITUD"), rs.getDouble("LONGITUD"), rs.getString("FECHA"), rs.getString("HORA"));
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
