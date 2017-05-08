package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Ventana_v2 extends JFrame {

	private JPanel contentPane;
	
	private JTextField cajaPalabras;
	private JTextField cajaResultado;
	private JButton botonOrdenar;
	private JButton botonBuscar;
	private JLabel etiqueta1;
	private ArrayList<String> listaPalabras;
	private JButton botonLimpiar;
	private JTextField cajaBuscar;
	
	private String palabras="";


	/**
	 * Constructor
	 */
	public Ventana_v2() {
		
		listaPalabras=new ArrayList<String>();
		
		
		//Configuración de nuestra ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		etiqueta1 = new JLabel("Introduzca las palabras que desee y pulse intro:");
		etiqueta1.setBounds(36, 48, 373, 14);
		contentPane.add(etiqueta1);
		
		cajaPalabras = new JTextField();
		cajaPalabras.setBounds(36, 91, 86, 20);
		contentPane.add(cajaPalabras);
		cajaPalabras.setColumns(10);
		cajaPalabras.addActionListener(new ListenerCajaPalabras());
		
		cajaResultado = new JTextField();
		cajaResultado.setBounds(32, 200, 377, 20);
		contentPane.add(cajaResultado);
		cajaResultado.setColumns(10);
		cajaResultado.setEditable(false);
		
		cajaBuscar = new JTextField();
		cajaBuscar.setEditable(false);
		cajaBuscar.setColumns(10);
		cajaBuscar.setBounds(36, 139, 86, 20);
		contentPane.add(cajaBuscar);
		cajaBuscar.addActionListener(new ListenerCajaBuscar());
		
		botonOrdenar = new JButton("Ordenar y mostrar");
		botonOrdenar.setBounds(141, 90, 150, 23);
		contentPane.add(botonOrdenar);
		botonOrdenar.setName("botonOrdenar");
		botonOrdenar.addActionListener(new ListenerBotones());
		
		botonBuscar = new JButton("Buscar palabra");
		botonBuscar.setBounds(141, 138, 150, 23);
		contentPane.add(botonBuscar);
		botonBuscar.setName("botonBuscar");
		botonBuscar.addActionListener(new ListenerBotones());
		
		botonLimpiar = new JButton("Limpiar");
		botonLimpiar.setBounds(320, 116, 89, 23);
		contentPane.add(botonLimpiar);
		botonLimpiar.setName("botonLimpiar");
		botonLimpiar.addActionListener(new ListenerBotones());
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 	  Métodos
	 */
	
	//Método para realizar una búsqueda binaria en el ArrayList (devolverá el valor del índice donde está la palabra, si no está devolvera un int negativo)
	public int busquedaBinaria(ArrayList<String> lista, String palabra){
		int inicio=0,fin=listaPalabras.size()-1,pos;
		while(inicio<=fin){
			pos=(inicio+fin)/2;
			if(listaPalabras.get(pos).compareTo(palabra)==0){
				return pos;															//Si al comparar es igual, nos devuelve la posición donde está la palabra en la lista
			}
			else if(listaPalabras.get(pos).compareTo(palabra)<0){
				inicio=pos+1;
			}
			else{
				fin=pos-1;
			}
		}
		return -1;																	//Si no encuentra el valor devuelve un int negativo
		}
	
	//Método para devolvernos un String con todos los elementos de un ArrayList
	public String listaToString(ArrayList<String> lista){
		palabras="";
		for(int i=0;i<lista.size();i++){																			
			palabras=palabras+ (i+1) + "-" + lista.get(i) + "   ";
		}
		return palabras;
	}
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 	  Inner classes
	 */
	
	class ListenerCajaPalabras implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Acción a realizar
			if(cajaPalabras.getText().matches("[a-zA-Z]*")){							//Comprobamos que no se introduzcan números ni carácteres extraños
				String palabra=cajaPalabras.getText();
				listaPalabras.add(palabra);
				//Limpiamos la caja
				cajaPalabras.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null,"Debes introducir una palabra");
			}
		}
	}
	
	class ListenerCajaBuscar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Acción a realizar
				String palabra=cajaBuscar.getText();
				int resultado=busquedaBinaria(listaPalabras, palabra);
				if(resultado>=0){
					JOptionPane.showMessageDialog(null,"La palabra se encuentra en la posición " + (resultado+1) + " de la lista");
				}
				else{
					JOptionPane.showMessageDialog(null,"La palabra no se encuentra en la lista");
				}
				botonBuscar.setEnabled(true);
				cajaBuscar.setEditable(false);
				cajaPalabras.setEditable(true);
				cajaBuscar.setText("");
			}
	}
	
	class ListenerBotones implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//Acción a realizar
			JButton boton=(JButton)e.getSource();
			if(boton.getName().equals("botonOrdenar")){
				//Acción al presionar el botón de ordenar
				if(listaPalabras.isEmpty()){													//Si no hemos introducido ninguna palabra mostramos mensaje de error
					JOptionPane.showMessageDialog(null,"No has introducido ninguna palabra"); 
				}
				else{
					//Ordenamos el ArrayList
					Collections.sort(listaPalabras);
					//Mostramos la lista ya ordenada en la caja de texto correspondiente
					cajaResultado.setText(listaToString(listaPalabras));
				}
			}
			else if(boton.getName().equals("botonBuscar")){
				//Acción a realizar al presionar el botón de buscar
				if(listaPalabras.isEmpty()){													//Si no hemos introducido ninguna palabra mostramos mensaje de error
					JOptionPane.showMessageDialog(null,"No has introducido ninguna palabra"); 
				}
				else{
					cajaBuscar.setEditable(true);
					cajaPalabras.setEditable(false);
					botonBuscar.setEnabled(false);
				}
			}
			else if(boton.getName().equals("botonLimpiar")){
				palabras="";										//Limpiamos el string de palabras
				cajaResultado.setText("");
				cajaBuscar.setText("");
				cajaPalabras.setText("");
				listaPalabras.clear();								//Limpiamos el valor arraylist (eliminamos todos los valores)
				botonBuscar.setEnabled(true);
			}
		}
	}
}
