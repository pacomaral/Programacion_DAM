import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	//PROPIEDADES O VARIABLES
	
	private JPanel contentPane;

	/**
	 * Punto de entrada a nuestra aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor.
	 */
	public Principal() {
		
		//Propiedades de nuestra ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Nuestro codigo
		
		//Objeto tipo carta
		
		/* Como hacerlo si las propiedades son públicas
		*Carta sieteBastos=new Carta();
		*sieteBastos.numero=7;
		*sieteBastos.palo="BASTOS";
		*System.out.println("Carta es: "+sieteBastos.numero+" y palo: "+sieteBastos.palo);
		*/
		
		//Carta de la Baraja española
		Carta sieteBastos=new Carta();
		sieteBastos.setNumero(7);			//asigna el 7 a la carta
		//sieteBastos.setPalo("BASTOS"); 	//asigna bastos a la carta
		sieteBastos.setPalo(Carta.BASTOS); 	//asigna bastos a la carta UTILIZANDO LA CONSTANTE
		System.out.println("Carta es: "+sieteBastos.getNumero()+" y palo: "+sieteBastos.getPalo());
		
		//Carta de Póker
		Carta diezPicas=new CartaPoker();
		diezPicas.setNumero(10);	//asigna el 10 a la carta
		diezPicas.setPalo(CartaPoker.PICAS); //asigna picas a la carta UTILIZANDO LA CONSTANTE
		System.out.println("Carta es: "+diezPicas.getNumero()+" y palo: "+diezPicas.getPalo());
		
	}

}
