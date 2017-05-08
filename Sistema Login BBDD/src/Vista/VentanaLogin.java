package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.ConexionBD;
import Modelo.Control_Jugadores;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField cajaUsuario;
	private JPasswordField cajaContrasenya;
	private JButton botonAcceso;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaContrasenya;
	
	private String usuario;
	private String clave;
	
	//Control de la BBDD
	private ConexionBD conexionDB;
	private Control_Jugadores controlDB;
	private Connection conexion;							//Conexión
	private boolean connected=false;						//Semáforo para saber si estamos conectados

	/**
	 * Constructor
	 */
	public VentanaLogin() {
		//Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Componentes
		cajaUsuario = new JTextField();
		cajaUsuario.setBounds(152, 69, 161, 20);
		contentPane.add(cajaUsuario);
		cajaUsuario.setColumns(10);
		
		botonAcceso = new JButton("Acceder");
		botonAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Acción a realizar al presionar el botón
				usuario = cajaUsuario.getText();
				clave = String.valueOf(cajaContrasenya.getPassword());
				conectarBD();																								//Nos conectamos				
				if(controlDB.accesoCorrecto(usuario, clave) == 1){
					JOptionPane.showMessageDialog(null, "Enhorabuena, has accedido con éxito");								//Comprobamos que el login sea correcto
				}																												
				else{
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Vuelve a intentarlo");
				}
			}
		});
		botonAcceso.setBounds(152, 204, 161, 23);
		contentPane.add(botonAcceso);
		
		cajaContrasenya = new JPasswordField();
		cajaContrasenya.setBounds(152, 130, 161, 20);
		contentPane.add(cajaContrasenya);
		
		etiquetaUsuario = new JLabel("Usuario");
		etiquetaUsuario.setBounds(39, 72, 78, 14);
		contentPane.add(etiquetaUsuario);
		
		etiquetaContrasenya = new JLabel("Contrase\u00F1a");
		etiquetaContrasenya.setBounds(39, 133, 78, 14);
		contentPane.add(etiquetaContrasenya);
	}
	
	public void conectarBD(){
		//Objeto para la conexion
		conexionDB = new ConexionBD("84.126.92.105:3306", "usuarios", "root", "asdasd");
		//Nos conectamos 
		connected = conexionDB.conectarBD();
		conexion = conexionDB.getConexion();
		//Pasamos la conexión al objeto de control de la BBDD
		controlDB = new Control_Jugadores(conexion);
	}
}
