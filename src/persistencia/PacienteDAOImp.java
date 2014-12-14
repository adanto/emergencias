	//****************************************************************
	//****    PATRÓN SINGLETON
	//****************************************************************
//	private static PacienteDAOImp paciente; 
//	
//	public static PacienteDAOImp dameDAO() throws DAOExcepcion{
//		if (paciente==null)		paciente = new PacienteDAOImp();
//		return paciente;
//	}
	
//PacienteDAOImp
package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Paciente;
import excepciones.DAOExcepcion;

public class PacienteDAOImp implements IPacienteDAO {
	protected ConnectionManager connManager;

	public PacienteDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasDB");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public void crearPaciente(Paciente pa) throws DAOExcepcion {
	// TODO Auto-generated method stub			
		try{
			connManager.connect();
			connManager.updateDB("insert into PACIENTE (DNI, NOMBRE, APELLIDOS, DIRECCION, TELEFONO, EDAD, SEXO) values ('"+pa.getDni()+"','"+pa.getNombre()+"','"+pa.getApellidos()+"', '"+pa.getDireccion()+"', "+pa.getTelefono()+", "+pa.getEdad()+",'"+pa.getSexo()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}

	
	public void borrarPaciente(Paciente pa) throws DAOExcepcion {
	// TODO Auto-generated method stub			
		try{
			connManager.connect();
			connManager.updateDB("DELETE FROM PACIENTE WHERE DNI='"+pa.getDni()+"'");
			System.out.println("DELETE FROM PACIENTE WHERE DNI='"+pa.getDni()+"'");
			connManager.close();
		}
		catch (Exception e){	
			System.out.println("No se pudo eliminar desde el DAO");
			throw new DAOExcepcion(e);}
	}
	  
	public List <Paciente> listarPacientes() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PACIENTE");						
			connManager.close();
	  	  
			List<Paciente> listaPacientes=new ArrayList<Paciente>();
				
			try{				
				while (rs.next()){
					Paciente pa = buscarPaciente(rs.getString("DNI"));	 
					listaPacientes.add(pa);
				}
				return listaPacientes;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	  
	public Paciente buscarPaciente(String dni)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PACIENTE where DNI= '"+dni+"'");
			connManager.close();
		
			if (rs.next())
				return new Paciente(dni,rs.getString("NOMBRE"),rs.getString("APELLIDOS"), rs.getString("DIRECCION"),rs.getInt("TELEFONO"),rs.getInt("EDAD"),rs.getString("SEXO").charAt(0));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

}
