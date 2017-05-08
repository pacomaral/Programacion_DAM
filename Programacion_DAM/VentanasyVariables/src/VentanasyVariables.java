import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanasyVariables extends JFrame {

	private JPanel contentPane; //Definimos el contenedor
	private JTextField caja1; //Definimos caja1
	private JLabel etiqueta2;
	private JTextField caja2;

	/**
	 * Punto de entrada a nuestra aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanasyVariables frame = new VentanasyVariables();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Configuración de nuestra ventana
	 */
	public VentanasyVariables() {
		
		//Variables
		String nombre = "Paco Maravilla";
		int edad = 19;
		
		//Ventana (JFrame)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//Contenedor (contentPane)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Generamos caja1 
		caja1 = new JTextField();
		caja1.setEditable(true);  //Para que podamos o no escribir en ella
		caja1.setText(nombre);
		caja1.setBounds(10, 36, 148, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		//Generamos etiqueta1
		JLabel etiqueta1 = new JLabel("Etiqueta 1");
		etiqueta1.setBounds(10, 11, 71, 14);
		contentPane.add(etiqueta1);
		
		//Generamos etiqueta2
		etiqueta2 = new JLabel("Etiqueta 2");
		etiqueta2.setBounds(10, 67, 71, 14);
		contentPane.add(etiqueta2);
		
		//Generamos caja2
		caja2 = new JTextField();
		caja2.setText(String.valueOf(edad)); //Se utiliza para poder convertir una variable a tipo String 
		caja2.setEditable(true);
		caja2.setColumns(10);
		caja2.setBounds(10, 92, 148, 20);
		contentPane.add(caja2);
	}
}
