import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ProbandoBoton extends JFrame {

	private JPanel contentPane;
	private JTextField etiqueta1;
	private JButton boton2;
	private JTextField etiqueta2;

	/**
	 * Punto de entrada a nuestra aplicaci�n
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProbandoBoton frame = new ProbandoBoton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Configuraci�n de la ventana
	 */
	public ProbandoBoton() {
		//Marco y contenido
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creaci�n del bot�n
		JButton boton1 = new JButton("Click aqu\u00ED para que te salude");
		//Evento + listener
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Acci�n cuando se pulsa bot�n
				etiqueta1.setText("Hola Paco");  // Ponemos el texto Hola Paco en la etiqueta cuando se pulsa el bot�n
			}
		});
		boton1.setBounds(10, 11, 414, 23);
		contentPane.add(boton1);
		
		//Creaci�n etiqueta1
		etiqueta1 = new JTextField();
		etiqueta1.setHorizontalAlignment(SwingConstants.CENTER); //Centramos el texto de la etiqueta
		etiqueta1.setBounds(123, 45, 183, 20);
		contentPane.add(etiqueta1);
		etiqueta1.setColumns(10);
		
		//Creaci�n segundo bot�n
		boton2 = new JButton("Click aqu\u00ED para comerme");
		//Evento + listener
		//--------
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Acci�n cuando se pulsa bot�n2
				etiqueta2.setText(etiqueta1.getText());  //Ponemos el texto de la etiqueta 1 en la 2
			}
		});
		//--------
		boton2.setBounds(10, 76, 414, 23);
		contentPane.add(boton2);
		
		//Creaci�n etiqueta2
		etiqueta2 = new JTextField();
		etiqueta2.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta2.setColumns(10);
		etiqueta2.setBounds(123, 110, 183, 20);
		contentPane.add(etiqueta2);
		
	}
}
