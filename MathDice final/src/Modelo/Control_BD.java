package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Control_BD {
	
	//Para gestionar nuestra conexion
	private Connection conexion = null;
	
	//Objetos necesarios para la consulta
	private Statement instruccion = null;
	private ResultSet resultados = null;
	private String sentencia = "";
	
	//Constructor (recogerá la conexión con la que se trabajará)
	public Control_BD(Connection conexion){
		this.conexion=conexion;
	}
	
	//Método para mostrar los datos de los jugadores por consola
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
				System.out.println(resultados.getInt("edad")+" años.");
			}
		}
		catch(SQLException excepcionSQL){
			excepcionSQL.printStackTrace();
		}
		finally{
			try{																//Cerramos conexión, statement, y resultset
				resultados.close();
				instruccion.close();
				conexion.close();
			}
			catch(SQLException excepcionSQL){
				excepcionSQL.printStackTrace();
			}
		}
	}
	
	//Método para asignar las propiedades del jugador a su objeto
	public void asignarPropiedades(Jugador jug, String usuario){
		try{
			sentencia = "SELECT * FROM jugadores WHERE usuario='"+usuario+"'";
			instruccion = this.conexion.createStatement();
			resultados = instruccion.executeQuery(sentencia);
			
			//Puesto que sólo obtendremos un resultado (el valor usuario es unique), utilizaremos esto:
			if(resultados.next()){
				//Recogemos los datos del jugador
				jug.setNombre(resultados.getString("nombre"));
				jug.setApellido1(resultados.getString("apellido1"));
				jug.setApellido2(resultados.getString("apellido2"));
				jug.setEdad(resultados.getInt("edad"));
				jug.setIdentificador(resultados.getInt("id_usuario"));
				jug.setPuntuacionMax(resultados.getInt("puntuacion_max"));
				jug.setUsuario(resultados.getString("usuario"));
				jug.setContrasenya(resultados.getString("clave"));
				System.out.println(jug.infoJugador());										//Imprimimos datos por consola para comprobar que los recoge correctamente
			}
			else{
				System.out.println("Error recogiendo los datos del jugador"); 
			}
		}
		catch(SQLException exc){
			exc.printStackTrace();
		}
		finally{
			try{
				resultados.close();
				instruccion.close();
				conexion.close();
				System.out.println("\nConexion cerrada");
			}
			catch(SQLException exc){
				exc.printStackTrace();
			}
		}
	}
	
	//Método para comprobar acceso del usuario
	public int accesoCorrecto(String usuario, String clave){
		int control = 0;
		try{
			instruccion = conexion.createStatement();
			sentencia = "SELECT * FROM jugadores WHERE usuario='"+usuario+"' AND clave='"+clave+"'";
			resultados = instruccion.executeQuery(sentencia);
			if (resultados.next()){												//Si hay datos que recorrer, entonces es que el login será correcto (devolveremos 1)
				control = 1;
			}
		}
		catch (SQLException ex){
			System.out.println("Se ha producido un error en la consulta");
		}
		catch (Exception e){
			System.out.println("Error");
		}
		finally{
			try{
				instruccion.close(); 											//Cerramos el statement
				resultados.close(); 											//Cerramos el resultset
				//conexion.close(); 	 										//No la cerramos porque después de esto se asignarán las propiedades del jugador y se cerrará ahi
				System.out.println("Cerrada la conexión");
			}
			catch(SQLException e){
				System.out.println("Error de desconexión");
			}
		}
		return control;
	}
	
	//Método para registrar un jugador
	public int insertarJugador(String nombre, String apellido1, String apellido2, int edad, String usuario, String clave){
		
		int r = 0;
		
		try{
			instruccion = conexion.createStatement();
			sentencia = "INSERT INTO jugadores (nombre, apellido1, apellido2, edad, usuario, clave) VALUES ('"+nombre+"', '"+apellido1+"', '"+apellido2+"', "+edad+", '"+usuario+"', '"+clave+"')";
			instruccion.executeUpdate(sentencia);										//Ejecutamos la sentencia
			System.out.println("Usuario " + usuario + " registrado con éxito");
			r = 1;
		}
		catch(SQLException excepcionSQL){
			//Si se produce un error en la consulta
			System.out.println("Se ha producido un error en la consulta");
			excepcionSQL.printStackTrace();
			r = 0;
		}
		catch(Exception e){
			//Cualquier otro error
			System.out.println("El usuario " + usuario + " ya se encuentra registrado.");
			e.printStackTrace();
			r = 0;
		}
		finally{
			try{
				instruccion.close();						//Cerramos el statement, resultset y conexión
				conexion.close();
			}
			catch(SQLException e){
			}
		}
		
		return r;
	}
	
	//Método para actualizar la puntuación de un jugador
	public void actualizarPuntuacion(Jugador jug, int punt){
		try{
			instruccion = conexion.createStatement();
			sentencia = "UPDATE jugadores SET puntuacion_max=" + punt + " WHERE id_usuario=" + jug.getIdentificador();
			instruccion.executeUpdate(sentencia);
			System.out.println("Nueva puntuación máxima para " + jug.getNombre());
		}
		catch(SQLException excepcionSQL){
			excepcionSQL.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la consulta");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				instruccion.close();
				conexion.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void actualizarDatos(Jugador jug){
		try{
			instruccion = conexion.createStatement();
			sentencia = "UPDATE jugadores SET "									//Preparamos la sentencia
					+ "nombre ='" + jug.getNombre() + "', "
					+ "apellido1 ='" + jug.getApellido1() + "', "
					+ "apellido2 ='" + jug.getApellido2() + "', "
					+ "edad =" + jug.getEdad() + ", "
					+ "usuario ='" + jug.getUsuario() + "', "
					+ "clave ='" + jug.getContrasenya() + "' "
					+ "WHERE id_usuario=" + jug.getIdentificador();
			instruccion.executeUpdate(sentencia);								//Ejecutamos la sentencia SQL
			System.out.println("Usuario " + jug.getUsuario() + " actualizado correctamente.");
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la actualización");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				instruccion.close();
				conexion.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet devolverClasificacion(){
		ResultSet rs = null;
		try{
			instruccion = conexion.createStatement();
			sentencia = "SELECT nombre, apellido1, puntuacion_max FROM jugadores ORDER BY puntuacion_max DESC, nombre ASC";
			rs = instruccion.executeQuery(sentencia);
		}
		catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error en la consulta.");
		}
		return rs;
	}
}
