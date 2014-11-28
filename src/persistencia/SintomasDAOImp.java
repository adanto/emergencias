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
			connManager= new ConnectionManager("emergencias");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public void crearSintoma(Sintoma sint) throws DAOExcepcion {
	// TODO Auto-generated method stub			
		try{
			connManager.connect();
			connManager.updateDB("insert into SINTOMA (ESTADO, DESCRIPCION, DURACION, NOMBRE) values ('"+sint.getEstado()+"','"+sint.getDescripcion()+"','"+sint.getDuracion()+"', '"+sint.getNombre()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
	  
	public List <Sintoma> listarSintoma() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA");						
			connManager.close();
	  	  
			List<Sintoma> listaSintoma=new ArrayList<Paciente>();
				
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