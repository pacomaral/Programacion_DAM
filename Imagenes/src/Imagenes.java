import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Imagenes extends JFrame {

	private JPanel contentPane;
	private JLabel etiqueta_imagen;

	/**
	 * Punto de entrada de nuestra aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Imagenes frame = new Imagenes();
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
	public Imagenes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creación de etiqueta para la imagen 
		etiqueta_imagen = new JLabel("");
		
		/**
		 * CONTROL DE EXCEPCIONES
		 */
		try{	
			ImageIcon hyperv = new ImageIcon(getClass().getResource("img/hyperv.png"));		//Creamos un objeto imageicon con la ruta de la imagen que queremos
			etiqueta_imagen.setIcon(hyperv);												//Con setIcon(nombre_objeto); pondríamos la imagen en el JLabel
		}
		catch(Exception e){
			System.out.println("La ruta de la imagen es incorrecta o no existe.");  		//Mensaje que mostraría si hubiera error en el try anterior
		}																					//El JFrame se lanzaría igual pero sin la imagen
			
		etiqueta_imagen.setBounds(202, 95, 27, 27);		//Ponemos en los dos últimos campos las medidas de la imágen (p.e 256*256px la que vamos a usar)
		contentPane.add(etiqueta_imagen);
		
		
	}
}
