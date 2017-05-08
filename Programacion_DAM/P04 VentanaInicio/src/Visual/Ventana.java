/**
 *  VENTANA PARA EL LOG-IN
 */


package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Jugador.Jugador;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JLabel etiquetaNombre;
	private JLabel etiquetaPrimerapellido;
	private JLabel etiquetaSegundoapellido;
	private JLabel etiquetaEdad;
	private JLabel etiquetaApodo;
	private JLabel etiquetaDatosJugador;
	private JLabel etiquetaImagen;
	private JTextField cajaNombre;
	private JTextField cajaPrimerapellido;
	private JTextField cajaSegundoapellido;
	private JTextField cajaEdad;
	private JTextField cajaApodo;
	private JButton botonCrearjugador;
	private JTextField cajaResultado;
	
	private Ventana referencia;
	
	//Generamos un nuevo jugador
	Jugador jug1 = new Jugador();
	
	
	
	/**
	 * Configuración de nuestra ventana
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//REFERENCIA 
		referencia = this;					//Para poder utilizar this dentro de los listeners
		
		
		//Etiqueta para el nombre
		etiquetaNombre = new JLabel("Nombre:");
		etiquetaNombre.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaNombre.setBounds(10, 44, 115, 20);
		contentPane.add(etiquetaNombre);
		
		//Etiqueta para el primer apellido
		etiquetaPrimerapellido = new JLabel("Primer apellido:");
		etiquetaPrimerapellido.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaPrimerapellido.setBounds(10, 67, 147, 20);
		contentPane.add(etiquetaPrimerapellido);
		
		//Etiqueta para el segundo apellido
		etiquetaSegundoapellido = new JLabel("Segundo apellido:");
		etiquetaSegundoapellido.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaSegundoapellido.setBounds(10, 92, 147, 20);
		contentPane.add(etiquetaSegundoapellido);
		
		//Etiqueta para la edad
		etiquetaEdad = new JLabel("Edad:");
		etiquetaEdad.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaEdad.setBounds(10, 117, 115, 20);
		contentPane.add(etiquetaEdad);
		
		//Etiqueta para el apodo
		etiquetaApodo = new JLabel("Alias en el juego:");
		etiquetaApodo.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaApodo.setBounds(10, 195, 164, 14);
		contentPane.add(etiquetaApodo);
		
		//Etiqueta "Datos del jugador"
		etiquetaDatosJugador = new JLabel("DATOS DEL JUGADOR");
		etiquetaDatosJugador.setFont(new Font("Consolas", Font.BOLD, 13));
		etiquetaDatosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaDatosJugador.setBounds(108, 11, 205, 14);
		contentPane.add(etiquetaDatosJugador);
		
		//JLabel para la imagen de la interrogación
		etiquetaImagen = new JLabel("");
		etiquetaImagen.setBounds(370, 184, 25, 25);
		contentPane.add(etiquetaImagen);
			//Ponemos imagen
		ImageIcon img1 = new ImageIcon(getClass().getResource("/imagenes/signo-interrogacion.png"));		//Creamos un objeto imageicon con la ruta de la imagen que queremos
		etiquetaImagen.setIcon(img1);
	    //MouseListener (listener para realizar acción al hacer click en el JLabel)
				//-------------------
			etiquetaImagen.addMouseListener(
					new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0){		//JOptionPane = ventana emergente con un mensaje
							JOptionPane.showMessageDialog(referencia, "Introduce tus datos en las cajas de texto y presiona intro. Después, haz click en crear jugador");
						}
						
					}
					);
				//--------------------
		
		
		//Caja de texto para poner el nombre
		cajaNombre = new JTextField();
		cajaNombre.setBounds(135, 43, 260, 20);
		contentPane.add(cajaNombre);
		cajaNombre.setColumns(10);
			//Listener para esperar a que se introduzca intro
			//-----------------------------------------------
		cajaNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//Acción a realizar al hacer intro (COMPROBACIONES CON LOS MÉTODOS)
				if (referencia.comprobarSiHayValor(cajaNombre.getText()) == false){								//Si está en blanco
					cajaResultado.setText("Has dejado el campo en blanco");										//Mostramos mensaje de error
				}
				else if (referencia.comprobarSiStringNumero(cajaNombre.getText()) == true){						//Si es un numero
					cajaResultado.setText("El valor del primer nombre no puede ser un numero");					//Mostramos mensaje de error
				}
				else if (referencia.sonEspacios(cajaNombre.getText()) == true){									//Si son espacios en blanco
					cajaResultado.setText("El nombre no pueden ser espacios. Introduce un nombre correcto"); 	//Mostramos mensaje de error
				}
				else{
					cajaNombre.setEditable(false); 																//Si son datos correctos ya no se puede editar
					cajaResultado.setText(""); 																	//Limpiamos la caja del resultado
					cajaPrimerapellido.requestFocus();															//Nos situa el cursor en la siguiente caja
				}
			}
			//-----------------------------------------------
		});
	
		
		//Caja de texto para poner el primer apellido
		cajaPrimerapellido = new JTextField();
		cajaPrimerapellido.setBounds(135, 66, 260, 20);
		contentPane.add(cajaPrimerapellido);
		cajaPrimerapellido.setColumns(10);
		cajaPrimerapellido.addActionListener(new ActionListener() {
			//Listener para esperar a que se introduzca intro
			//-----------------------------------------------
			public void actionPerformed(ActionEvent arg0){
				//Acción a realizar al presionar enter (COMPROBACIONES CON LOS MÉTODOS)
				if (referencia.comprobarSiHayValor(cajaPrimerapellido.getText()) == false){							//Si está en blanco
					cajaResultado.setText("Has dejado el campo en blanco");											//Mostramos mensaje de error
				}
				else if (referencia.comprobarSiStringNumero(cajaPrimerapellido.getText()) == true){					//Si es un numero
					cajaResultado.setText("El valor del primer apellido no puede ser un numero");					//Mostramos mensaje de error
				}
				else if (referencia.sonEspacios(cajaPrimerapellido.getText()) == true){								//Si son espacios en blanco
					cajaResultado.setText("El apellido no pueden ser espacios. Introduce un apellido correcto"); 	//Mostramos mensaje de error
				}
				else{
					cajaPrimerapellido.setEditable(false); 															//Si son datos correctos ya no se puede editar
					cajaResultado.setText(""); 																		//Limpiamos la caja del resultado
					cajaSegundoapellido.requestFocus();																//Nos situa el cursor en la siguiente caja
				}
			}
			//-----------------------------------------------
		});
		
		//Caja de texto para poner el segundo apellido
		cajaSegundoapellido = new JTextField();
		cajaSegundoapellido.setBounds(135, 91, 260, 20);
		contentPane.add(cajaSegundoapellido);
		cajaSegundoapellido.setColumns(10);
		//Listener para esperar a que se introduzca intro
		//-----------------------------------------------
		cajaSegundoapellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//Acción a realizar al hacer click en la imagen (COMPROBACIONES CON LOS MÉTODOS)
				if (referencia.comprobarSiHayValor(cajaSegundoapellido.getText()) == false){					//Si está en blanco
					cajaResultado.setText("Has dejado el campo en blanco");										//Mostramos mensaje de error
				}
				else if (referencia.comprobarSiStringNumero(cajaSegundoapellido.getText()) == true){			//Si es un numero
					cajaResultado.setText("El valor apellido no puede ser un numero");							//Mostramos mensaje de error
				}
				else if (referencia.sonEspacios(cajaSegundoapellido.getText()) == true){						//Si son espacios en blanco
					cajaResultado.setText("El apellido no pueden ser espacios. Introduce un nombre correcto"); 	//Mostramos mensaje de error
				}
				else{
					cajaSegundoapellido.setEditable(false); 													//Si son datos correctos ya no se puede editar
					cajaResultado.setText(""); 																	//Limpiamos la caja del resultado
					cajaEdad.requestFocus();																	//Nos situa el cursor en la siguiente caja
				}
			}
		//-----------------------------------------------
	});
		
		//Caja de texto para poner la edad
		cajaEdad = new JTextField();
		cajaEdad.setBounds(135, 116, 260, 20);
		contentPane.add(cajaEdad);
		cajaEdad.setColumns(10);
		//Listener para esperar a que se introduzca intro
		//-----------------------------------------------
		cajaEdad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//Acción a realizar al hacer click en la imagen (COMPROBACIONES CON LOS MÉTODOS)	
				if (referencia.comprobarSiHayValor(cajaEdad.getText()) == false){										//Si está en blanco
					cajaResultado.setText("Has dejado el campo en blanco");												//Mostramos mensaje de error
				}
				else if (referencia.comprobarSiStringNumero(cajaEdad.getText()) == false){								//Si NO es un numero
					cajaResultado.setText("El valor de la edad debe ser un numero");									//Mostramos mensaje de error
				}
				else{
					cajaEdad.setEditable(false); 																		//Si son datos correctos ya no se puede editar
					cajaResultado.setText(""); 																			//Limpiamos la caja del resultado
				}
			}
		//-----------------------------------------------
	});

		
		//Caja de texto para el apodo
		cajaApodo = new JTextField();
		cajaApodo.setHorizontalAlignment(SwingConstants.CENTER);
		cajaApodo.setEditable(false);
		cajaApodo.setBounds(150, 191, 105, 20);
		contentPane.add(cajaApodo);
		cajaApodo.setColumns(10);
		
		//Caja para mostrar el resultado cuando creamos jugador
		cajaResultado = new JTextField();
		cajaResultado.setEditable(false);
		cajaResultado.setBounds(9, 225, 415, 25);
		contentPane.add(cajaResultado);
		cajaResultado.setColumns(10);
		
		
		/**
		 *  BOTÓN PARA EDITAR EL APODO (NO SE UTILIZARÁ DE MOMENTO PARA ESTE PROYECTO04) POR QUE DABA ALGUN ERROR
		 */
		//JButton botonEditarapodo = new JButton("Editar apodo");
		//LISTENER
		//--------
		//botonEditarapodo.addActionListener(new ActionListener() {
			//Acción a realizar al presionar el botón
			//public void actionPerformed(ActionEvent arg0) {
				//cajaApodo.setEditable(true); 							//Esto hará que podamos editar la caja Apodo si no nos gusta el nombre generado
			//}
		//--------	
		//});
		//botonEditarapodo.setBounds(260, 102, 132, 23);
		//contentPane.add(botonEditarapodo);
		
		/**
		 *  BOTÓN PARA CREAR EL JUGADOR
		 */
		
		botonCrearjugador = new JButton("Crear jugador");
		botonCrearjugador.setForeground(Color.BLACK);
			//LISTENER
			//-----------------------------------------------------------------------------------------------
		botonCrearjugador.addActionListener(new ActionListener() {
			//Acción al presionar el botón
			public void actionPerformed(ActionEvent arg0) {																			
				try{
				referencia.asignarPropiedades();														//Asignamos las propiedades al jugador
				cajaResultado.setText(jug1.infoJugador()); 												//Mostramos info del nuevo jugador
				jug1.crearApodo(); 																		//Creamos el apodo del jugador
				cajaApodo.setText(jug1.getApodo()); 													//Mostramos el apodo en cajaApodo
				}
				catch(Exception e){
					cajaResultado.setText("Debes introducir antes valores en todos los campos");		//Mensaje a mostrar en caso de que presionar el botón antes de rellenar todos los campos
				}
				}
			//-------------------------------------------------------------------------------------------------
		});
		botonCrearjugador.setBounds(10, 153, 252, 23);
		contentPane.add(botonCrearjugador);
		
	}
	
	
	/**
	 *  								MÉTODOS
	 */
	
	//MÉTODO PARA ASIGNAR LOS VALORES DE LAS CAJAS DE TEXTO A LAS PROPIEDADES CORRESPONDIENTES DE LA CLASE JUGADOR
	public void asignarPropiedades(){
		jug1.setNombre(cajaNombre.getText());
		jug1.setApellido1(cajaPrimerapellido.getText());
		jug1.setApellido2(cajaSegundoapellido.getText());
		jug1.setEdad(Integer.valueOf(cajaEdad.getText()));		//Convertimos el string de cajaEdad a Integer para poder asignarlo a la propiedad EDAD del jugador
		jug1.setApodo(cajaApodo.getText());
	}
	
	//MÉTODO PARA COMPROBAR SI EL VALOR DE UNA CAJA ES UN NUMERO
	public boolean comprobarSiStringNumero(String cadena){
																//Si no está en blanco, comprobamos que se hayan introducido un numero entero:
			try{
				Integer.parseInt(cadena);				//Si puede transformar la cadena en numero entero
				return true;							//Será true
			}
			catch (Exception e){
				return false;							//Si no puede será false
			}
		}
	
	
	//MÉTODO PARA COMPROBAR SI UN STRING ESTÁ EN BLANCO
	public boolean comprobarSiHayValor(String t){
		if (t.length() == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	//MÉTODO PARA COMPROBAR SI SE HAN INTRODUCIDO ESPACIOS EN UNA CADENA
		//true = son espacios || false = no son espacios
	public boolean sonEspacios(String t){
		for(int i =0; i<t.length(); i++)
		if(t.charAt(i) != ' ')							//Recorre todos los valores del string introducido y comprueba si son espacios
		return false;									//Si no son espacios devuelve false
			 
		return true;									//Si son espacios devuelve true
	}
}

