import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana1 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Ventana1() {
		setLayout(new java.awt.BorderLayout(0, 0));
		
		JButton btnNorte = new JButton("Norte");
		add(btnNorte, java.awt.BorderLayout.NORTH);
		
		JButton btnCentro = new JButton("Centro");
		btnCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//volver a la ventana principal
			}
		});
		add(btnCentro, java.awt.BorderLayout.CENTER);
		
		JButton btnOeste = new JButton("Oeste");
		add(btnOeste, java.awt.BorderLayout.WEST);
		
		JButton btnEste = new JButton("Este");
		add(btnEste, java.awt.BorderLayout.EAST);
		
		JButton btnSur = new JButton("Sur");
		add(btnSur, java.awt.BorderLayout.SOUTH);

	}

}
