import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class ObjetosYClases extends JFrame {

	private JPanel contentPane;
	private JTextField cajatexto;

	//Punto de entrada de la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObjetosYClases frame = new ObjetosYClases();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Creación de la ventana
	public ObjetosYClases() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Nuevo objeto de la clase JTextField
		cajatexto = new JTextField();
		contentPane.add(cajatexto, BorderLayout.NORTH);
		cajatexto.setColumns(10);
	}

}
