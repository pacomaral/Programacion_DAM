package Vista;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionBD;
import Modelo.Control_BD;

import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class ClasificacionPuntuaciones extends JPanel {
	
	//Componentes
	private JTable tabla;
	private JScrollPane scrollPane;								//ScrollPane para que nos salga la barra si no cabe en la pantalla
	
	
	//Objetos necesarios para la conexión y control de BD
	private ConexionBD conexionBD;
	private Control_BD controlBD;
	private Connection conexion;
	private boolean connected;
	private ResultSet rs;										//Para poder rellenar la tabla con el resultset

	/**
	 * Constructor
	 */
	public ClasificacionPuntuaciones() {
		setBackground(new Color(153, 153, 102));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setRowHeight(40);;
		tabla.setFillsViewportHeight(true);
		tabla.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		tabla.setBackground(new Color(153, 153, 102));
		scrollPane.setViewportView(tabla);
		
		//Creamos un "modelo" donde iremos colocando el resultado de la consulta SQL obtenida
		DefaultTableModel dfm = new DefaultTableModel();
		tabla.setModel(dfm);
		
		//Nos conectamos y obtenemos el resultado de la consulta
		conectarBD();
		rs = controlBD.devolverClasificacion();
		
		//Rellenamos la tabla
		dfm.setColumnIdentifiers(new Object[]{"PUESTO", "NOMBRE", "APELLIDO", "PUNTUACION"}); 												//Las columnas que tendrá la tabla
		try{
			int i = 1;
			while(rs.next()){
				dfm.addRow(new Object[]{i, rs.getString("nombre"), rs.getString("apellido1"), rs.getInt("puntuacion_max")});			//Añadimos los datos a la columna en cada línea
				i = i + 1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		

	}
	
	/**
	 *  MÉTODOS
	 */
	
	public void setConexion_Clasificacion(ConexionBD conex, Control_BD control){				//Para pasar los objetos desde la ventana de acceso
		this.conexionBD = conex;
		this.controlBD = control;
	}
	
	//Método para establecer conexión con la BBDD
	public void conectarBD(){
		try{
			conexionBD = ConexionBD.getInstance("84.126.92.105:3306", "usuarios", "root", "asdasd");			//Esto creará el objeto y la instancia de ConexionBD
			connected = conexionBD.conectarBD();																//Nos conectamos
			conexion = conexionBD.getConexion();
			if(connected){
				controlBD = new Control_BD(conexion);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error en la conexión a la BBDD.");
		}
	}

}
