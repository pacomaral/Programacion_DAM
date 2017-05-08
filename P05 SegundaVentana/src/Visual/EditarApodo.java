/**
 *  ***JDialog: Es una ventana emergente que sirve para que el usuario introduzca algún dato adicional, por ejemplo si quisiera editar su alias.
 *  A diferencia del JFrame, no se crea otra ventana ni tiene los típicos botones de minimizar/maximizar***
 */

package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JComboBox;
import java.awt.Window.Type;

public class EditarApodo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField cajaAlias;
	private JLabel etiquetaAlias;
	// private JButton botonDialogo;  no se utiliza de momento
	private Jugador jug1;
	private JLabel etiquetaCambiar;
	private JComboBox eleccion;
	private EditarApodo referencia;
	private VentanaLogin v1;

	/**
	 * Constructor
	 */
	public EditarApodo(VentanaJuego v3){
		
		referencia = this;
		
		setTitle("Editar apodo del juego");
		setBounds(100, 100, 370, 182);
		getContentPane().setLayout(null);									//Absolute layout
		
		//Etiqueta 
		etiquetaAlias = new JLabel("Se te ha asignado el alias:");
		etiquetaAlias.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaAlias.setBounds(10, 28, 249, 25);
		getContentPane().add(etiquetaAlias);
		
		
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
			//-----------------------------------------------
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
					v1.setVisible(false);																		//Cerramos la ventana1
					v3.setJugador_v3(jug1);   																	//Pasamos jug1 a la ventana3 para poder utilizarlo
					v3.setVisible(true); 																		//Hacemos visible la ventana3 (Ventana del juego)
					dispose();																					//Cerramos el JDialog
				}
			}
		//-----------------------------------------------
	});
		

		//Etiqueta
		etiquetaCambiar = new JLabel("\u00BFQuieres cambiarlo?");
		etiquetaCambiar.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaCambiar.setBounds(64, 88, 162, 22);
		getContentPane().add(etiquetaCambiar);
		
		//ComboBox (menú para elegir si se quiere editar el alias o no)
		eleccion = new JComboBox();
		eleccion.setBounds(233, 90, 43, 22);
		getContentPane().add(eleccion);
		eleccion.addItem("--");
		eleccion.addItem("Si");
		eleccion.addItem("No");
		
			//LISTENER--------------------------------------------------------------------------------------
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
				}
				else if(seleccion.equals("No")){										//Si se ha elegido no:
					cajaAlias.setEditable(false);
					jug1.setApodo(cajaAlias.getText());															//Asignamos al jugador el apodo deseado
					v1.setVisible(false);																		//Cerramos la ventana1
					v3.setJugador_v3(jug1);   																	//Pasamos jug1 a la ventana3 para poder utilizarlo
					v3.setVisible(true); 																		//Hacemos visible la ventana3 (Ventana del juego)
					dispose();																					//Cerramos el JDialog													//Cerramos el JDialog
				}
			}
			});
			//LISTENER--------------------------------------------------------------------------------------

			
	}
	
	/**
	 *  MÉTODOS
	 */
	
	//Método para importar el objeto jug1 creado previamente
	public void setJugador_v2(Jugador jug1){
		this.jug1 = jug1;
		cajaAlias.setText(jug1.getApodo()); 							//Ponemos el apodo en la caja del Alias
	}
	
	//Método para importar el objeto v1 y poder utilizar sus métodos
	public void setV1(VentanaLogin v1){
		this.v1 = v1;
	}
}
