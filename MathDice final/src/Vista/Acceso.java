package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Modelo.ConexionBD;
import Modelo.Control_BD;
import Modelo.Jugador;

import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Rectangle;

public class Acceso extends JFrame {

	private JPanel contentPane;
	private VentanaPrincipal vPrincipal;
	private Jugador jug1;
	private Acceso acceso;
	
	//Objetos necesarios para conexión/consulta/manipulación de BBDD
	private ConexionBD conexionBD;
	private boolean connected = false;
	private Connection conexion;
	private Control_BD controlBD;
	
	//Paneles que añadiremos al CardLayout
	private JPanel panelLogin = new JPanel();
	private JPanel panelRegistro = new JPanel();
	
	//Constantes necesarias
	private final static String LOGIN="Ventana para validarse en la BBDD";		
	private final static String REGISTRO="Ventana para registrar un nuevo jugador";
	
	//Componentes para el panel de Login
	private JLabel etiquetaUsuario, etiquetaPassword;
	private JTextField cajaUsuario;
	private JPasswordField cajaPassword;
	private JButton botonAcceder, botonRegistro;
	private JComboBox comboBox;
	private JTextField cajaComboBox;
	private JButton botonBuscar;
	private int itemsComboBox;
	private JLabel etiquetaSeleccionUsuario;
	
	//Componentes para el panel de registro
	private JLabel etiquetaNombre, etiquetaApellido1, etiquetaApellido2, etiquetaEdad, etiquetaUser, etiquetaPass;
	private JTextField cajaNombre, cajaApellido1, cajaApellido2, cajaEdad, cajaUser;
	private JPasswordField cajaPass;
	private JButton botonRegistrar;
	
	private String usuario, contrasenya;


