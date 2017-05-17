package Vista;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controlador.Encriptacion;
import Modelo.ConexionBD;
import Modelo.Control_BD;
import Modelo.Jugador;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Font;
import java.awt.Color;

public class Perfil extends JPanel {
	
	private Jugador jug1;
	private Encriptacion encript = new Encriptacion();
	
	//Paneles a incluir en el GridBagLayout
	private JPanel panelDatos = new JPanel();
	private JPanel panelImagen = new JPanel();
	private Perfil referencia;
	
	//Objetos necesarios para la conexión y control de BD
	private ConexionBD conexionBD;
	private Control_BD controlBD;
	private Connection conexion;
	private boolean connected;
	
	//Componentes panel para los datos
	private JLabel etiquetaNombre;
	private JLabel etiquetaApellido1;
	private JLabel etiquetaApellido2;
	private JLabel etiquetaEdad;
	private JLabel etiquetaApodo;
	private JLabel etiquetaPass;
	private JTextField cajaNombre;
	private JTextField cajaApellido1;
	private JTextField cajaApellido2;
	private JTextField cajaEdad;
	private JTextField cajaApodo;
	private JPasswordField cajaPass;
	private JButton botonActualizar;
	
	//Componentes panel para la imagen de perfil
	private JLabel etiquetaImagenPerfil;
	private JButton elegirImagen;
	
	private JTextField cajaInfo;
	private JLabel etiquetaPuntuacion;
	
