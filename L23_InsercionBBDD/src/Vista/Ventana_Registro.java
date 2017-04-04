package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.ConexionBD;
import Modelo.Control_Jugadores;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana_Registro extends JFrame {

	private JPanel contentPane;
	private JTextField cajaNombre;
	private JTextField cajaApellido1;
	private JTextField cajaApellido2;
	private JTextField cajaEdad;
	private JTextField cajaUsuario;
	private JLabel etiquetaNombre;
	private JLabel etiquetaApellido1;
	private JLabel etiquetaApellido2;
	private JLabel etiquetaEdad;
	private JLabel etiquetaUsuario;
	private JButton botonRegistrar;
	
	//Control de la BBDD
	private ConexionBD conexionDB;
	private Control_Jugadores controlDB;
	private Connection conexion;							//Conexión
	private boolean connected=false;						//Semáforo para saber si estamos conectados


	/**
	 * Constructor
	 */
	public Ventana_Registro() {
		
		//Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Componentes
		etiquetaNombre = new JLabel("Nombre");
		etiquetaNombre.setBounds(20, 40, 87, 14);
		contentPane.add(etiquetaNombre);
		
		etiquetaApellido1 = new JLabel("Apellido 1");
		etiquetaApellido1.setBounds(20, 82, 87, 14);
		contentPane.add(etiquetaApellido1);
		
		etiquetaApellido2 = new JLabel("Apellido 2");
		etiquetaApellido2.setBounds(20, 121, 87, 14);
		contentPane.add(etiquetaApellido2);
		
		etiquetaEdad = new JLabel("Edad");
		etiquetaEdad.setBounds(20, 161, 87, 14);
		contentPane.add(etiquetaEdad);
		
		etiquetaUsuario = new JLabel("Nombre de usuario");
		etiquetaUsuario.setBounds(20, 199, 117, 14);
		contentPane.add(etiquetaUsuario);
		
		cajaNombre = new JTextField();
		cajaNombre.setBounds(131, 37, 293, 20);
		contentPane.add(cajaNombre);
		cajaNombre.setColumns(10);
		
		cajaApellido1 = new JTextField();
		cajaApellido1.setColumns(10);
		cajaApellido1.setBounds(131, 79, 293, 20);
		contentPane.add(cajaApellido1);
		
		cajaApellido2 = new JTextField();
		cajaApellido2.setColumns(10);
		cajaApellido2.setBounds(131, 115, 293, 20);
		contentPane.add(cajaApellido2);
		
		cajaEdad = new JTextField();
		cajaEdad.setColumns(10);
		cajaEdad.setBounds(131, 158, 293, 20);
		contentPane.add(cajaEdad);
		
		cajaUsuario = new JTextField();
		cajaUsuario.setColumns(10);
		cajaUsuario.setBounds(131, 196, 293, 20);
		contentPane.add(cajaUsuario);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Nos conectamos
				connected = conexionDB.conectarBD();
				//Asignamos la conexión y la pasamos al nuevo objeto de control de la base de datos
				conexion = conexionDB.getConexion();
				controlDB = new Control_Jugadores(conexion);
				//Insertamos el jugador cogiendo los datos de las cajas
				controlDB.insertarJugador(cajaNombre.getText(), cajaApellido1.getText(), cajaApellido2.getText(), Integer.valueOf(cajaEdad.getText()), cajaUsuario.getText());
				//Cerramos la conexion
				conexionDB.finalizarConexion();
			}
		});
		botonRegistrar.setBounds(168, 251, 146, 23);
		contentPane.add(botonRegistrar);
		
		//Objeto para la conexion
		conexionDB = new ConexionBD("localhost", "jugadores", "root", "asdasd");
		
	}
}
