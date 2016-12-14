package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Jugador;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaJuego extends JFrame {

	private JPanel contentPane;
	private Jugador jug1;
	private JLabel etiquetaPropiedades;


	/**
	 * Constructor
	 */
	public VentanaJuego() {								
		setTitle("MathDice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		etiquetaPropiedades = new JLabel("New label");
		etiquetaPropiedades.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaPropiedades.setBounds(25, 26, 399, 14);
		contentPane.add(etiquetaPropiedades);
	}
	
	/**
	 *  Métodos
	 */

	////Método para importar el objeto jug1 creado previamente desde v2 (editar apodo)
	public void setJugador_v3(Jugador jug1){
		this.jug1 = jug1;
		//Mostramos la información del jugador en el JLabel
		etiquetaPropiedades.setText("Bienvenido a MathDice " + jug1.getNombre() + ", tu alias es: " + jug1.getApodo());
	}
}
