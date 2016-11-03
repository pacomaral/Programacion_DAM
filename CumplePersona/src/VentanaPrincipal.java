import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField cajaNombre;
	private JTextField cajaEdad;
	private JTextField cajaDia;
	private JTextField cajaMes;
	private JTextField cajaAño;
	private JTextField cajaResultado;
	private JLabel etiquetaNombre;
	private JLabel etiquetaEdad;
	private JLabel etiquetaDia;
	private JLabel etiquetaMes;
	private JLabel etiquetaAño;
	private JButton botonGuardar;
	
	//Creamos objeto cumple
	private Cumple cump1;
	
	/**
	 *  Punto de entrada a nuestra aplicación
	 */

	public static void main(String[] args) {
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);

	}
	
	
	
	/**
	 * Configuración de nuestra ventana
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creacion etiquetaNombre
		etiquetaNombre = new JLabel("Nombre");
		etiquetaNombre.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaNombre.setBounds(10, 25, 67, 14);
		contentPane.add(etiquetaNombre);
		
		//Creacion etiquetaEdad
		etiquetaEdad = new JLabel("Edad");
		etiquetaEdad.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaEdad.setBounds(10, 64, 67, 14);
		contentPane.add(etiquetaEdad);
		
		//Creacion etiquetaDia
		etiquetaDia = new JLabel("Dia");
		etiquetaDia.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaDia.setBounds(10, 101, 67, 14);
		contentPane.add(etiquetaDia);
		
		//Creacion etiquetaMes
		etiquetaMes = new JLabel("Mes");
		etiquetaMes.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaMes.setBounds(10, 137, 67, 14);
		contentPane.add(etiquetaMes);
		
		//Creacion etiquetaAño
		etiquetaAño = new JLabel("A\u00F1o");
		etiquetaAño.setFont(new Font("Consolas", Font.BOLD, 12));
		etiquetaAño.setBounds(10, 172, 67, 14);
		contentPane.add(etiquetaAño);
		
		//Creacion cajaNombre
		cajaNombre = new JTextField();
		cajaNombre.setBounds(79, 21, 345, 20);
		contentPane.add(cajaNombre);
		cajaNombre.setColumns(10);
		
		//Creacion cajaEdad
		cajaEdad = new JTextField();
		cajaEdad.setColumns(10);
		cajaEdad.setBounds(79, 60, 60, 20);
		contentPane.add(cajaEdad);
		
		//Creacion cajaDia
		cajaDia = new JTextField();
		cajaDia.setColumns(10);
		cajaDia.setBounds(79, 97, 60, 20);
		contentPane.add(cajaDia);
		
		//Creacion cajaMes
		cajaMes = new JTextField();
		cajaMes.setColumns(10);
		cajaMes.setBounds(79, 133, 60, 20);
		contentPane.add(cajaMes);
		
		//Creacion cajaAño
		cajaAño = new JTextField();
		cajaAño.setColumns(10);
		cajaAño.setBounds(79, 168, 60, 20);
		contentPane.add(cajaAño);
		
		//Creacion botonGuardar
		botonGuardar = new JButton("Guardar");
		//Evento (addActionListener)
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Acción a realizar cuando presionamos el botón
				cump1.setNombre(cajaNombre.getText());						// Con todo esto asignamos las propiedades
				cump1.setEdad(Integer.valueOf(cajaEdad.getText()));			// del nuevo cumpleaños según los datos 
				cump1.setDia(Integer.valueOf(cajaDia.getText()));			// que se han introducido en las cajas
				cump1.setMes(Integer.valueOf(cajaMes.getText()));			// de texto de la VentanaPrincipal
				cump1.setAño(Integer.valueOf(cajaAño.getText()));			// Integer.valueOf(String) para asignar a un entero el valor de un String
				//Mostramos el cumpleaños en cajaResultado
				String resultado = cump1.getNombre() + " tiene " + cump1.getEdad() + " años, y cumple el día " + cump1.getDia() + " del mes " + cump1.getMes();
				System.out.println(resultado);		//Lo mostramos por consola
				cajaResultado.setText(resultado);	//Lo ponemos en la caja del Resultado
			}
		});
		botonGuardar.setFont(new Font("Consolas", Font.BOLD, 12));
		botonGuardar.setBounds(10, 199, 414, 23);
		contentPane.add(botonGuardar);
		
		//Creacion cajaResultado
		cajaResultado = new JTextField();
		cajaResultado.setFont(new Font("Consolas", Font.BOLD, 12));
		cajaResultado.setHorizontalAlignment(SwingConstants.CENTER);
		cajaResultado.setEditable(false);
		cajaResultado.setBounds(10, 230, 414, 20);
		contentPane.add(cajaResultado);
		cajaResultado.setColumns(10);
		
		//Creación nuevo cumpleaños
		cump1 = new Cumple();
	}
	
}