	//Objetos necesarios para elegir una imagen para el perfil
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen", "png");							//Solo valdrán imágenes con extensión .png
	private JFileChooser fileChooser = new JFileChooser();
	private String ruta, nombre;
	private ImageIcon imagen, nuevaImagen;
	private ImageIcon avatar = new ImageIcon(getClass().getResource("/Imagenes/avatar.png"));
	
	
	/**
	 * Constructor
	 */
	public Perfil() {
		setBackground(new Color(153, 153, 102));
	
		referencia = this;
		
		//---------------------------------------------------------------------------
		//Configurando panel principal con GRIDBAGLAYOUT
		//---------------------------------------------------------------------------
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.insets = new Insets(0, 0, 0, 15);
		gbc_panelDatos.weightx = 0.65;
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.gridx=1;															//Comenzará desde fila 0 y columna 0
		gbc_panelDatos.gridy=0;
		gbc_panelDatos.gridwidth=1;														//Ocupará 2 filas y 2 columnas
		gbc_panelDatos.gridheight=1;
		panelDatos.setBackground(new Color(153, 153, 102));
		add(panelDatos, gbc_panelDatos);
		
		GridBagConstraints gbc_panelImagen = new GridBagConstraints();
		gbc_panelImagen.insets = new Insets(0, 0, 20, 0);
		gbc_panelImagen.weightx = 0.35;
		gbc_panelImagen.fill = GridBagConstraints.BOTH;
		gbc_panelImagen.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_panelImagen.gridy=0;
		gbc_panelImagen.gridwidth=1;														//Ocupará 2 filas y 2 columnas
		gbc_panelImagen.gridheight=1;
		panelImagen.setBackground(new Color(153, 153, 102));
		add(panelImagen, gbc_panelImagen);
		
		cajaInfo = new JTextField();
		cajaInfo.setBackground(new Color(153, 153, 102));
		cajaInfo.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		cajaInfo.setHorizontalAlignment(SwingConstants.CENTER);
		cajaInfo.setEditable(false);
		GridBagConstraints gbc_cajaInfo = new GridBagConstraints();
		gbc_cajaInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaInfo.gridwidth = 1;
		gbc_cajaInfo.gridx=1;
		gbc_cajaInfo.gridy=1;
		add(cajaInfo, gbc_cajaInfo);
		
		etiquetaPuntuacion = new JLabel();
		etiquetaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_etiquetaPuntuacion = new GridBagConstraints();
		gbc_etiquetaPuntuacion.gridwidth = 1;
		gbc_etiquetaPuntuacion.gridx=0;
		gbc_etiquetaPuntuacion.gridy=1;
		add(etiquetaPuntuacion, gbc_etiquetaPuntuacion);
		
		
		
		
		//---------------------------------------------------------------------------
		//Configurando panel de los datos del jugador
		//---------------------------------------------------------------------------
		
		GridBagLayout gridBagLayout3 = new GridBagLayout();
		gridBagLayout3.columnWidths = new int[]{0};
		gridBagLayout3.rowHeights = new int[]{0};
		gridBagLayout3.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout3.rowWeights = new double[]{Double.MIN_VALUE};
		panelDatos.setLayout(gridBagLayout3);
		

		//Creamos los objetos
		etiquetaNombre = new JLabel();
		etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaNombre.setText("Nombre");
		GridBagConstraints gbc_etiquetaNombre = new GridBagConstraints();
		gbc_etiquetaNombre.weighty = 0.09;
		gbc_etiquetaNombre.anchor = GridBagConstraints.WEST;
		gbc_etiquetaNombre.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaNombre.gridy=0;
		panelDatos.add(etiquetaNombre, gbc_etiquetaNombre);
		

		etiquetaApellido1 = new JLabel();
		etiquetaApellido1.setText("Primer apellido");
		GridBagConstraints gbc_etiquetaApellido1 = new GridBagConstraints();
		gbc_etiquetaApellido1.weighty = 0.09;
		gbc_etiquetaApellido1.anchor = GridBagConstraints.NORTHWEST;
		gbc_etiquetaApellido1.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaApellido1.gridy=2;
		panelDatos.add(etiquetaApellido1, gbc_etiquetaApellido1);

		etiquetaApellido2 = new JLabel();
		etiquetaApellido2.setText("Segundo apellido");
		GridBagConstraints gbc_etiquetaApellido2 = new GridBagConstraints();
		gbc_etiquetaApellido2.weighty = 0.09;
		gbc_etiquetaApellido2.anchor = GridBagConstraints.WEST;
		gbc_etiquetaApellido2.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaApellido2.gridy=4;
		panelDatos.add(etiquetaApellido2, gbc_etiquetaApellido2);
		

		etiquetaEdad = new JLabel();
		etiquetaEdad.setText("Edad");
		GridBagConstraints gbc_etiquetaEdad = new GridBagConstraints();
		gbc_etiquetaEdad.weighty = 0.09;
		gbc_etiquetaEdad.anchor = GridBagConstraints.WEST;
		gbc_etiquetaEdad.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaEdad.gridy=6;
		panelDatos.add(etiquetaEdad, gbc_etiquetaEdad);
		
		etiquetaApodo = new JLabel();
		etiquetaApodo.setText("Usuario");
		GridBagConstraints gbc_etiquetaApodo = new GridBagConstraints();
		gbc_etiquetaApodo.weighty = 0.09;
		gbc_etiquetaApodo.anchor = GridBagConstraints.WEST;
		gbc_etiquetaApodo.gridx=0;
		gbc_etiquetaApodo.gridy=8;
		panelDatos.add(etiquetaApodo, gbc_etiquetaApodo);
		
		etiquetaPass = new JLabel();
		etiquetaPass.setText("Clave de acceso");
		GridBagConstraints gbc_etiquetaPass = new GridBagConstraints();
		gbc_etiquetaPass.insets = new Insets(0, 0, 2, 0);
		gbc_etiquetaPass.anchor = GridBagConstraints.WEST;
		gbc_etiquetaPass.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_etiquetaPass.gridy=10;
		panelDatos.add(etiquetaPass, gbc_etiquetaPass);

		cajaNombre = new JTextField();
		cajaNombre.setName("Nombre");
		GridBagConstraints gbc_cajaNombre = new GridBagConstraints();
		gbc_cajaNombre.weighty = 0.09;
		gbc_cajaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaNombre.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaNombre.gridy=1;
		panelDatos.add(cajaNombre, gbc_cajaNombre);

		cajaApellido1 = new JTextField();
		cajaApellido1.setName("Apellido 1");
		GridBagConstraints gbc_cajaApellido1 = new GridBagConstraints();
		gbc_cajaApellido1.weighty = 0.09;
		gbc_cajaApellido1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaApellido1.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaApellido1.gridy=3;
		panelDatos.add(cajaApellido1, gbc_cajaApellido1);

		cajaApellido2 = new JTextField();
		cajaApellido2.setName("Apellido 2");
		GridBagConstraints gbc_cajaApellido2 = new GridBagConstraints();
		gbc_cajaApellido2.weighty = 0.09;
		gbc_cajaApellido2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaApellido2.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_cajaApellido2.gridy=5;
		panelDatos.add(cajaApellido2, gbc_cajaApellido2);

		cajaEdad = new JTextField();
		cajaEdad.setName("Edad");
		GridBagConstraints gbc_cajaEdad = new GridBagConstraints();
		gbc_cajaEdad.weighty = 0.09;
		gbc_cajaEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaEdad.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaEdad.gridy=7;
		panelDatos.add(cajaEdad, gbc_cajaEdad);
		
		cajaApodo = new JTextField();
		cajaApodo.setName("Apodo");
		GridBagConstraints gbc_cajaApodo = new GridBagConstraints();
		gbc_cajaApodo.weighty = 0.09;
		gbc_cajaApodo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaApodo.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaApodo.gridy=9;
		panelDatos.add(cajaApodo, gbc_cajaApodo);
		
		cajaPass = new JPasswordField();
		cajaPass.setName("Password");
		GridBagConstraints gbc_cajaPass = new GridBagConstraints();
		gbc_cajaPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaPass.gridx=0;																//Comenzará desde fila 0 y columna 0
		gbc_cajaPass.gridy=11;
		panelDatos.add(cajaPass, gbc_cajaPass);
		
		botonActualizar = new JButton();
		botonActualizar.setBackground(new Color(204, 255, 255));
		botonActualizar.setName("Actualizar");
		botonActualizar.setText("Actualizar");
		botonActualizar.setEnabled(true);
		GridBagConstraints gbc_botonActualizar = new GridBagConstraints();
		gbc_botonActualizar.insets = new Insets(10, 0, 0, 0);
		gbc_botonActualizar.gridx=0;														//Comenzará desde fila 0 y columna 0
		gbc_botonActualizar.gridy=12;
		panelDatos.add(botonActualizar, gbc_botonActualizar);
		
		botonActualizar.addActionListener(new ListenerBotonActualizar());
	
		
		//---------------------------------------------------------------------------
		//Configurando panel de la imagen de perfil
		//---------------------------------------------------------------------------
		GridBagLayout gridBagLayout2 = new GridBagLayout();
		gridBagLayout2.columnWidths = new int[]{0};
		gridBagLayout2.rowHeights = new int[]{0};
		gridBagLayout2.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout2.rowWeights = new double[]{Double.MIN_VALUE};
		panelImagen.setLayout(gridBagLayout2);
		
		etiquetaImagenPerfil = new JLabel();
		GridBagConstraints gbc_etiquetaImagen = new GridBagConstraints();
		gbc_etiquetaImagen.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_etiquetaImagen.gridy=0;
		panelImagen.add(etiquetaImagenPerfil, gbc_etiquetaImagen);
		
		elegirImagen = new JButton();
		elegirImagen.setBackground(new Color(204, 255, 255));
		elegirImagen.setName("Elegir");
		elegirImagen.setText("Cambiar Imagen");
		elegirImagen.addActionListener(new ListenerBotonElegirImagen());
		GridBagConstraints gbc_elegirImagen = new GridBagConstraints();
		gbc_elegirImagen.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_elegirImagen.gridy=1;
		panelImagen.add(elegirImagen, gbc_elegirImagen);
	}
	
