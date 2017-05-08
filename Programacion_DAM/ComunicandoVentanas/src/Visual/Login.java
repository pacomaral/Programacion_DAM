package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private Login referencia;
	private VentanaPrincipal v1;


	/**
	 * Configuración de nuestra ventana
	 */
	public Login(VentanaPrincipal vPrinc) {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		referencia = this;										//Con esto asignamos referencia al objeto login
		v1 = vPrinc;
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				referencia.dispose();							//	Utilizamos referencia para cerrar login, ya que dentro del ActionListener 
			}													//	this.dispose() no nos serviría
		});
		botonCerrar.setBounds(255, 227, 169, 23);
		contentPane.add(botonCerrar);
		
		JButton botonVentanaPrincipal = new JButton("Ir a VentanaPrincipal");
		botonVentanaPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vPrinc.setVisible(true);
				referencia.setVisible(false);
			}
		});
		botonVentanaPrincipal.setBounds(255, 193, 169, 23);
		contentPane.add(botonVentanaPrincipal);
	}
}
