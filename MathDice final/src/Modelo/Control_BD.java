package Modelo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import Controlador.Encriptacion;

public class Control_BD {
	
	private Encriptacion encript;
	
	//Para gestionar nuestra conexion
	private Connection conexion = null;
	
	//Objetos necesarios para la consulta
	private Statement instruccion = null;
	private PreparedStatement instruccionPreparada = null;
	private ResultSet resultados = null;
	private String sentencia = "";
	
	//Constructor (recoger� la conexi�n con la que se trabajar�)
	public Control_BD(Connection conexion){
		this.conexion=conexion;
		encript = new Encriptacion();
	}

	//M�todo para asignar las propiedades del jugador a su objeto
	public void asignarPropiedades(Jugador jug, String usuario){
		try{
			sentencia = "SELECT * FROM jugadores WHERE usuario='"+usuario+"'";
			instruccion = this.conexion.createStatement();
			resultados = instruccion.executeQuery(sentencia);
			
			//Puesto que s�lo obtendremos un resultado (el valor usuario es unique), utilizaremos esto:
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
				jug.setImagenPerfil(this.leerImagen(usuario));
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
	
	//M�todo para comprobar acceso del usuario
	public int accesoCorrecto(String usuario, String clave){
		int control = 0;
		try{
			instruccion = conexion.createStatement();
			sentencia = "SELECT * FROM jugadores WHERE usuario='"+usuario+"' AND clave='"+clave+"'";
			resultados = instruccion.executeQuery(sentencia);
			if (resultados.next()){												//Si hay datos que recorrer, entonces es que el login ser� correcto (devolveremos 1)
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
				//conexion.close(); 	 										//No la cerramos porque despu�s de esto se asignar�n las propiedades del jugador y se cerrar� ahi
				System.out.println("Cerrada la conexi�n");
			}
			catch(SQLException e){
				System.out.println("Error de desconexi�n");
			}
		}
		return control;
	}
	
	//M�todo para registrar un jugador
	public int insertarJugador(String nombre, String apellido1, String apellido2, int edad, String usuario, String clave){
		
		int r = 0;
		
		try{
			instruccion = conexion.createStatement();
			sentencia = "INSERT INTO jugadores (nombre, apellido1, apellido2, edad, usuario, clave) VALUES ('"+nombre+"', '"+apellido1+"', '"+apellido2+"', "+edad+", '"+usuario+"', '"+clave+"')";
			instruccion.executeUpdate(sentencia);										//Ejecutamos la sentencia
			System.out.println("Usuario " + usuario + " registrado con �xito");
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
				instruccion.close();						//Cerramos el statement, resultset y conexi�n
				conexion.close();
			}
			catch(SQLException e){
			}
		}
		
		return r;
	}
	
	//M�todo para actualizar la puntuaci�n de un jugador
	public void actualizarPuntuacion(Jugador jug, int punt){
		try{
			instruccion = conexion.createStatement();
			sentencia = "UPDATE jugadores SET puntuacion_max=" + punt + " WHERE id_usuario=" + jug.getIdentificador();
			instruccion.executeUpdate(sentencia);
			System.out.println("Nueva puntuaci�n m�xima para " + jug.getNombre());
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
			JOptionPane.showMessageDialog(null, "Error en la actualizaci�n");
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
	
	public void buscarUsuarios(String usuario, JComboBox cb){
		try{
			instruccion = conexion.createStatement();
			sentencia = "SELECT usuario, edad FROM jugadores WHERE usuario LIKE '"+usuario+"%' ORDER BY usuario ASC";		//Devolver� usuarios que empiecen por la cadena introducida
			resultados = instruccion.executeQuery(sentencia);
			//A�adimos antes que nada un valor por defecto al combobox
			cb.addItem("Usuario");
			//Vamos rellenando el combobox
			if(resultados.next()){
				String user = resultados.getString("usuario");
				int edad = resultados.getInt("edad");
				//a�adimos al combobox
				cb.addItem(resultados.getString("usuario"));
				while(resultados.next()){
					//a�adimos al combobox
					cb.addItem(resultados.getString("usuario"));
				}
			}
			else{
				cb.addItem("0 coincidencias");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				instruccion.close();
				resultados.close();
				conexion.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	//M�todo para guardar una imagen de perfil en la bbdd para un determinado usuario
	public boolean guardarImagen(String ruta, String usuario){
		sentencia = "UPDATE jugadores SET imagen_perfil=? WHERE usuario='"+usuario+"'";
		FileInputStream fis = null;
		try{
			//Con esto hacemos que la conexi�n no actualice los cambios que hagamos hasta que se lo digamos
			conexion.setAutoCommit(false);			
			//Creamos objeto file con la ruta de la imagen
			File archivo = new File(ruta);		
			//Esto guardar� los bytes de la imagen
			fis = new FileInputStream(archivo);													
			instruccionPreparada = (PreparedStatement) conexion.prepareStatement(sentencia);			//Preparamos la sentencia
			instruccionPreparada.setBinaryStream(1, fis, (int)archivo.length()); 						//A�adimos los bytes de la imagen al par�metro '?' de la sentencia
			instruccionPreparada.executeUpdate();														//Ejecutamos la sentencia
			//Finalmente le decimos a la conexi�n que actualice los cambios
			conexion.commit();
			return true;
		}
		catch(Exception exc){
			exc.printStackTrace();
			JOptionPane.showMessageDialog(null, "La imagen es demasiado grande");
		}
		finally{
			try{
				instruccionPreparada.close();
				fis.close();
				conexion.setAutoCommit(true); 										//Volvemos a poner que actualice los cambios de manera autom�tica
				conexion.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ImageIcon leerImagen(String usuario){
		ImageIcon imagen = null;
		try{
			sentencia = "SELECT imagen_perfil FROM jugadores WHERE usuario='"+usuario+"'";
			instruccion = conexion.createStatement();
			resultados = instruccion.executeQuery(sentencia);
			while(resultados.next()){
				Blob blob = resultados.getBlob("imagen_perfil");							//Obtenemos los bytes de la imagen
				byte[] data = blob.getBytes(1, (int)blob.length());					//Leemos los bytes de la imagen
				BufferedImage img = null;
				try{
					img = ImageIO.read(new ByteArrayInputStream(data));				//Guardamos los bytes le�dos en el objeto de bufferedImage
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
			imagen = new ImageIcon(img);										//Finalmente guardamos la imagen en el ImageIcon
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				instruccion.close();
				resultados.close();
				conexion.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return imagen;
	}
}
