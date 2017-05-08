package Visual;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Logica.Jugador;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

public class Perfil extends JPanel {
	
	private Jugador jug1;
	
	//Paneles a incluir en el GridBagLayout
	private JPanel panelDatos = new JPanel();
	private JPanel panelImagen = new JPanel();
	private Perfil referencia;
	
	//Componentes panel para los datos
	private JLabel etiquetaNombre;
	private JLabel etiquetaApellido1;
	private JLabel etiquetaApellido2;
	private JLabel etiquetaEdad;
	private JLabel etiquetaApodo;
	private JTextField cajaNombre;
	private JTextField cajaApellido1;
	private JTextField cajaApellido2;
	private JTextField cajaEdad;
	private JTextField cajaApodo;
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
		

		botonActualizar = new JButton();
		botonActualizar.setBackground(new Color(204, 255, 255));
		botonActualizar.setName("Actualizar");
		botonActualizar.setText("Actualizar");
		botonActualizar.setEnabled(false);
		if(!botonActualizar.isEnabled()){
			botonActualizar.setToolTipText("Debes actualizar algún campo de tu perfil");
		}
		GridBagConstraints gbc_botonActualizar = new GridBagConstraints();
		gbc_botonActualizar.insets = new Insets(10, 0, 0, 0);
		gbc_botonActualizar.gridx=0;														//Comenzará desde fila 0 y columna 0
		gbc_botonActualizar.gridy=10;
		panelDatos.add(botonActualizar, gbc_botonActualizar);
		
		//Añadiendo listeners
		cajaNombre.addActionListener(new ListenerCajasTexto());
		cajaApellido1.addActionListener(new ListenerCajasTexto());
		cajaApellido2.addActionListener(new ListenerCajasTexto());
		cajaApodo.addActionListener(new ListenerCajasTexto());
		cajaEdad.addActionListener(new ListenerCajaEdad());
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
		etiquetaImagenPerfil.setIcon(redimensionarImagen(avatar, 256, 256));
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
	
	
	//Método para poder elegir una imagen de nuestro sistema y ponerla en un JLabel
	public void elegirImagen_paraJLabel(JLabel etiqueta){
		fileChooser.setFileFilter(filter);												//Ponemos el filtro con el objeto creado previamente
		int opcion = fileChooser.showOpenDialog(referencia);							//Abrimos la ventana para buscar las imágenes
		if(opcion==JFileChooser.APPROVE_OPTION){										//Si hemos elegido nuestra imagen:
			String ruta = fileChooser.getSelectedFile().getPath();						//Obtenemos la ruta de la imagen
			imagen = new ImageIcon(ruta);												//Creamos el ImageIcon con la imagen seleccionada

			//Ponemos la imagen redimensionada en JLabel introducido como parámetro
			etiqueta.setIcon(redimensionarImagen(imagen, 256, 256));
		}
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
		cajaApodo.setText(jug1.getApodo());
	}
	
	//Método para actualizar las propiedades del jugador
	public void actualizarPropiedades(Jugador jug){
		jug.setNombre(cajaNombre.getText());
		jug.setApellido1(cajaApellido1.getText());
		jug.setApellido2(cajaApellido2.getText());
		jug.setEdad(Integer.valueOf(cajaEdad.getText()));
		jug.setApodo(cajaApodo.getText());
		cajaInfo.setText("Perfil actualizado");
	}
	
	//Método para mostrar la puntuación máxima
	public void mostrarPuntuacion(){
		etiquetaPuntuacion.setText("Puntuación máxima: " + jug1.getPuntuacionMax());
	}
	
	public void limpiarCajaInfo(){
		cajaInfo.setText("");
	}
	
	/**
	 * Inner classes
	 */
	
	class ListenerBotonElegirImagen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			elegirImagen_paraJLabel(etiquetaImagenPerfil);
		}
		
	}
	
	class ListenerCajasTexto implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent c) {
			JTextField caja = (JTextField)c.getSource();
			//Realizamos las comprobaciones
			if (caja.getText().length()==0){															//Si está en blanco
				cajaInfo.setText("Datos no válidos");										//Mostramos mensaje de error
			}
			else if (caja.getText().matches("\\d*")){													//Si es un numero
				cajaInfo.setText("Datos no válidos");									//Mostramos mensaje de error
			}
			else if (!caja.getText().matches("\\w*")){													//Si son espacios en blanco
				cajaInfo.setText("Datos no válidos"); 										//Mostramos mensaje de error
			}
			else{
				cajaInfo.setText("");																	//Limpiamos la caja del resultado
				botonActualizar.setEnabled(true);
				if(caja.getName().equals("Nombre")){	
					cajaApellido1.requestFocus();
				}
				else if(caja.getName().equals("Apellido 1")){
					cajaApellido2.requestFocus();
				}
				else if(caja.getName().equals("Apellido 2")){
					cajaEdad.requestFocus();
				}
				else if(caja.getName().equals("Apodo")){
					//cajaInfo.setText("Ya puedes actualizar tus datos");
				}
			}
		}
	}
	
	class ListenerCajaEdad implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(cajaEdad.getText().matches("\\d*")){
				cajaInfo.setText("");
				cajaApodo.requestFocus();
			}
			else{
				cajaInfo.setText("Datos no válidos");
			}
		}
	}
	
	class ListenerBotonActualizar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			actualizarPropiedades(jug1);
			botonActualizar.setEnabled(false);
		}
	}
}


