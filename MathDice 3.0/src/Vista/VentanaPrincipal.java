package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Modelo.ConexionBD;
import Modelo.Control_BD;
import Modelo.Jugador;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {
	
	private JPanel contentPane;															
	
	private Jugador jug1;
	
	private Acceso vAcceso;
	
	//Objetos necesarios para conexión a BBDD
	private ConexionBD conexionBD;
	private Control_BD controlBD;

	//Constantes necesarias
	private final static String JUEGO_DIFICIL="Ventana del modo de juego difícil";		
	private final static String JUEGO_FACIL="Ventana del modo de juego fácil";
	private final static String MODO_JUEGO="Ventana para elegir el modo de juego";
	private final static String AYUDA="Ventana con instrucciones de juego";
	private final static String PERFIL="Ventana que nos permite modificar el perfil del jugador";
	private final static String CLASIFICACION="Ventana que nos muestra una tabla con una clasificación de puntuaciones";
	
	//Generamos las ventanas que añadiremos al CardLayout
	private Juego_Dificil vJuego_Dificil = new Juego_Dificil();
	private Juego_Facil vJuego_Facil = new Juego_Facil();
	private JPanel vModoJuego = new JPanel();
	private Ayuda vAyuda = new Ayuda();
	private Perfil vPerfil = new Perfil();
	private ClasificacionPuntuaciones vClasificacion = new ClasificacionPuntuaciones();
	
	boolean tocaModoDificil;
	
	//Componentes panel para elegir el modo de juego 
	private JRadioButton botonFacil;
	private JRadioButton botonDificil;
	private ButtonGroup grupoBotones;
	private JLabel etiquetaModoJuego;
	private JButton continuarBoton;
	
	//Componentes menú
	private JMenuBar barraMenu;
	private JMenu menuVentanas;
	private JMenu menuModoJuego;
	private JMenuItem subMenuEditarPerfil;
	private JMenuItem subMenuJuegoFacil;
	private JMenuItem subMenuJuegoDificil;
	private JMenuItem subMenuClasificacion;
	private JMenu menuAyuda;
	private JMenuItem subMenuComoJugar, subMenuDesconectar;
	

	/**
	 * Constructor
	 */
	public VentanaPrincipal() {
		
		
		//---------------------------------------------------------------------------------
		//Configuración del JFrame
		//---------------------------------------------------------------------------------
		setPreferredSize(new Dimension(600, 420));								//Tamaño predeterminado del Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 411);
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));							//CARDLAYOUT
				
				
				
		//Añadimos las ventanas que queremos que tenga el CardLayout (se mostrará primero el primer JPanel que hayamos añadido)
		contentPane.add(vModoJuego, MODO_JUEGO);
		contentPane.add(vJuego_Facil, JUEGO_FACIL);
		contentPane.add(vJuego_Dificil, JUEGO_DIFICIL);
		contentPane.add(vAyuda, AYUDA);
		contentPane.add(vPerfil, PERFIL);
		contentPane.add(vClasificacion, CLASIFICACION);
		
		vJuego_Dificil.setBackground(new Color(153, 153, 102));
		vJuego_Facil.setBackground(new Color(153, 153, 102));
		vClasificacion.setBackground(new Color(153, 153, 102));
		
		//---------------------------------------------------------------------------------
		//Panel para elegir el modo de juego
		//---------------------------------------------------------------------------------
		
		vModoJuego.setBackground(new Color(153, 153, 102));
		vModoJuego.setLayout(null);
		
		//Botones para elegir una opción (Se meten en un 'ButtonGroup' para que sólo se pueda seleccionar uno)
		botonFacil = new JRadioButton("Fácil", true);
		botonFacil.setBackground(new Color(153, 153, 102));
		botonFacil.setBounds(128, 92, 109, 23);
		vModoJuego.add(botonFacil);
		
		botonDificil = new JRadioButton("Difícil", false);
		botonDificil.setBackground(new Color(153, 153, 102));
		botonDificil.setBounds(128, 121, 101, 46);
		vModoJuego.add(botonDificil);
		
		grupoBotones = new ButtonGroup();
		grupoBotones.add(botonFacil);
		grupoBotones.add(botonDificil);
		
		etiquetaModoJuego = new JLabel("Elige un modo de juego:");
		etiquetaModoJuego.setFont(new Font("Consolas", Font.PLAIN, 15));
		etiquetaModoJuego.setBounds(66, 49, 248, 14);
		vModoJuego.add(etiquetaModoJuego);
		
		continuarBoton = new JButton("Continuar");
		continuarBoton.setBackground(new Color(204, 255, 255));
		continuarBoton.addActionListener(new ListenerBotonContinuar());
		continuarBoton.setBounds(110, 174, 89, 23);
		vModoJuego.add(continuarBoton);
		
		//---------------------------------------------------------------------------------
		//Configuración del menú
		//---------------------------------------------------------------------------------
		
		barraMenu = new JMenuBar();
		barraMenu.setForeground(new Color(255, 255, 255));
		barraMenu.setBackground(new Color(153, 153, 153));
		setJMenuBar(barraMenu);
		
		menuVentanas = new JMenu("Acceder");
		barraMenu.add(menuVentanas);
		
		menuModoJuego = new JMenu("Modo de juego");
		menuVentanas.add(menuModoJuego);
		
		subMenuJuegoFacil = new JMenuItem("F\u00E1cil");
		subMenuJuegoFacil.setName("Modo_Facil");
		menuModoJuego.add(subMenuJuegoFacil);
		
		subMenuJuegoDificil = new JMenuItem("Dif\u00EDcil");
		subMenuJuegoDificil.setName("Modo_Dificil");
		menuModoJuego.add(subMenuJuegoDificil);
		
		subMenuEditarPerfil = new JMenuItem("Editar perfil");
		subMenuEditarPerfil.setName("Perfil");
		menuVentanas.add(subMenuEditarPerfil);
		
		subMenuClasificacion = new JMenuItem("Clasificación");
		subMenuClasificacion.setName("Clasificacion");
		menuVentanas.add(subMenuClasificacion);
		
		menuAyuda = new JMenu("Ayuda");
		barraMenu.add(menuAyuda);
		
		subMenuComoJugar = new JMenuItem("Como jugar");
		subMenuComoJugar.setName("Ayuda");
		menuAyuda.add(subMenuComoJugar);
		
		subMenuDesconectar = new JMenuItem("Desconectar");
		subMenuDesconectar.setName("Desconectar");
		menuAyuda.add(subMenuDesconectar);
		
		//Listeners
		subMenuJuegoFacil.addActionListener(new ListenerMenuItems());
		subMenuJuegoDificil.addActionListener(new ListenerMenuItems());
		subMenuEditarPerfil.addActionListener(new ListenerMenuItems());
		subMenuComoJugar.addActionListener(new ListenerMenuItems());
		subMenuDesconectar.addActionListener(new ListenerMenuItems());
		subMenuClasificacion.addActionListener(new ListenerMenuItems());
		
		
		
		
	}
	
	/**
	 *  MÉTODOS
	 */
	public void setJugador_ventanaPrincipal(Jugador jug1){
		this.jug1 = jug1;
	}
	
	public void setConexion_ventanaPrincipal(ConexionBD conex, Control_BD control){				//Para pasar los objetos desde la ventana de acceso
		this.conexionBD = conex;
		this.controlBD = control;
	}
	
	public void setAcceso_ventanaPrincipal(Acceso vAcceso){
		this.vAcceso=vAcceso;
	}
	
	public void lanzarJuegoFacil(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, JUEGO_FACIL);	
		vJuego_Facil.setJugador_vJuegoFacil(jug1); 												//Pasamos el objeto al panel para utilizarlo allí
		setBounds(100, 100, 525, 394);
	}
	
	public void lanzarJuegoDificil(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, JUEGO_DIFICIL);
		vJuego_Dificil.setJugador_vJuegoDificil(jug1); 											//Pasamos el objeto al panel para utilizarlo allí
		vJuego_Dificil.setConexion_vJuegoDificil(conexionBD, controlBD); 						//Pasamos los objetos para la conexión
		setBounds(100, 100, 525, 394);
	}
	
	public void lanzarAyuda(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, AYUDA);
		setBounds(100, 100, 525, 394);
	}
	
	public void lanzarPerfil(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, PERFIL);
		vPerfil.setJugador_EditarPerfil(jug1);										//Pasamos el jugador allí para poder acceder a él
		vPerfil.setConexion_EditarPerfil(conexionBD, controlBD); 					//Pasamos los objetos de conexión para acceder desde sea ventana
		vPerfil.mostrarDatos();
		vPerfil.mostrarPuntuacion();
		vPerfil.limpiarCajaInfo();
	}
	
	public void lanzarAcceso(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, MODO_JUEGO);
		vAcceso.setVisible(true);
		vAcceso.limpiarCajas();
		dispose(); 																	//Cerramos la ventana actual
	}
	
	public void lanzarClasificacion(){
		CardLayout c1 = (CardLayout)(contentPane.getLayout());
		c1.show(contentPane, CLASIFICACION);
		vClasificacion.setConexion_Clasificacion(conexionBD, controlBD);
	}
	
	
	
	/**
	 *  INNER CLASSES
	 */
	
	class ListenerBotonContinuar implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(botonFacil.isSelected()){													//Mostramos el panel del juego facil
				lanzarJuegoFacil();
			}
			else if(botonDificil.isSelected()){												//Mostramos el panel del juego dificil
				lanzarJuegoDificil();
			}
			
		}
	}
	
	class ListenerMenuItems implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JMenuItem item = (JMenuItem)arg0.getSource(); 									//Comprobamos en que item hemos hecho click
			if(item.getName().equals("Modo_Facil")){
				lanzarJuegoFacil();	
			}
			else if(item.getName().equals("Modo_Dificil")){
				lanzarJuegoDificil();
			}
			else if(item.getName().equals("Perfil")){
				//Lanzar ventana de editar perfil
				lanzarPerfil();
			}
			else if(item.getName().equals("Ayuda")){
				//Lanzar ventana de ayuda
				lanzarAyuda();
			}
			else if(item.getName().equals("Desconectar")){
				//Mostrar Frame acceso de nuevo
				lanzarAcceso();
			}
			else if(item.getName().equals("Clasificacion")){
				lanzarClasificacion();
			}
			
		}
		
	}
}
