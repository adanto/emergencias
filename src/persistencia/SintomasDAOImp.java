package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Sintoma;
import excepciones.DAOExcepcion;

public class SintomasDAOImp implements ISintomasDAO {
	protected ConnectionManager connManager;

	public SintomasDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	public void deleteSintoma(String cod) throws DAOExcepcion{
		
		connManager.connect();										
		connManager.updateDB("delete from sintoma where codemergencia = '"+cod+"'");
		connManager.close();
	}
	
	public void crearSintoma(Sintoma sint) throws DAOExcepcion {
	// TODO Auto-generated method stub			
		try{
			connManager.connect();  	
			connManager.updateDB("insert into SINTOMA values ('"+sint.getNombre()+"', '"+sint.getEstado()+"','"+sint.getDescripcion()+"','"+sint.getDuracion()+"', '"+sint.getCodEsp()+"', null, '"+sint.getEmerg()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
	  
	public List <Sintoma> listarSintoma() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA");						
			connManager.close();
	  	  
			List<Sintoma> listaSintoma=new ArrayList<Sintoma>();
				
			try{				
				while (rs.next()){
					Sintoma pa = buscarSintoma(rs.getString("NOMBRE"));	 
					listaSintoma.add(pa);
				}
				return listaSintoma;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	  
	public Sintoma buscarSintoma(String nombre)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA where NOMBRE= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
				//	public Sintoma(String nombre, String desc, int duracion, String est, String emergencia, String codEsp){
				
				return new Sintoma(nombre, rs.getString("DESCRIPCION"), rs.getInt("DURACION"), rs.getString("ESTADO"), rs.getString("CODEMERGENCIA"), rs.getString("CODEMERGENCIA"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
}