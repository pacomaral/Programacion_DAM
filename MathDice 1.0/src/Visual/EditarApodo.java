/**
 *  ***JDialog: Es una ventana emergente que sirve para que el usuario introduzca algún dato adicional, por ejemplo si quisiera editar su alias.
 *  A diferencia del JFrame, no se crea otra ventana ni tiene los típicos botones de minimizar/maximizar***
 */

package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JComboBox;
import java.awt.Window.Type;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class EditarApodo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Jugador jug1;
	private JTextField cajaAlias;
	private JLabel etiquetaAlias;
	private JLabel etiquetaModo;
	private JLabel etiquetaCambiar;
	private JComboBox eleccion;
	private EditarApodo referencia;
	private VentanaLogin v1;
	private JButton botonContinuar;
	private JRadioButton botonFacil;
	private JRadioButton botonDificil;
	private ButtonGroup grupoBotones;

	/**
	 * Constructor
	 */
	public EditarApodo(VentanaJuego v3, VentanaJuego_dificil v4){
		
		referencia = this;
		
		setTitle("Editar apodo del juego");
		setBounds(100, 100, 371, 332);
		getContentPane().setLayout(null);									//Absolute layout
		
		//Etiquetas
		etiquetaAlias = new JLabel("Se te ha asignado el alias:");
		etiquetaAlias.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaAlias.setBounds(10, 28, 249, 25);
		getContentPane().add(etiquetaAlias);
		
		etiquetaModo = new JLabel("Modo de juego:");
		etiquetaModo.setFont(new Font("Calibri", Font.BOLD, 14));
		etiquetaModo.setBounds(54, 187, 97, 14);
		getContentPane().add(etiquetaModo);
		
		etiquetaCambiar = new JLabel("\u00BFQuieres cambiarlo?");
		etiquetaCambiar.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaCambiar.setBounds(64, 88, 162, 22);
		getContentPane().add(etiquetaCambiar);
		
		
		//CAJA ALIAS
		cajaAlias = new JTextField();
		cajaAlias.setFont(new Font("Verdana", Font.PLAIN, 13));
		cajaAlias.setBounds(197, 29, 116, 22);
		getContentPane().add(cajaAlias);
		cajaAlias.setColumns(10);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		cajaAlias.setEditable(false);
			//Listener para esperar a que se introduzca intro
		cajaAlias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				//Acción a realizar al presionar enter
				if(v1.comprobarSiHayValor(cajaAlias.getText()) == false){										//Se comprueba si está el campo vacío
					JOptionPane.showMessageDialog(referencia, "No puedes dejar el campo en blanco");
				}
				else if(v1.comprobarSiStringNumero(cajaAlias.getText()) == true){								//Se comprueba si son numeros
					JOptionPane.showMessageDialog(referencia, "El alias no pueden ser solamente numeros");
				}
				else if(v1.sonEspacios(cajaAlias.getText()) == true){											//Se comprueba si se han introducido espacios
					JOptionPane.showMessageDialog(referencia, "No puedes introducir espacios como alias");
				}
				else{
					cajaAlias.setEditable(false);
					jug1.setApodo(cajaAlias.getText());															//Asignamos al jugador el apodo deseado
					botonContinuar.setEnabled(true);
				}
			}
		//-----------------------------------------------
	});
		


		
		//ComboBox (menú para elegir si se quiere editar el alias o no)
		eleccion = new JComboBox();
		eleccion.setBounds(233, 90, 43, 22);
		getContentPane().add(eleccion);
		eleccion.addItem("--");
		eleccion.addItem("Si");
		eleccion.addItem("No");
		eleccion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//Acción a realizar al recibir el listener
				String seleccion = (String)eleccion.getSelectedItem();					//Comprobamos la elección y la guardamos como un String
				if(seleccion.equals("Si")){												//Si se ha elegido si:
					eleccion.setEnabled(false); 										//Para que no se pueda cambiar la elección
					etiquetaAlias.setText("Introduce el alias deseado: "); 				//Cambiamos el mensaje de la etiqueta
					cajaAlias.setText("");												//Limpiamos la cajaAlias
					cajaAlias.setEditable(true);										//Haremos editable la cajaAlias
					cajaAlias.requestFocus(); 											//Ponemos el cursor en la caja de texto
					eleccion.setEnabled(false);
				}
				else if(seleccion.equals("No")){										//Si se ha elegido no:
					cajaAlias.setEditable(false);
					jug1.setApodo(cajaAlias.getText());								 	//Asignamos al jugador el apodo deseado
					botonContinuar.setEnabled(true);		
					eleccion.setEnabled(false);
				}
			}
			});
		
		//Boton para continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.setEnabled(false);
		botonContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Accion a realizar al pulsar el botón
				if(botonFacil.isSelected()){
					v1.setVisible(false);																	//Cerramos la ventana1
					v3.setJugador_v3(jug1);   																//Pasamos jug1 a la ventana3 para poder utilizarlo
					v3.setVisible(true); 																	//Hacemos visible la ventana3 (Ventana del juego)
					dispose();																				//Cerramos la ventana de editar apodo
				}
				else if(botonDificil.isSelected()){
					v1.setVisible(false);
					v4.setJugador_v4(jug1);
					v4.setVisible(true);
					dispose();
				}
			}
		});
		botonContinuar.setBounds(137, 244, 89, 23);
		getContentPane().add(botonContinuar);
		//Mostrará un mensaje cuando pasemos el ratón por encima y el botón no esté activado
		if(!(botonContinuar.isEnabled())){
			botonContinuar.setToolTipText("Debes elegir si quieres cambiar tu alias o no");
		}
		
		
		//Botones para elegir una opción (Se meten en un 'ButtonGroup' para que sólo se pueda seleccionar uno)
		botonFacil = new JRadioButton("Fácil", true);
		botonFacil.setBounds(197, 168, 109, 23);
		getContentPane().add(botonFacil);
		
		botonDificil = new JRadioButton("Difícil", false);
		botonDificil.setBounds(197, 195, 109, 23);
		getContentPane().add(botonDificil);
		
		grupoBotones = new ButtonGroup();
		grupoBotones.add(botonFacil);
		grupoBotones.add(botonDificil);

		

			
	}
	
	/**
	 *  MÉTODOS
	 */
	
	//Método para importar el objeto jug1 creado previamente
	public void setJugador_v2(Jugador jug1){
		this.jug1 = jug1;
		cajaAlias.setText(jug1.getApodo()); 							//Ponemos el apodo en la caja del Alias
	}
	
	//Método para importar el objeto v1 y poder utilizar sus métodos (así podremos utilizar las comprobaciones)
	public void setV1(VentanaLogin v1){
		this.v1 = v1;
	}
}