	/**
	 * Métodos
	 */
	
	//Método para importar el jugador desde otra ventana
	public void setJugador_EditarPerfil(Jugador jug1){
		this.jug1=jug1;
	}
	
	public void setConexion_EditarPerfil(ConexionBD conex, Control_BD control){				//Para pasar los objetos desde la ventana de acceso
		this.conexionBD = conex;
		this.controlBD = control;
	}
	
	
	//Método para poder elegir una imagen de nuestro sistema y ponerla en un JLabel
	public void elegirImagen_paraJLabel(JLabel etiqueta){
		fileChooser.setFileFilter(filter);												//Ponemos el filtro con el objeto creado previamente
		int opcion = fileChooser.showOpenDialog(referencia);							//Abrimos la ventana para buscar las imágenes
		if(opcion==JFileChooser.APPROVE_OPTION){										//Si hemos elegido nuestra imagen:
			String ruta = fileChooser.getSelectedFile().getPath();						//Obtenemos la ruta de la imagen
			//Guardamos en la BBDD
			conectarBD();
			controlBD.guardarImagen(ruta, jug1.getUsuario());
			
			//Mostramos la imagen
			conectarBD();
			this.mostrarImagenPerfil();
		}
	}
	
	//Método para poner la imagen de perfil del jugador en la etiqueta correspondiente
	public void mostrarImagenPerfil(){
		conectarBD();
		etiquetaImagenPerfil.setIcon(redimensionarImagen(controlBD.leerImagen(jug1.getUsuario()), 256, 256));				//Ponemos la imagen de perfil del jugador
	}
	