	/**
	 * Constructor
	 */
	public Acceso(VentanaPrincipal vp) {
		

		
		//Para controlar el objeto ventanaprincipal que introduciremos como parámetro
		vPrincipal = vp;
			
		//---------------------------------------------------------------------------------
		//Configuración del JFrame
		//---------------------------------------------------------------------------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));											//Le asignamos un CardLayout
		
		//Añadimos los paneles al CardLayout (se mostrará primero el primero que se añada)
		contentPane.add(panelLogin, LOGIN);
		contentPane.add(panelRegistro, REGISTRO);
		
		
		//---------------------------------------------------------------------------------
		//Configuración del panel de Login
		//---------------------------------------------------------------------------------
		
		//Cambiamos color de fondo
		panelLogin.setBackground(new Color(153, 153, 102));
		
		//Ponemos como layout del panel el GridBagLayout
		GridBagLayout gridBagLayout_login = new GridBagLayout();
		gridBagLayout_login.columnWidths = new int[]{0};
		gridBagLayout_login.rowHeights = new int[]{0};
		gridBagLayout_login.columnWeights = new double[]{1.0};
		gridBagLayout_login.rowWeights = new double[]{0.0};
		panelLogin.setLayout(gridBagLayout_login);
		
		
		
		//Configuramos los componentes
		etiquetaSeleccionUsuario = new JLabel();
		etiquetaSeleccionUsuario.setFont(new Font("Ubuntu", Font.BOLD, 14));
		etiquetaSeleccionUsuario.setText("Selecciona tu usuario");													//Le ponemos un nombre por si lo necesitamos posteriormente
		GridBagConstraints gbc_etiquetaSeleccionUsuario=new GridBagConstraints();
		gbc_etiquetaSeleccionUsuario.weightx = 0.5;
		gbc_etiquetaSeleccionUsuario.insets = new Insets(30, 0, 0, 0);
		gbc_etiquetaSeleccionUsuario.gridx=0;															//Comenzará desde la columna 0 y fila 0
		gbc_etiquetaSeleccionUsuario.gridy=0;
		gbc_etiquetaSeleccionUsuario.gridwidth=3;  														//Ocupará 1 fila y 1 columna
		gbc_etiquetaSeleccionUsuario.gridheight=1;
		panelLogin.add(etiquetaSeleccionUsuario, gbc_etiquetaSeleccionUsuario);

		etiquetaUsuario = new JLabel();
		etiquetaUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		etiquetaUsuario.setText("Usuario");
		etiquetaUsuario.setName("Usuario"); 													//Le ponemos un nombre por si lo necesitamos posteriormente
		GridBagConstraints gbc_etiquetaUsuario=new GridBagConstraints();
		gbc_etiquetaUsuario.weightx = 0.5;
		gbc_etiquetaUsuario.insets = new Insets(0, 20, 0, 0);
		gbc_etiquetaUsuario.gridx=0;															//Comenzará desde la columna 0 y fila 0
		gbc_etiquetaUsuario.gridy=2;
		gbc_etiquetaUsuario.gridwidth=1;  														//Ocupará 1 fila y 1 columna
		gbc_etiquetaUsuario.gridheight=1;
		panelLogin.add(etiquetaUsuario, gbc_etiquetaUsuario);
		
		etiquetaPassword = new JLabel();
		etiquetaPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		etiquetaPassword.setText("Contraseña");
		etiquetaUsuario.setName("Contraseña"); 													
		GridBagConstraints gbc_etiquetaPassword=new GridBagConstraints();
		gbc_etiquetaPassword.insets = new Insets(0, 20, 0, 0);
		gbc_etiquetaPassword.gridx=0;															
		gbc_etiquetaPassword.gridy=3;
		gbc_etiquetaPassword.gridwidth=1;  														
		gbc_etiquetaPassword.gridheight=1;
		panelLogin.add(etiquetaPassword, gbc_etiquetaPassword);
		
		cajaUsuario = new JTextField();
		cajaUsuario.setEditable(false);
		cajaUsuario.setName("cajaUsuario"); 													
		GridBagConstraints gbc_cajaUsuario=new GridBagConstraints();
		gbc_cajaUsuario.weighty = 0.2;
		gbc_cajaUsuario.insets = new Insets(0, 0, 0, 50);
		gbc_cajaUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaUsuario.gridx=1;															
		gbc_cajaUsuario.gridy=2;
		gbc_cajaUsuario.gridwidth=2;  														
		gbc_cajaUsuario.gridheight=1;
		panelLogin.add(cajaUsuario, gbc_cajaUsuario);
		
		cajaPassword = new JPasswordField();
		cajaPassword.setName("cajaUsuario"); 													
		GridBagConstraints gbc_cajaPassword=new GridBagConstraints();
		gbc_cajaPassword.weighty = 0.2;
		gbc_cajaPassword.insets = new Insets(0, 0, 0, 50);
		gbc_cajaPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaPassword.gridx=1;															
		gbc_cajaPassword.gridy=3;
		gbc_cajaPassword.gridwidth=2;  														
		gbc_cajaPassword.gridheight=1;
		panelLogin.add(cajaPassword, gbc_cajaPassword);
		
		cajaComboBox = new JPasswordField();
		cajaComboBox.setName("cajaUsuario"); 													
		GridBagConstraints gbc_cajaComboBox=new GridBagConstraints();
		gbc_cajaComboBox.weighty = 0.2;
		gbc_cajaComboBox.insets = new Insets(20, 10, 0, 10);
		gbc_cajaComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaComboBox.gridx=1;															
		gbc_cajaComboBox.gridy=1;
		gbc_cajaComboBox.gridwidth=1;  														
		gbc_cajaComboBox.gridheight=1;
		panelLogin.add(cajaComboBox, gbc_cajaComboBox);
		
		botonAcceder = new JButton();
		botonAcceder.setText("Acceder"); 													
		GridBagConstraints gbc_botonAcceder=new GridBagConstraints();
		gbc_botonAcceder.insets = new Insets(0, 30, 20, 0);
		gbc_botonAcceder.gridx=0;															
		gbc_botonAcceder.gridy=4;
		gbc_botonAcceder.gridwidth=1;  														
		gbc_botonAcceder.gridheight=1;
		panelLogin.add(botonAcceder, gbc_botonAcceder);
		botonAcceder.addActionListener(new listenerBotonAcceder());
		
		botonRegistro = new JButton();
		botonRegistro.setText("Nuevo usuario"); 													
		GridBagConstraints gbc_botonRegistro=new GridBagConstraints();
		gbc_botonRegistro.weighty = 0.1;
		gbc_botonRegistro.insets = new Insets(0, 0, 20, 50);
		gbc_botonRegistro.gridx=1;															
		gbc_botonRegistro.gridy=4;
		gbc_botonRegistro.gridwidth=1;  														
		gbc_botonRegistro.gridheight=1;
		panelLogin.add(botonRegistro, gbc_botonRegistro);
		botonRegistro.addActionListener(new ListenerBotonRegistro());
		
		botonBuscar = new JButton();
		botonBuscar.setText("Buscar");
		GridBagConstraints gbc_botonBuscar = new GridBagConstraints();
		gbc_botonBuscar.insets = new Insets(20, 0, 0, 50);
		gbc_botonBuscar.gridx=2;
		gbc_botonBuscar.gridy=1;
		gbc_botonBuscar.gridwidth=1;
		gbc_botonBuscar.gridheight=1;
		panelLogin.add(botonBuscar, gbc_botonBuscar);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(20, 20, 0, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx=0;
		gbc_comboBox.gridy=1;
		gbc_comboBox.gridwidth=1;
		gbc_comboBox.gridheight=1;
		panelLogin.add(comboBox, gbc_comboBox);
		
		//---------------------------------------------------------------------------------
		//Configuración del panel de Registro
		//---------------------------------------------------------------------------------
		
		
		//Cambiamos color de fondo
		panelRegistro.setBackground(new Color(153, 153, 102));
		
		//Ponemos como layout del panel el GridBagLayout
		GridBagLayout gridBagLayout_registro = new GridBagLayout();
		gridBagLayout_registro.columnWidths = new int[]{0};
		gridBagLayout_registro.rowHeights = new int[]{0};
		gridBagLayout_registro.columnWeights = new double[]{1.0};
		gridBagLayout_registro.rowWeights = new double[]{0.0};
		panelRegistro.setLayout(gridBagLayout_registro);
		
		//Creamos los objetos
		etiquetaNombre = new JLabel();
		etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaNombre.setText("Nombre");
		GridBagConstraints gbc_etiquetaNombre = new GridBagConstraints();
		gbc_etiquetaNombre.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaNombre.anchor = GridBagConstraints.WEST;
		gbc_etiquetaNombre.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaNombre.gridy=0;
		panelRegistro.add(etiquetaNombre, gbc_etiquetaNombre);
				

		etiquetaApellido1 = new JLabel();
		etiquetaApellido1.setText("Primer apellido");
		GridBagConstraints gbc_etiquetaApellido1 = new GridBagConstraints();
		gbc_etiquetaApellido1.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaApellido1.anchor = GridBagConstraints.WEST;
		gbc_etiquetaApellido1.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaApellido1.gridy=2;
		panelRegistro.add(etiquetaApellido1, gbc_etiquetaApellido1);

		etiquetaApellido2 = new JLabel();
		etiquetaApellido2.setText("Segundo apellido");
		GridBagConstraints gbc_etiquetaApellido2 = new GridBagConstraints();
		gbc_etiquetaApellido2.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaApellido2.anchor = GridBagConstraints.WEST;
		gbc_etiquetaApellido2.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaApellido2.gridy=4;
		panelRegistro.add(etiquetaApellido2, gbc_etiquetaApellido2);
				

		etiquetaEdad = new JLabel();
		etiquetaEdad.setText("Edad");
		GridBagConstraints gbc_etiquetaEdad = new GridBagConstraints();
		gbc_etiquetaEdad.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaEdad.anchor = GridBagConstraints.WEST;
		gbc_etiquetaEdad.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaEdad.gridy=6;
		panelRegistro.add(etiquetaEdad, gbc_etiquetaEdad);
				
		etiquetaUser = new JLabel();
		etiquetaUser.setText("Usuario");
		GridBagConstraints gbc_etiquetaUser = new GridBagConstraints();
		gbc_etiquetaUser.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaUser.anchor = GridBagConstraints.WEST;
		gbc_etiquetaUser.gridx=0;
		gbc_etiquetaUser.gridy=8;
		panelRegistro.add(etiquetaUser, gbc_etiquetaUser);
		
		etiquetaPass = new JLabel();
		etiquetaPass.setText("Clave de acceso");
		GridBagConstraints gbc_etiquetaPass = new GridBagConstraints();
		gbc_etiquetaPass.insets = new Insets(0, 70, 0, 0);
		gbc_etiquetaPass.anchor = GridBagConstraints.WEST;
		gbc_etiquetaPass.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_etiquetaPass.gridy=10;
		panelRegistro.add(etiquetaPass, gbc_etiquetaPass);

		cajaNombre = new JTextField();
		cajaNombre.setName("Nombre");
		GridBagConstraints gbc_cajaNombre = new GridBagConstraints();
		gbc_cajaNombre.insets = new Insets(3, 70, 3, 70);
		gbc_cajaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaNombre.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaNombre.gridy=1;
		panelRegistro.add(cajaNombre, gbc_cajaNombre);

		cajaApellido1 = new JTextField();
		cajaApellido1.setName("Apellido 1");
		GridBagConstraints gbc_cajaApellido1 = new GridBagConstraints();
		gbc_cajaApellido1.insets = new Insets(3, 70, 3, 70);
		gbc_cajaApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaApellido1.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaApellido1.gridy=3;
		panelRegistro.add(cajaApellido1, gbc_cajaApellido1);

		cajaApellido2 = new JTextField();
		cajaApellido2.setName("Apellido 2");
		GridBagConstraints gbc_cajaApellido2 = new GridBagConstraints();
		gbc_cajaApellido2.insets = new Insets(3, 70, 3, 70);
		gbc_cajaApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaApellido2.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaApellido2.gridy=5;
		panelRegistro.add(cajaApellido2, gbc_cajaApellido2);

		cajaEdad = new JTextField();
		cajaEdad.setName("Edad");
		GridBagConstraints gbc_cajaEdad = new GridBagConstraints();
		gbc_cajaEdad.insets = new Insets(3, 70, 3, 70);
		gbc_cajaEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaEdad.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaEdad.gridy=7;
		panelRegistro.add(cajaEdad, gbc_cajaEdad);
				
		cajaUser = new JTextField();
		cajaUser.setName("Usuario");
		GridBagConstraints gbc_cajaUser = new GridBagConstraints();
		gbc_cajaUser.insets = new Insets(3, 70, 3, 70);
		gbc_cajaUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaUser.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaUser.gridy=9;
		panelRegistro.add(cajaUser, gbc_cajaUser);
		
		
		cajaPass = new JPasswordField();
		cajaPass.setName("Password");
		GridBagConstraints gbc_cajaPass = new GridBagConstraints();
		gbc_cajaPass.insets = new Insets(3, 70, 3, 70);
		gbc_cajaPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaPass.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaPass.gridy=11;
		panelRegistro.add(cajaPass, gbc_cajaPass);
		
				
		botonRegistrar = new JButton();
		botonRegistrar.setBackground(new Color(204, 255, 255));
		botonRegistrar.setName("Registrar");
		botonRegistrar.setText("Registrar");
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.insets = new Insets(10, 0, 0, 0);
		gbc_botonRegistrar.gridx=0;														//Comenzará desde fila 0 y columna 0
		gbc_botonRegistrar.gridy=12;
		panelRegistro.add(botonRegistrar, gbc_botonRegistrar);
		
		//Añadimos los listeners a las cajas (para realizar comprobaciones y cambiar automáticamente de caja al presionar enter)
		botonRegistrar.addActionListener(new ListenerBotonRegistrarUsuario());
		
	}
	
	/**
	 * METODOS
	 */
	

	
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
	
	//Método para comprobar que no haya ningún valor vacío
	public boolean datosCorrectos(){
		if(cajaNombre.getText().isEmpty() || cajaApellido1.getText().isEmpty() || cajaApellido2.getText().isEmpty() || cajaEdad.getText().isEmpty() || cajaUser.getText().isEmpty() || String.valueOf(cajaPass.getPassword()).isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}
	
	//Método para comprobar que los datos no sean espacios en blanco
	public boolean sonEspacios(String t){
		for(int i =0; i<t.length(); i++)
		if(t.charAt(i) != ' ')							//Recorre todos los valores del string introducido y comprueba si son espacios
		return false;									//Si no son espacios devuelve false
			 
		return true;									//Si son espacios devuelve true
	}
	
	//Método para comprobar que los datos introducidos sean válidos (Número en edad, sin espacios en blanco...)
	public boolean datosValidos(){
		if(sonEspacios(cajaNombre.getText()) || sonEspacios(cajaApellido1.getText()) || sonEspacios(cajaApellido2.getText()) || sonEspacios(cajaEdad.getText()) || sonEspacios(cajaUser.getText()) || sonEspacios(String.valueOf(cajaPass.getPassword()))){
			return false;
		}
		else{
			if(cajaEdad.getText().matches("\\d*")){ 			//Si la caja edad es un numero
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public void limpiarCajas(){
		cajaUsuario.setText("");
		cajaPassword.setText("");
	}
	
	public void limpiarDatosRegistro(){
		cajaNombre.setText("");
		cajaApellido1.setText("");
		cajaApellido2.setText("");
		cajaEdad.setText("");
		cajaUser.setText("");
		cajaPass.setText("");
	}
	
	
	/**
	 * INNER CLASSES
	 */
	class listenerBotonAcceder implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			conectarBD();																						//Creamos la conexión a la BBDD
			if(connected){																						
				//Si estamos conectados
				usuario = cajaUsuario.getText();
				contrasenya = String.valueOf(cajaPassword.getPassword());;
				if(controlBD.accesoCorrecto(usuario, contrasenya) == 1){										//Nos conectamos para comprobar pero no cerramos conexión
					jug1 = new Jugador();																		//Creamos un jugador para recoger sus datos de la bbdd
					controlBD.asignarPropiedades(jug1, usuario);												//Cerramos la conexión en este momento
					JOptionPane.showMessageDialog(null, "Logueado con éxito");
					vPrincipal.setJugador_ventanaPrincipal(jug1);												//Pasamos los objetos a la vPrincipal
					vPrincipal.setConexion_ventanaPrincipal(conexionBD, controlBD);
					vPrincipal.setVisible(true);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuario/contraseña incorrectos. Vuelve a intentarlo o regístrate.");
					cajaUsuario.setText("");
					cajaPassword.setText("");
				}
			}
		}
	}
	
	class ListenerBotonRegistro implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//MOSTRAR el JPanel de registro
			CardLayout c1 = (CardLayout)(contentPane.getLayout());
			c1.show(contentPane, REGISTRO);
		}
	}
	
	class ListenerBotonRegistrarUsuario implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//Hacemos la inserción en la BBDD del usuario, si estan todos los datos correctos (cajas desactivadas)
			//Y mostramos de nuevo la ventana de acceso para que el nuevo usuario se loguee
			if(datosCorrectos()){
				if(datosValidos()){
					conectarBD();
					if(controlBD.insertarJugador(cajaNombre.getText(), cajaApellido1.getText(), cajaApellido2.getText(), Integer.valueOf(cajaEdad.getText()), cajaUser.getText(), String.valueOf(cajaPass.getPassword())) == 1){
						//Si se consigue crear el jugador correctamente
						JOptionPane.showMessageDialog(null, "Registrado correctamente. Ahora puedes acceder con tu usuario y contraseña.");
						//Mostrar panel de acceso de nuevo
						CardLayout c2 = (CardLayout)(contentPane.getLayout());
						c2.show(contentPane, LOGIN);
					}
					else{
						//Si no se ha podido crear el jugador
						JOptionPane.showMessageDialog(null, "Registro incorrecto. El nombre de usuario ya está en uso. Elige otro");
						cajaUser.setEnabled(true);
						cajaUser.setText("");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Error. La edad debe ser un número");
				}
			}
			else{
				//Por ejemplo, si algun valor de las cajas de texto esta en blanco
				JOptionPane.showMessageDialog(null, "Error. Debes introducir valores en todos los campos y presionar enter.");
			}
		}
	}
	
}
