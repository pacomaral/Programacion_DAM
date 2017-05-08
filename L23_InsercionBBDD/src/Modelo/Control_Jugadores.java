package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Control_Jugadores {
	
	private Connection conexion;
	private Statement orden=null;					//Objeto necesario para al ejecución de órdenes sql
	private String sql;								//String para almacenar la orden sql
	
	//Constructor (Pasamos por parámetro una conexión, si no, no se podrá trabajar)
	public Control_Jugadores(Connection conex){
		this.conexion=conex;
	}
	
	//Método para insertar un nuevo jugador en la base de datos (sin comprobar si estaba previamente)
	public void insertarJugador(String nombre, String apellido1, String apellido2, int edad, String usuario, String clave){
		try{
			orden = conexion.createStatement();
			sql = "INSERT INTO jugadores (nombre, apellido1, apellido2, edad, usuario, clave) VALUES ('"+nombre+"', '"+apellido1+"', '"+apellido2+"', "+edad+", '"+usuario+"', '"+clave+"')";
			orden.executeUpdate(sql);
			System.out.println("Usuario " + usuario + " registrado con éxito");
		}
		catch(SQLException excepcionSQL){
			//Si se produce un error en la consulta
			System.out.println("Se ha producido un error en la consulta");
			excepcionSQL.printStackTrace();
		}
		catch(Exception e){
			//Cualquier otro error
			System.out.println("El usuario " + usuario + " ya se encuentra registrado.");
			e.printStackTrace();
		}
		finally{
			try{
				orden.close();						//Cerramos el statement
			}
			catch(SQLException e){
			}
		}
	}
}
