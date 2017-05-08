/**
 *  VENTANA PARA EL LOG-IN
 */


package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Jugador;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class VentanaLogin extends JFrame {

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
	
	private VentanaLogin referencia;
	private EditarApodo editarApodo;
	
	//Generamos un nuevo jugador
	Jugador jug1 = new Jugador();
	
	
	
	/**
	 * Configuración de nuestra ventana
	 */
	public VentanaLogin(EditarApodo v2) {						//Parámetro para acceder a v2 desde v1
		
		editarApodo = v2;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 102));
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
		ImageIcon img1 = new ImageIcon(getClass().getResource("/Imagenes/signo-interrogacion.png"));		//Creamos un objeto imageicon con la ruta de la imagen que queremos
		etiquetaImagen.setIcon(img1);
		//Añadimos el listener
	    etiquetaImagen.addMouseListener(new ListenerInterrogacion());
		
		
		//Caja de texto para poner el nombre
		cajaNombre = new JTextField();
		cajaNombre.setBounds(135, 43, 260, 20);
		contentPane.add(cajaNombre);
		cajaNombre.setColumns(10);
		cajaNombre.setName("Nombre");
		cajaNombre.addActionListener(new ListenerCajas());
	
		
		//Caja de texto para poner el primer apellido
		cajaPrimerapellido = new JTextField();
		cajaPrimerapellido.setBounds(135, 66, 260, 20);
		contentPane.add(cajaPrimerapellido);
		cajaPrimerapellido.setColumns(10);
		cajaPrimerapellido.setName("Apellido 1");
		cajaPrimerapellido.addActionListener(new ListenerCajas());
		
		//Caja de texto para poner el segundo apellido
		cajaSegundoapellido = new JTextField();
		cajaSegundoapellido.setBounds(135, 91, 260, 20);
		contentPane.add(cajaSegundoapellido);
		cajaSegundoapellido.setColumns(10);
		cajaSegundoapellido.setName("Apellido 2");
		cajaSegundoapellido.addActionListener(new ListenerCajas());
		
		//Caja de texto para poner la edad
		cajaEdad = new JTextField();
		cajaEdad.setBounds(135, 116, 260, 20);
		contentPane.add(cajaEdad);
		cajaEdad.setColumns(10);
		cajaEdad.setName("Edad");
		cajaEdad.addActionListener(new ListenerCajaEdad());

		
		
		//Caja para mostrar el resultado cuando creamos jugador
		cajaResultado = new JTextField();
		cajaResultado.setBackground(new Color(153, 153, 102));
		cajaResultado.setEditable(false);
		cajaResultado.setBounds(9, 225, 415, 25);
		contentPane.add(cajaResultado);
		cajaResultado.setColumns(10);
		
			
		
		//Botón crear jugador
		botonCrearjugador = new JButton("Crear jugador");
		botonCrearjugador.setBackground(new Color(204, 255, 255));
		botonCrearjugador.setForeground(Color.BLACK);
		//LISTENER
		botonCrearjugador.addActionListener(new ListenerBotonCrearJugador());
		botonCrearjugador.setBounds(10, 170, 252, 23);
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
	}
	
	/**
	 *  INNER CLASSES
	 */
	
	class ListenerInterrogacion implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JOptionPane.showMessageDialog(referencia, "Introduce tus datos en las cajas de texto y presiona intro. Después, haz click en crear jugador");
		}
		
		//Esto tiene que estar aunque no se utilice
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
	class ListenerCajas implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField caja = (JTextField)e.getSource(); 													//Comprobamos en qué caja hemos hecho click
			if (caja.getText().length()==0){																//Si está en blanco
				cajaResultado.setText("Has dejado el campo en blanco");										//Mostramos mensaje de error
			}
			else if (caja.getText().matches("\\d*")){														//Si es un numero
				cajaResultado.setText("El valor no puede ser un numero");									//Mostramos mensaje de error
			}
			else if (!caja.getText().matches("\\w*")){														//Si son espacios en blanco
				cajaResultado.setText("No puedes introducir espacios"); 									//Mostramos mensaje de error
			}
			else{
				caja.setEditable(false); 																	//Si son datos correctos ya no se puede editar
				cajaResultado.setText(""); 																	//Limpiamos la caja del resultado
				if(caja.getName().equals("Nombre")){	
					cajaPrimerapellido.requestFocus();
				}
				else if(caja.getName().equals("Apellido 1")){
					cajaSegundoapellido.requestFocus();
				}
				else if(caja.getName().equals("Apellido 2")){
					cajaEdad.requestFocus();
				}
			}
		}
	}
	
	class ListenerCajaEdad implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cajaEdad.getText().matches("\\d*")){
				cajaEdad.setEditable(false);
				cajaResultado.setText("");
			}
			else{
				cajaResultado.setText("La edad se debe poner con dígitos");
			}
		}
		
	}
	
	
	class ListenerBotonCrearJugador implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try{
				referencia.asignarPropiedades();														//Asignamos las propiedades al jugador
				cajaResultado.setText(jug1.infoJugador()); 												//Mostramos info del nuevo jugador
				jug1.crearApodo(); 																		//Creamos el apodo del jugador
				editarApodo.setJugador_EditarApodo(jug1); 												//Importamos el objeto a la ventana de editar el apodo
				editarApodo.setVisible(true); 															//Hacemos visible v2
				setVisible(false);									
			}
			catch(Exception e){
				cajaResultado.setText("Debes introducir antes valores en todos los campos");		//Mensaje a mostrar en caso de que presionar el botón antes de rellenar todos los campos
				System.out.println(e.toString());
			}
		}
		
	}
}

