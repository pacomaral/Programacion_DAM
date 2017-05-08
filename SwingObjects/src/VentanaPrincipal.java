import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame{

	//Layout
	private JPanel contentPane;
	private JTextField cajatexto1;
	private JTextField cajatexto2;
	private JButton botonComprobacion;
	private JTextArea areatexto1;
	private JScrollPane scrollPane;
	private JLabel etiquetaNombre;
	
	public VentanaPrincipal() {
		//Propiedades necesarias para lanzar nuestra ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dimensiones ventana
		setBounds(100, 100, 450, 300);
		//Añadimos un Layout
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//
		setContentPane(contentPane);
		contentPane.setLayout(null);  //Absolute layout
		
		//Creacion primera etiqueta
		etiquetaNombre = new JLabel("Nombre");
		etiquetaNombre.setHorizontalAlignment(SwingConstants.CENTER);	//Alineación al centro
		etiquetaNombre.setFont(new Font("Consolas", Font.BOLD, 14));	//Cambiamos la fuente
		etiquetaNombre.setBounds(10, 11, 414, 14);
		etiquetaNombre.setText("Paco");		//También podemos poner el texto así
		contentPane.add(etiquetaNombre);	//Añadimos la etiqueta al contentPane
		
		//Creacion JTextField (caja de texto)
		cajatexto1 = new JTextField();
		cajatexto1.setText("Escribe algo");
		cajatexto1.setBounds(10, 33, 260, 20);
		contentPane.add(cajatexto1);
		cajatexto1.setColumns(10);
		
		//Creación otro JTextField
		cajatexto2 = new JTextField();
		cajatexto2.setEnabled(false);		//No se puede editar cuando ejecutamos el programa
		cajatexto2.setBounds(10, 230, 414, 20);
		cajatexto2.setText("Nuevo mensaje de texto");
		contentPane.add(cajatexto2);
		cajatexto2.setColumns(10);
		
		//Creación del scroll(lo veremos más adelante)
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 414, 149);
		contentPane.add(scrollPane);
		
		//Creación JTextArea (Es como el JTextField pero con una área predeterminada) 
		areatexto1 = new JTextArea();
		scrollPane.setViewportView(areatexto1);		//Lo metemos dentro del ScrollPane
		
		//Creación de botón
		botonComprobacion = new JButton("Comprobar");
		//Evento (llamado addActionListener
		botonComprobacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Acción a realizar al pulsar el botón
				String texto = cajatexto1.getText();
				if(texto.length()==0){
					cajatexto2.setText("No hay contenido");
				}
				else{
					cajatexto2.setText("Hay contenido");
				}
			}
		});
		botonComprobacion.setBounds(280, 32, 144, 23);
		contentPane.add(botonComprobacion);
		
	}
	
	public void cambiarMsg(String msg){
		cajatexto2.setText(msg);
	}
}
