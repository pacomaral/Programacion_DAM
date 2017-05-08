package Vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Juego_Facil extends JPanel {

	private JPanel contentPane;
	private Jugador jug1;
	private Juego_Facil referencia;
	private Random random = new Random();
	
	//JLabels
	private JLabel etiqueta1;
	private JLabel etiqueta2;
	private JLabel etiquetaSuma;
	private JLabel etiquetaResta;
	private JLabel dado1, dado2, dado3, dado4, dado5, dado6;
	
	//Jbutton
	private JButton botonComprobar;
	private JButton botonReintentar;
	
	//Separadores
	private JSeparator separador;
	private JSeparator separador2;
	
	private JTextField cajaTexto;
	
	//Variable "semáforo" (por defecto será true)
	private boolean tocaNumero=true;									//true = toca numero
	private boolean tocaOperacion=false;								//true = toca operacion
	
	//Otra variable de tipo "semáforo" para saber si toca suma o resta
	private boolean tocaSumar = true;									//true = toca sumar
	private boolean tocaRestar = false;									//true = toca restar
	
	//Variable para almacenar las operaciones que realizaremos
	private int resultadoOperaciones=0;
	
	//Variable para la puntuación del jugador
	private int puntuacion=0;
	
	//Variable para almacenar el valor del dado de 12 caras
	private int valorDado_12=0;
	
	
	/**
	 *  Variables necesarias para asignar las imágenes de los dados necesarios aleatoriamente
	 */
	
	//Arrays para almacenar las imágenes de los dados
	private ImageIcon[] dados3 = new ImageIcon[3];
	private ImageIcon[] dados6 = new ImageIcon[6];
	private ImageIcon[] dado12 = new ImageIcon[12];
	//ImageIcon para el dado gris
	private ImageIcon dadogris = new ImageIcon(getClass().getResource("/Imagenes/dado_gris.png"));
	
	//Array para guardar los números aleatorios
	private int[] numAleatorio = new int [6];               //Array que almacenará los números aleatorios a utlizar
	

	



	/**
	 * Constructor
	 */
	public Juego_Facil() {
		
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//Referencia
		referencia=this;
		
		//Configuración de la ventana
		setLayout(null);
		
		//Etiqueta para poner la imagen de la suma
		etiquetaSuma = new JLabel("");
		etiquetaSuma.setBounds(53, 230, 35, 35);
		add(etiquetaSuma);
		ImageIcon suma = new ImageIcon(getClass().getResource("/Imagenes/suma.png"));		//Creamos un objeto imageicon con la ruta de la imagen que queremos
		etiquetaSuma.setIcon(suma);
		etiquetaSuma.setName("Suma");
		
		
		//Etiqueta para poner la imagen de la resta
		etiquetaResta = new JLabel("");
		etiquetaResta.setBounds(53, 278, 35, 35);
		add(etiquetaResta);
		ImageIcon resta = new ImageIcon(getClass().getResource("/Imagenes/resta.png"));		//Ponemos la imagen
		etiquetaResta.setIcon(resta);
		etiquetaResta.setName("Resta");
		
		//Etiquetas para las imágenes de los dados
		
		dado1 = new JLabel("");
		dado1.setBounds(38, 36, 50, 50);
		add(dado1);

		
		dado2 = new JLabel("");
		dado2.setBounds(119, 36, 50, 50);
		add(dado2);
		
		
		dado3 = new JLabel("");
		dado3.setBounds(206, 36, 50, 50);
		add(dado3);
		
		dado4 = new JLabel("");
		dado4.setBounds(81, 119, 50, 50);
		add(dado4);

		
		dado5 = new JLabel("");
		dado5.setBounds(169, 119, 50, 50);
		add(dado5);

		
		dado6 = new JLabel("");
		dado6.setBounds(295, 78, 50, 50);
		add(dado6);
		
		//Línea para separar la ventana y organizarla mejor visualmente
		separador2 = new JSeparator();
		separador2.setBounds(10, 217, 357, 2);
		add(separador2);
		
		//Línea para separar la ventana y organizarla mejor visualmente
		separador = new JSeparator();
		separador.setOrientation(SwingConstants.VERTICAL);
		separador.setBounds(365, 0, 2, 219);
		add(separador);
			
		//Etiqueta para dar la bienvenida al jugador
		etiqueta1 = new JLabel("");
		etiqueta1.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta1.setBounds(365, 11, 136, 23);
		add(etiqueta1);

			
		//Etiqueta para mostrar la puntuación
		etiqueta2 = new JLabel("");
		etiqueta2.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta2.setBounds(365, 63, 136, 23);
		add(etiqueta2);
		etiqueta2.setText("Puntuacion: " + puntuacion);
			
		//Caja de texto para mostrar informacion
		cajaTexto = new JTextField();
		cajaTexto.setEditable(false);
		cajaTexto.setHorizontalAlignment(SwingConstants.CENTER);
		cajaTexto.setBounds(253, 259, 229, 29);
		add(cajaTexto);
		cajaTexto.setColumns(10);
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Boton de reintentar
		botonReintentar = new JButton("Reintentar");
		botonReintentar.setEnabled(false);
		botonReintentar.addActionListener(new ListenerBotonReintentar());
		botonReintentar.setBounds(135, 278, 97, 23);
		add(botonReintentar);
		
		//Boton de comprobar (MathDice)
		botonComprobar = new JButton("MathDice");
		botonComprobar.addActionListener(new ListenerBotonComprobar());
		botonComprobar.setBounds(135, 242, 97, 23);
		add(botonComprobar);
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Llamamos al método 
		tirarDados();
		
		//Creamos los mouselisteners
		dado1.addMouseListener(new ListenerDados());
		dado2.addMouseListener(new ListenerDados());
		dado3.addMouseListener(new ListenerDados());
		dado4.addMouseListener(new ListenerDados());
		dado5.addMouseListener(new ListenerDados());
		etiquetaSuma.addMouseListener(new ListenerOperaciones());
		etiquetaResta.addMouseListener(new ListenerOperaciones());
		
	}
	
	/**
	 *  Métodos
	 */

	//Método para importar el objeto jug1 creado previamente desde v2 (editar apodo)
	public void setJugador_vJuegoFacil(Jugador jug1){											
		this.jug1 = jug1;
		etiqueta1.setText("Bienvenido " + jug1.getNombre());
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Método para inicializar las imágenes de los dados (se utilizará al lanzar esta ventana o al presionar el botón de reiniciar.
	public void tirarDados(){
		
		
		//Rellenamos el array dados3 con las 3 imágenes del dado con valores 1, 2 y 3; para utilizarlo posteriormente
		for(int i=0; i<dados3.length; i++){
			dados3[i] = new ImageIcon(getClass().getResource("/Imagenes/dado"+String.valueOf(i+1)+"_3.png"));
		}
		
		
		//Rellenamos el array dados6 con las imágenes del dado con 6 caras
		for(int i=0; i<dados6.length; i++){
			dados6[i] = new ImageIcon(getClass().getResource("/Imagenes/dado"+String.valueOf(i+1)+"_6.png"));
		}
		
		
		//Rellenamos el array dados12 con las imágenes del dado con 12 caras
		for(int i=0; i<dado12.length; i++){
			dado12[i] = new ImageIcon(getClass().getResource("/Imagenes/dado"+String.valueOf(i+1)+"_12.png"));
		}

		//Rellenamos el array numAleatorio
		for(int i=0; i<3; i++){
			numAleatorio[i] = random.nextInt(3);										//Numero aleatorio entre 0 y 2
		}
		for(int i=3; i<5; i++){
			numAleatorio[i] = random.nextInt(6);										//Numero aleatorio entre 0 y 5
		}
		numAleatorio[5] = random.nextInt(12);											//Numero aleatorio entre 0 y 11
		
		//Generamos los dados con las imágenes de los dados aleatorias
		dado1.setIcon(dados3[numAleatorio[0]]);
		dado2.setIcon(dados3[numAleatorio[1]]);
		dado3.setIcon(dados3[numAleatorio[2]]);
		dado4.setIcon(dados6[numAleatorio[3]]);
		dado5.setIcon(dados6[numAleatorio[4]]);
		dado6.setIcon(dado12[numAleatorio[5]]);
	
		
		//Ponemos nombres a los JLabels para poder identificarlos después
		dado1.setName("1");
		dado2.setName("2");
		dado3.setName("3");
		dado4.setName("4");
		dado5.setName("5");
		dado6.setName("6");
		
		//Ponemos el semáforo en true ya que al empezar tocará seleccionar número
		tocaNumero = true;	
		
		//Almacenamos el valor del dado de 12 en una variable int
		int dado_12=Integer.valueOf(dado6.getName());
		int valorDado12 = numAleatorio[dado_12-1] + 1;								//Obtenemos el valor del dado de 12 caras
		valorDado_12 = valorDado12;													//Lo asignamos a esta variable para poder utilizarla fuera de este método
		
		sePuedeJugar(); 															//Llamamos al método para comprobar que sea jugable
		
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Método para realizar las operaciones
	public void operacion(int num){
		if(tocaSumar){							//Si toca sumar sumaremos el parámetro a la variable del resultado
			resultadoOperaciones = resultadoOperaciones + num;
		}
		else if(tocaRestar){					//Si no toca sumar restaremos el parámetro a la variable del resultado
			resultadoOperaciones = resultadoOperaciones - num;
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//MÉTODO PARA COMPROBAR QUE LA SUMA DE TODOS SEA MAYOR O IGUAL QUE EL DADO DE 12 CARAS, SI NO NO SE PUEDE JUGAR Y DEBEREMOS VOLVER A LANZAR LOS DADOS
	//De esta manera nunca ocurrirá que por ejemplo salga en todos los dados el 1, y en el dado de 12 un 10, por ejemplo.
	public void sePuedeJugar(){
		
		int dado1_3=Integer.valueOf(dado1.getName());
		int valorDado1_3= numAleatorio[dado1_3 - 1]+1;
		int dado2_3=Integer.valueOf(dado2.getName());
		int valorDado2_3= numAleatorio[dado2_3 - 1]+1;
		int dado3_3=Integer.valueOf(dado3.getName());
		int valorDado3_3= numAleatorio[dado3_3 - 1]+1;
		int dado1_6=Integer.valueOf(dado4.getName());
		int valorDado1_6= numAleatorio[dado1_6 - 1]+1;
		int dado2_6=Integer.valueOf(dado5.getName());
		int valorDado2_6= numAleatorio[dado2_6 - 1]+1;
		
		//Así, cada vez que se lancen los dados, comprobará que la suma de todos sea al menos igual que el valor del de 12, si no, volverá a lanzarlos.
		while((valorDado1_3+valorDado2_3+valorDado3_3+valorDado1_6+valorDado2_6)<valorDado_12){
			tirarDados();
		}
	}
	
	
	// --------------------------------------------------------------------------------------------------
	
	//Creamos la 'Inner class' ListenerDados
	class ListenerDados implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// Acción a realizar al hacer click
		JLabel dado = (JLabel) e.getSource(); 									//Esto comprueba sobre que dado se ha dado el evento
		int numeroDado = Integer.valueOf(dado.getName());
		int valorDado = numAleatorio[numeroDado-1] + 1;							//Esta variable almacenará el valor del dado que hemos presionado
		if(tocaNumero){
			operacion(valorDado);												//Llamamos al método para realizar la operación necesaria
			dado.setIcon(dadogris); 											//Ponemos el dado gris
			tocaNumero=false;													
			tocaOperacion=true;
			cajaTexto.setText(cajaTexto.getText() + String.valueOf(valorDado));
			dado.removeMouseListener(this);
		}
		else if(tocaOperacion){
			//No hará nada
		}		
	}
		
	//Esto tiene que estar en la clase aunque no lo utilicemos
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	}
			
			
			
			
			
	//Creamos la 'Inner class' ListenerOperaciones
	class ListenerOperaciones implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// Acción a realizar al hacer click
				JLabel operacion = (JLabel) e.getSource(); 					//Esto comprueba sobre que operacion se ha dado el evento
				if(tocaOperacion){
					if(operacion.getName()=="Resta"){							
						tocaSumar=false;
						tocaRestar=true;
						cajaTexto.setText(cajaTexto.getText() + "-"); 			//Añadimos el símbolo de la resta a la caja de texto
					}
					else if(operacion.getName()=="Suma"){
						tocaSumar=true;
						tocaRestar=false;
						cajaTexto.setText(cajaTexto.getText() + "+"); 			//Añadimos el símbolo de la suma a la caja de texto
					}
					tocaNumero=true;											//Una vez presionada una operación, volverá a tocar un número
					tocaOperacion=false;	
					}
				else if(tocaNumero){
					//No haremos nada	
				}
				}

		//Esto tiene que estar en la clase aunque no lo utilicemos
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	class ListenerBotonReintentar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Accion a realizar al presionar el boton
			tirarDados();
			botonReintentar.setEnabled(false);
			tocaNumero=true;											//Al volver a jugar, tocará número y no operación
			tocaSumar=true;												//Al volver a jugar, tocará que el primer número se sume a la variable
			tocaOperacion=false;
			botonComprobar.setEnabled(true); 							//Volveremos a activar el botón de comprobar
			if(botonReintentar.getText()=="Reintentar"){
				resultadoOperaciones=0;									//Reiniciamos la variable del resultado
				etiqueta2.setText("Puntuacion: 0"); 					//Reiniciamos la etiqueta que muestra el resultado
				puntuacion=0;											//Reiniciamos la variable de la puntuación
				cajaTexto.setText("");
			}
			else if(botonReintentar.getText()=="Continuar"){
				resultadoOperaciones=0;									//También se reinicia la variable en este caso
				cajaTexto.setText("");
			}
			botonReintentar.setText("Reintentar");
			botonReintentar.setEnabled(false);
			
			//Añadimos los MouseListeners, ya que al presionar en un Dado lo habremos eliminado y si no los añadimos no los podremos utilizar de nuevo
			dado1.addMouseListener(new ListenerDados());
			dado2.addMouseListener(new ListenerDados());
			dado3.addMouseListener(new ListenerDados());
			dado4.addMouseListener(new ListenerDados());
			dado5.addMouseListener(new ListenerDados());
		}
	}
	
	class ListenerBotonComprobar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Accion a realizar al presionar el boton
			botonComprobar.setEnabled(false); 												//Para que no se pueda presionar más veces y aumente la puntuación
			if(resultadoOperaciones==valorDado_12){
				puntuacion=puntuacion+5;
				etiqueta2.setText("Puntuacion: " + String.valueOf(puntuacion));				//Aumentamos la puntuación del jugador		
				botonReintentar.setText("Continuar");										//Cambiamos el texto del boton
				cajaTexto.setText("Correcto!");
			}
			else{
				botonReintentar.setText("Reintentar");										//Cambiamos el texto del boton
				cajaTexto.setText("Incorrecto. Vuelve a comenzar");
			}
			botonReintentar.setEnabled(true); 		
			
		}
		
	}
}
