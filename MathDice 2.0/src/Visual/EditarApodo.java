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
import java.awt.Color;

public class EditarApodo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Jugador jug1;
	private JTextField cajaAlias;
	private JLabel etiquetaAlias;
	private JLabel etiquetaCambiar;
	private JComboBox eleccion;
	private EditarApodo referencia;
	private JButton botonContinuar;
	private JRadioButton botonFacil;
	private JRadioButton botonDificil;
	private ButtonGroup grupoBotones;
	
	private VentanaPrincipal vPrincipal;

	/**
	 * Constructor
	 */
	public EditarApodo(VentanaPrincipal vP){
		getContentPane().setBackground(new Color(153, 153, 102));
		
		vPrincipal = vP;
		referencia = this;
		
		setTitle("Editar apodo del juego");
		setBounds(100, 100, 371, 332);
		getContentPane().setLayout(null);									//Absolute layout
		
		//Etiquetas
		etiquetaAlias = new JLabel("Se te ha asignado el alias:");
		etiquetaAlias.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaAlias.setBounds(29, 81, 249, 25);
		getContentPane().add(etiquetaAlias);
		
		etiquetaCambiar = new JLabel("\u00BFQuieres cambiarlo?");
		etiquetaCambiar.setFont(new Font("Verdana", Font.PLAIN, 13));
		etiquetaCambiar.setBounds(83, 141, 162, 22);
		getContentPane().add(etiquetaCambiar);
		
		
		//CAJA ALIAS
		cajaAlias = new JTextField();
		cajaAlias.setFont(new Font("Verdana", Font.PLAIN, 13));
		cajaAlias.setBounds(216, 82, 116, 22);
		getContentPane().add(cajaAlias);
		cajaAlias.setColumns(10);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		cajaAlias.setEditable(false);
		cajaAlias.addActionListener(new ListenerCajaAlias());
		


		
		//ComboBox (menú para elegir si se quiere editar el alias o no)
		eleccion = new JComboBox();
		eleccion.setBackground(new Color(204, 255, 255));
		eleccion.setBounds(252, 143, 43, 22);
		getContentPane().add(eleccion);
		eleccion.addItem("--");
		eleccion.addItem("Si");
		eleccion.addItem("No");
		eleccion.addActionListener(new ListenerEleccion());
		
		
		//Boton para continuar
		botonContinuar = new JButton("Continuar");
		botonContinuar.setBackground(new Color(204, 255, 255));
		botonContinuar.setEnabled(false);
		botonContinuar.addActionListener(new ListenerBotonContinuar());
		botonContinuar.setBounds(137, 244, 89, 23);
		getContentPane().add(botonContinuar);
		
		
		//Mostrará un mensaje cuando pasemos el ratón por encima y el botón no esté activado
		if(!(botonContinuar.isEnabled())){
			botonContinuar.setToolTipText("Debes elegir si quieres cambiar tu alias o no");
		}
	}
	
	/**
	 *  MÉTODOS
	 */
	
	//Método para importar el objeto jug1 creado previamente
	public void setJugador_EditarApodo(Jugador jug1){
		this.jug1 = jug1;
		cajaAlias.setText(jug1.getApodo()); 											//Ponemos el apodo en la caja del Alias
	}
	
	/**
	 *  INNER CLASSES
	 */
	
	class ListenerCajaAlias implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(cajaAlias.getText().length()==0){
				JOptionPane.showMessageDialog(referencia, "No puedes dejar el campo en blanco");
			}
			else if(cajaAlias.getText().matches("\\d*")){
				JOptionPane.showMessageDialog(referencia, "El apodo no pueden ser sólo números");
			}
			else if(!cajaAlias.getText().matches("\\w*")){
				JOptionPane.showMessageDialog(referencia, "No puedes introducir espacios como alias");
			}
			else{
				cajaAlias.setEditable(false);
				jug1.setApodo(cajaAlias.getText());															//Asignamos al jugador el apodo deseado
				botonContinuar.setEnabled(true);
			}
		}	
	}
	
	class ListenerEleccion implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
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
	}
	
	class ListenerBotonContinuar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Accion a realizar al pulsar el botón
			vPrincipal.setVisible(true);
			vPrincipal.setJugador_ventanaPrincipal(jug1); 									//Pasamos el objeto 'jug1' a la ventana principal
			dispose();
		}
		
	}
}

	