	//Método para redimensionar una imagen con anchura y altura introducidos por parámetro
	public ImageIcon redimensionarImagen(ImageIcon imagen, int anchura, int altura){
		Image img = imagen.getImage();
		Image nueva_img = img.getScaledInstance(anchura, altura, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionada = new ImageIcon(nueva_img);
		return imagenRedimensionada;
	}
	
	//Método para mostrar los datos del jugador
	public void mostrarDatos(){
		cajaNombre.setText(jug1.getNombre());
		cajaApellido1.setText(jug1.getApellido1());
		cajaApellido2.setText(jug1.getApellido2());
		cajaEdad.setText(String.valueOf(jug1.getEdad()));
		cajaApodo.setText(jug1.getUsuario());
		cajaPass.setText(jug1.getContrasenya());
	}
	
	//Método para actualizar las propiedades del jugador
	public void actualizarPropiedades(Jugador jug){
		jug.setNombre(cajaNombre.getText());
		jug.setApellido1(cajaApellido1.getText());
		jug.setApellido2(cajaApellido2.getText());
		jug.setEdad(Integer.valueOf(cajaEdad.getText()));
		jug.setUsuario(cajaApodo.getText());
		jug.setContrasenya(encript.encriptarString((String.valueOf(cajaPass.getPassword()))));								//Encriptamos la contraseña ya que trabajaremos con ella encriptada
	}
	
	//Método para mostrar la puntuación máxima
	public void mostrarPuntuacion(){
		etiquetaPuntuacion.setText("Puntuación máxima: " + jug1.getPuntuacionMax());
	}
	
	//Método para limpiar la caja de informacion
	public void limpiarCajaInfo(){
		cajaInfo.setText("");
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
	
	//Método para comprobar que un string no sea espacios en blanco
	public boolean sonEspacios(String t){
		for(int i =0; i<t.length(); i++)
		if(t.charAt(i) != ' ')							//Recorre todos los valores del string introducido y comprueba si son espacios
		return false;									//Si no son espacios devuelve false
			 
		return true;									//Si son espacios devuelve true
	}
	
	//Método para comprobar que no haya ninguna caja de texto vacía
	public boolean datosCorrectos(){
		if(cajaNombre.getText().isEmpty() || cajaApellido1.getText().isEmpty() || cajaApellido2.getText().isEmpty() || cajaEdad.getText().isEmpty()  || cajaApodo.getText().isEmpty() || String.valueOf(cajaPass.getPassword()).isEmpty()){
			cajaInfo.setText("Error. Algún dato vacío");
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean datosValidos(){
		if(sonEspacios(cajaNombre.getText()) || sonEspacios(cajaApellido1.getText()) || sonEspacios(cajaApellido2.getText()) || sonEspacios(cajaEdad.getText()) || sonEspacios(cajaApodo.getText()) || sonEspacios(String.valueOf(cajaPass.getPassword()))){
			return false;
		}
		else{
			if(cajaEdad.getText().matches("\\d*")){ 			//Si la caja edad es un numero
				return true;
			}
			else{
				cajaInfo.setText("La edad debe ser un número");
				return false;
			}
		}
	}
	
	
	/**
	 * Inner classes
	 */
	
	class ListenerBotonElegirImagen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Tamaño recomendado: 250 x 250 px");
			elegirImagen_paraJLabel(etiquetaImagenPerfil);
		}
		
	}
	
		class ListenerBotonActualizar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if (datosCorrectos()){
				if (datosValidos()){
					actualizarPropiedades(jug1);															//Actualizamos las propiedades el jugador
					conectarBD();																			//Nos conectamos a la bbdd
					controlBD.actualizarDatos(jug1); 														//Los actualizamos en la BBDD
					cajaInfo.setText("Perfil actualizado.");
				}
				else{
					//Datos no válidos
				}
			}
		}
	}
}


