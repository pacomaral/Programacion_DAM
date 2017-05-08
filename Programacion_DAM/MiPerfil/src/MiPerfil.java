import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MiPerfil extends JFrame {

	private JPanel contentPane;
	private JTextField cajanombre;
	private JTextField cajaapellidos;
	private JTextField cajaedad;
	private JTextField cajaocu;
	private JTextField cajacorreo;

	/**
	 * Punto de entrada a nuestra aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiPerfil frame = new MiPerfil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Ventana
	 */
	public MiPerfil() {
		
		//Variables
		String nombre = "Paco";
		String apellidos = "Maravilla Aliaga";
		String correo = "maravilleta_6@hotmail.com";
		String ocupacion = "Estudiante";
		int edad = 19;
		
		//Configuración 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Etiqueta del nombre
		JLabel etiquetanombre = new JLabel("Nombre");
		etiquetanombre.setBounds(10, 11, 99, 14);
		contentPane.add(etiquetanombre);
		
		// Etiqueta de los apellidos
		JLabel etiquetaapellidos = new JLabel("Apellidos");
		etiquetaapellidos.setBounds(133, 11, 190, 14);
		contentPane.add(etiquetaapellidos);
		
		// Caja del nombre
		cajanombre = new JTextField();
		cajanombre.setBounds(10, 36, 99, 20);
		contentPane.add(cajanombre);
		cajanombre.setColumns(10);
		cajanombre.setText(nombre); //Añadimos el string a la caja
		
		// Caja de los apellidos
		cajaapellidos = new JTextField();
		cajaapellidos.setBounds(133, 36, 190, 20);
		contentPane.add(cajaapellidos);
		cajaapellidos.setColumns(10);
		cajaapellidos.setText(apellidos); //Añadimos el string a la caja
		
		// Etiqueta de la edad
		JLabel etiquetaedad = new JLabel("Edad");
		etiquetaedad.setBounds(365, 11, 46, 14);
		contentPane.add(etiquetaedad);
		
		// Caja de la edad
		cajaedad = new JTextField();
		cajaedad.setBounds(363, 36, 32, 20);
		contentPane.add(cajaedad);
		cajaedad.setColumns(10);
		cajaedad.setText(String.valueOf(edad)); // Añadimos el int a la caja con el comando String.valueOf(int).
		
		//Etiqueta de la ocupación
		JLabel etiquetaocu = new JLabel("Ocupaci\u00F3n");
		etiquetaocu.setBounds(10, 96, 99, 14);
		contentPane.add(etiquetaocu);
		
		//Etiqueta del correo
		JLabel etiquetacorreo = new JLabel("Correo");
		etiquetacorreo.setBounds(133, 96, 46, 14);
		contentPane.add(etiquetacorreo);
		
		//Caja de la ocupación
		cajaocu = new JTextField();
		cajaocu.setBounds(10, 121, 99, 20);
		contentPane.add(cajaocu);
		cajaocu.setColumns(10);
		cajaocu.setText(ocupacion); //Añadimos el string a la caja
		
		// Caja del correo
		cajacorreo = new JTextField();
		cajacorreo.setBounds(133, 121, 190, 20);
		contentPane.add(cajacorreo);
		cajacorreo.setColumns(10);
		cajacorreo.setText(correo); // Añadimos el string a la caja
	}
}
