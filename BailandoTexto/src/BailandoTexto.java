import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/*
 * Actividad Evaluable Proyecto 01 BailandoTexto - Paco Maravilla Aliaga
 */

public class BailandoTexto extends JFrame {

	private JPanel contentPane;
	private JTextField cajatexto1;
	private JTextField cajatexto2;

	/**
	 * Punto de inicio de la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BailandoTexto frame = new BailandoTexto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creación y configuración de la ventana
	 */
	public BailandoTexto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creación caja de texto 1
		cajatexto1 = new JTextField();
		cajatexto1.setBounds(43, 110, 145, 36);
		contentPane.add(cajatexto1);
		cajatexto1.setColumns(10);
		
		//Creacion boton 1
		JButton boton1 = new JButton("-->");
		//Listener o evento
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Acción a realizar cuando pulsamos el botón 1
				cajatexto2.setText(cajatexto1.getText());		//Ponemos el texto de la caja 1 en la caja 2
				cajatexto1.setText("");							//Ponemos el texto de la caja 1 en blanco
			}
		});
		boton1.setBounds(99, 155, 89, 23);
		contentPane.add(boton1);
		
		//Creacion boton 2
		JButton boton2 = new JButton("<--");
		//Listener o evento
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Acción a realizar cuando pulsamos el botón 2
				cajatexto1.setText(cajatexto2.getText());		//Ponemos el texto de la caja 2 en la caja 1
				cajatexto2.setText("");							//Ponemos el texto de la caja 2 en blanco
			}
		});
		boton2.setBounds(246, 155, 89, 23);
		contentPane.add(boton2);
		
		//Creación caja de texto 2
		cajatexto2 = new JTextField();
		cajatexto2.setBounds(246, 110, 145, 36);
		contentPane.add(cajatexto2);
		cajatexto2.setColumns(10);
		
		//Creación de la etiqueta 1
		JLabel etiqueta1 = new JLabel("Bailando Texto");
		etiqueta1.setFont(new Font("Consolas", Font.BOLD, 14));
		etiqueta1.setBounds(158, 38, 139, 14);
		contentPane.add(etiqueta1);
	}
}
