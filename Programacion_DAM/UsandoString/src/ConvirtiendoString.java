import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConvirtiendoString extends JFrame {

	private JPanel contentPane;
	private JTextField cajaEdad;
	private JLabel etiquetaMasa;
	private JTextField cajaMasa;
	int edad;
	String edadString;
	String masaString;
	int masa;

	/**
	 * Punto de entrada de nuestra aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConvirtiendoString frame = new ConvirtiendoString();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creación y configuración de nuestra ventana
	 */
	public ConvirtiendoString() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etiquetaEdad = new JLabel("Edad");
		etiquetaEdad.setBounds(10, 11, 46, 14);
		contentPane.add(etiquetaEdad);
		
		cajaEdad = new JTextField();
		cajaEdad.setBounds(10, 36, 27, 20);
		contentPane.add(cajaEdad);
		cajaEdad.setColumns(10);
		
		JButton botonConvertir = new JButton("Calcular");
		botonConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edadString = cajaEdad.getText();     									//Esto nos devolvería un string ya que cajaEdad es un JTextField
				edad = Integer.valueOf(edadString);										//Este comando convierte un String a un Integer
				edad++;
				System.out.println(edad);
				
				masa = edad * 2 + 10;													//Al revés, para mostrar un Int en JTextField necesitaremos convertirlo
				masaString = String.valueOf(masa);										//a un String, con el comando utilizado
				cajaMasa.setText(masaString);											//(La formula de la masa es inventada)
			}
		});
		botonConvertir.setBounds(10, 67, 89, 23);
		contentPane.add(botonConvertir);
		
		etiquetaMasa = new JLabel("Masa corporal");
		etiquetaMasa.setBounds(10, 101, 102, 14);
		contentPane.add(etiquetaMasa);
		
		cajaMasa = new JTextField();
		cajaMasa.setBounds(13, 126, 99, 20);
		contentPane.add(cajaMasa);
		cajaMasa.setColumns(10);
	}
}
