package Visual;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	
	private JTextField cajaPalabra1;
	private JLabel etiquetaIntroducir;
	

	private JTextField cajaPalabra2;
	private JTextField cajaPalabra3;
	private JTextField cajaPalabra4;
	private JTextField cajaPalabra5;
	private JTextField cajaBuscarPalabra;
	private JButton botonOrdenar;
	private JButton botonBuscar;
	private JButton botonAnyadir;
	
	private ArrayList<String> miArrayList;						//Lo usaremos para añadir una nueva palabra
	private String[] miArray=new String[4];						//Array para almacenar las 4 palabras introducidas
	private String[] nuevoArray;								//Array para almacenar la nueva palabra + las otras 4
	
	//Variables que utilizaremos para buscar la palabra
	private int inicio,fin,med;
	private boolean estaEnArray=false;
	private JLabel etiquetaResultado;
	
	

	/**
	 * 	CONSTRUCTOR
	 */
	public Ventana() {
		
		//Configuración de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Componentes gráficos
		cajaPalabra1 = new JTextField();
		cajaPalabra1.setEditable(true);
		cajaPalabra1.setBounds(20, 59, 86, 20);
		contentPane.add(cajaPalabra1);
		cajaPalabra1.setColumns(10);
		cajaPalabra1.setName("1");
		cajaPalabra1.addActionListener(new ListenerCajas());
		
		cajaPalabra2 = new JTextField();
		cajaPalabra2.setEditable(true);
		cajaPalabra2.setColumns(10);
		cajaPalabra2.setBounds(20, 90, 86, 20);
		contentPane.add(cajaPalabra2);
		cajaPalabra2.setName("2");
		cajaPalabra2.addActionListener(new ListenerCajas());
		
		cajaPalabra3 = new JTextField();
		cajaPalabra3.setEditable(true);
		cajaPalabra3.setColumns(10);
		cajaPalabra3.setBounds(20, 121, 86, 20);
		contentPane.add(cajaPalabra3);
		cajaPalabra3.setName("3");
		cajaPalabra3.addActionListener(new ListenerCajas());
		
		cajaPalabra4 = new JTextField();
		cajaPalabra4.setEditable(true);
		cajaPalabra4.setColumns(10);
		cajaPalabra4.setBounds(20, 152, 86, 20);
		contentPane.add(cajaPalabra4);
		cajaPalabra4.setName("4");
		cajaPalabra4.addActionListener(new ListenerCajas());
		
		cajaPalabra5 = new JTextField();
		cajaPalabra5.setEnabled(true);
		cajaPalabra5.setEditable(false);
		cajaPalabra5.setBounds(20, 183, 86, 20);
		contentPane.add(cajaPalabra5);
		cajaPalabra5.setColumns(10);
		cajaPalabra5.setName("5");
		cajaPalabra5.addActionListener(new ListenerCajas());
		
		cajaBuscarPalabra = new JTextField();
		cajaBuscarPalabra.setEditable(false);
		cajaBuscarPalabra.setBounds(292, 90, 86, 20);
		contentPane.add(cajaBuscarPalabra);
		cajaBuscarPalabra.setColumns(10);
		cajaBuscarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){												
				if(cajaPalabra5.isEditable() || !(botonAnyadir.isEnabled())){					//Si ocurre algo de esto tocara el array de 5 palabras
					//Antes de realizar la búsqueda binaria, tendremos que ordenar el array:
					ordenarArray(nuevoArray);
					buscar(nuevoArray, cajaBuscarPalabra.getText());
				}
				else{																			//Si no, el de 4
					ordenarArray();
					buscar(miArray, cajaBuscarPalabra.getText());
				}
			}
		});
		
		botonOrdenar = new JButton("Ordenar");
		botonOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cajaPalabra5.isEditable() || !(botonAnyadir.isEnabled())){					//Si ocurre algo de esto tocara ordenar el array de 5 palabras
					ordenarArray(nuevoArray);
				}
				else{																			//Si no el de 4
					ordenarArray();
				}
			}
		});
		
		botonOrdenar.setBounds(148, 58, 124, 23);
		contentPane.add(botonOrdenar);
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Acción a realizar al pulsar el botón
				cajaBuscarPalabra.setEditable(true);
			}
		});
		botonBuscar.setBounds(148, 89, 124, 23);
		contentPane.add(botonBuscar);
		
		botonAnyadir = new JButton("A\u00F1adir palabra");
		botonAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cajaPalabra5.setEditable(true);
				botonAnyadir.setEnabled(false);
			}
		});
		botonAnyadir.setBounds(148, 120, 124, 23);
		contentPane.add(botonAnyadir);
		
		
		etiquetaIntroducir = new JLabel("Introduce las palabras:");
		etiquetaIntroducir.setBounds(20, 11, 145, 14);
		contentPane.add(etiquetaIntroducir);
		
		etiquetaResultado = new JLabel("");
		etiquetaResultado.setBounds(292, 62, 132, 14);
		contentPane.add(etiquetaResultado);
	}
	
	/**
	 * 	MÉTODOS
	 */
	//Método para ordenar un Array (método sobrecargado dependiendo de si introducimos parámetro o no) -> Si introducimos ordenará el array de 5 palabras
	public void ordenarArray(){
		Arrays.sort(miArray);
		cajaPalabra1.setText(miArray[0]);
		cajaPalabra2.setText(miArray[1]);
		cajaPalabra3.setText(miArray[2]);
		cajaPalabra4.setText(miArray[3]);
	}
	public void ordenarArray(String array[]){
		Arrays.sort(nuevoArray);
		cajaPalabra1.setText(array[0]);
		cajaPalabra2.setText(array[1]);
		cajaPalabra3.setText(array[2]);
		cajaPalabra4.setText(array[3]);
		cajaPalabra5.setText(array[4]);
	}
	
	//Método para añadir una palabra al array original
	public void anyadirPalabra(String array[], String palabra){
		miArrayList=new ArrayList<String>(Arrays.asList(array));				//Creamos arrayList introduciendo como parámetro la Lista del array original
		miArrayList.add(palabra);												//Añadimos la palabra deseada
		nuevoArray=new String[miArrayList.size()];								//Pasamos el arrayList al nuevo array que hemos creado previamente
		nuevoArray=miArrayList.toArray(nuevoArray);
	}
	
	//Método que realiza la búsqueda binaria
	public void buscar(String array[], String palabra){
		if(Arrays.binarySearch(array, palabra)>=0){								//Si devuelve un num >= 0 es que está, si es menor NO
			JOptionPane.showMessageDialog(null,"La palabra se encuentra en la lista"); 
		}
		else{
			JOptionPane.showMessageDialog(null,"La palabra NO se encuentra en la lista"); 
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Inner class
	class ListenerCajas implements ActionListener{

		public void actionPerformed(ActionEvent e){
			// Acción a realizar al hacer click
			JTextField caja=(JTextField)e.getSource();						//Comprobamos sobre que caja se ha realizado la accion
			int indice=Integer.valueOf(caja.getName());						//Asignamos a un entero el valor del nombre de la caja
			
			if(caja.getName().equals("5")){
				if(caja.getText().matches("[A-Z].*")){						//Devolverá true si el string empieza por mayúscula
					anyadirPalabra(miArray, caja.getText());
					nuevoArray[indice-1]=caja.getText();
					caja.setEditable(false);
					
				}
				else{
					JOptionPane.showMessageDialog(null,"Tienes que introducir una palabra que empiece por mayúscula"); 
				}
			}
			else{
				if(caja.getText().matches("[A-Z].*")){						//Devolverá true si el string empieza por mayúscula
					miArray[indice-1]=caja.getText();						//Asignamos el texto al valor del indice correspondiente (-1 porque el array empieza de [0]
					caja.setEditable(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Tienes que introducir una palabra que empiece por mayúscula"); 	
					}
				}
			}
		}
}
