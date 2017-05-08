import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Control_BD {
	
	//Para gestionar nuestra conexion
	private Connection conexion = null;
	
	//Objetos necesarios para la consulta
	private Statement instruccion = null;
	private ResultSet resultados = null;
	private String sentencia = "";
	
	//Constructor (recoger� la conexi�n con la que se trabajar�)
	public Control_BD(Connection conexion){
		this.conexion=conexion;
	}
	
	//M�todo para mostrar los datos de los jugadores
	public void mostrarDatosJugadores(){
		try{
			sentencia = "SELECT nombre, apellido1, apellido2, edad FROM jugadores ORDER BY edad DESC";
			instruccion = this.conexion.createStatement(); 								//Creamos el statement
			resultados = instruccion.executeQuery(sentencia);							//Guardamos los resultados de ejecutar la sentencia;
			
			//Mostramos por pantalla los datos
			while(resultados.next()){
				System.out.print(resultados.getString("nombre")+" ");
				System.out.print(resultados.getString("apellido1")+" ");
				System.out.print(resultados.getString("apellido2")+", ");
				System.out.println(resultados.getInt("edad")+" a�os.");
			}
		}
		catch(SQLException excepcionSQL){
			excepcionSQL.printStackTrace();
		}
		finally{
			try{																//Cerramos conexi�n, statement, y resultset
				resultados.close();
				instruccion.close();
				conexion.close();
				System.out.println("\nConexi�n cerrada.");
			}
			catch(SQLException excepcionSQL){
				excepcionSQL.printStackTrace();
			}
		}
	}
	
	
}
