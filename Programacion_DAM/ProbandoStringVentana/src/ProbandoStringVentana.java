import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class ProbandoStringVentana extends JFrame {

	private JPanel contentPane;
	private JTextField cajatexto;

	//Punto de entrada de la aplicacion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProbandoStringVentana frame = new ProbandoStringVentana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Propiedades de nuestra ventana
	public ProbandoStringVentana() {
		//Variables
		String s1 = new String("Este es mi primer String en una ventana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Genera una caja de texto
		cajatexto = new JTextField();
		contentPane.add(cajatexto, BorderLayout.NORTH);
		cajatexto.setColumns(10);
		cajatexto.setText(s1);
	}

}
