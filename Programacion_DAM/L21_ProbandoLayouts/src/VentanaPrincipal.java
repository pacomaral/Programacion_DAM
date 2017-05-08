import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	private final static String BORDERLAYOUT="Ventana con borderlayout";
	private final static String PRINCIPAL="Ventana principal";
	private final static String GRIDBAGLAYOUT="Ventana con gridbaglayout";
	
	private Ventana1 ventanaBorderLayout=new Ventana1();
	private GBLayout gridBagLayout = new GBLayout();
	private JPanel ventanaPrincipal=new JPanel();
	private JButton btnBorderlayout = new JButton("BorderLayout");
	private JButton botonGridBagLayout = new JButton("GridBagLayout");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//Añadimos las ventanas que queremos que tenga el CardLayout (se mostrará primero el primer JPanel que hayamos añadido)
		contentPane.add(ventanaPrincipal, PRINCIPAL);
		contentPane.add(ventanaBorderLayout, BORDERLAYOUT);
		contentPane.add(gridBagLayout, GRIDBAGLAYOUT);
		
		ventanaPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		ventanaPrincipal.add(btnBorderlayout);
		
		
		botonGridBagLayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mostrar el JPanel del GridBagLayout
				CardLayout c1 = (CardLayout)(contentPane.getLayout());
				c1.show(contentPane, GRIDBAGLAYOUT);
			}
		});
		
		btnBorderlayout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mostrar el JPanel del BorderLayout
				CardLayout c1 = (CardLayout)(contentPane.getLayout());
				c1.show(contentPane, BORDERLAYOUT);
			}
		});
		ventanaPrincipal.add(botonGridBagLayout);
	}

}
