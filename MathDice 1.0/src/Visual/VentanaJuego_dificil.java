package Visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Jugador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;

public class VentanaJuego_dificil extends JFrame {
	
	private JPanel panelJuego;
	private JPanel panelAyuda;
	private JTabbedPane pestañas;
	private VentanaJuego_dificil referencia;
	private Random random = new Random();
	private Jugador jug1;
	
	//Componentes
	private JLabel etiqueta2;
	private JLabel etiquetaSuma;
	private JLabel etiquetaResta;
	private JLabel etiquetaMultiplicacion;
	private JLabel etiquetaDivision;
	private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7;
	private JLabel etiquetaTemp;
	
	private JTextField cajaOperaciones;
	
	private JSeparator separador2;
	private JSeparator separador1;
	
	private JButton botonComprobar;
	private JButton botonEmpezar;
	
	private JTextArea textoAyuda;
	
	//Semáforos
	private boolean tocaNumero=false;									//true = toca numero
	private boolean tocaOperacion=false;								//true = toca operacion
	private boolean tocaSumar = true;									//true = tocará la operación correspondiente
	private boolean tocaRestar = false;									
	private boolean tocaMultiplicar=false;
	private boolean tocaDividir=false;
	
	//Variables para almacenar valores que necesitamos
	private int valorDado_12=0;
	private int dadoValor_3=0;
	private int puntuacion=0;
	private int resultadoOperaciones=0;
	
	//ImageIcons con las imágenes necesarias
	private ImageIcon suma = new ImageIcon(getClass().getResource("/imagenes/suma.png"));								//Suma
	private ImageIcon resta = new ImageIcon(getClass().getResource("/imagenes/resta.png"));								//Resta
	private ImageIcon multiplicacion = new ImageIcon(getClass().getResource("/imagenes/multiplicacion.png"));			//Multiplicacion
	private ImageIcon division = new ImageIcon(getClass().getResource("/imagenes/division.png"));						//Division
	private ImageIcon dadogris = new ImageIcon(getClass().getResource("/imagenes/dado_gris.png"));						//Dado gris
	private ImageIcon dadoValor3 = new ImageIcon(getClass().getResource("/imagenes/dado3_12.png"));						//Dado 12 caras parate 3
	
	
	//Arrays para almacenar las imágenes de los dados
	private ImageIcon[] dados3 = new ImageIcon[3];
	private ImageIcon[] dados6 = new ImageIcon[6];
	private ImageIcon[] dado12 = new ImageIcon[12];
	
	//Array para guardar los números aleatorios
	private int[] numAleatorio = new int [6];               //Array que almacenará los números aleatorios a utlizar
	
	
	//VARIABLES PARA TEMPORIZADOR
	private int min=0,seg=60;
	private DecimalFormat formatoNumero;
	private Timer timer;
	private JTextField cajaInfo;

	

	/**
	 * Constructor
	 */
	public VentanaJuego_dificil() {
		
		//Creamos los objetos de los dos JPanel y el JTabbedPane (Los dos JPanel serán pestañas del JTabbedPane)
		panelJuego = new JPanel();
		panelAyuda = new JPanel();
		panelAyuda.setBackground(SystemColor.menu);
		pestañas = new JTabbedPane();
		
		//Añadimos los paneles al JTabbedPane()
		pestañas.addTab("MathDice", panelJuego);
		pestañas.addTab("Cómo jugar", panelAyuda);
		panelAyuda.setLayout(null);
		
		//Configuración de la ventana
		setTitle("MathDice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 452);
		
		//Añadimos las pestañas al contentPane
		getContentPane().add(pestañas);
		
		//Configuramos el panel del juego
		panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelJuego.setLayout(null);
		
		//*************************************************************************************************************************
		//COMPONENTES DEL PANEL DEL JUEGO
		//*************************************************************************************************************************
		
		//Etiqueta para mostrar la puntuación
		etiqueta2 = new JLabel("");
		etiqueta2.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta2.setBounds(377, 162, 136, 23);
		panelJuego.add(etiqueta2);
		etiqueta2.setText("Puntuacion: " + puntuacion);
		
		
		//Etiquetas para las OPERACIONES
		etiquetaSuma = new JLabel("");
		etiquetaSuma.setBounds(30, 252, 35, 35);
		panelJuego.add(etiquetaSuma);
		etiquetaSuma.setIcon(suma);
		etiquetaSuma.setName("Suma");
		
		etiquetaResta = new JLabel("");
		etiquetaResta.setBounds(134, 252, 35, 35);
		panelJuego.add(etiquetaResta);
		etiquetaResta.setIcon(resta);
		etiquetaResta.setName("Resta");
		
		etiquetaMultiplicacion = new JLabel("");
		etiquetaMultiplicacion.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaMultiplicacion.setBounds(30, 333, 35, 35);
		panelJuego.add(etiquetaMultiplicacion);
		etiquetaMultiplicacion.setIcon(multiplicacion);
		etiquetaMultiplicacion.setName("Multiplicacion");
		
		etiquetaDivision = new JLabel("");
		etiquetaDivision.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaDivision.setBounds(134, 333, 35, 35);
		panelJuego.add(etiquetaDivision);
		etiquetaDivision.setIcon(division);
		etiquetaDivision.setName("Division");
		
		//Etiqueta para mostrar el tiempo del temporizador
		etiquetaTemp = new JLabel("00:60");
		etiquetaTemp.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 25));
		etiquetaTemp.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaTemp.setBounds(387, 11, 114, 51);
		panelJuego.add(etiquetaTemp);
		
		
		//Etiquetas para las imágenes de los dados
		dado1 = new JLabel("");
		dado1.setBounds(38, 36, 50, 50);
		panelJuego.add(dado1);

		dado2 = new JLabel("");
		dado2.setBounds(119, 36, 50, 50);
		panelJuego.add(dado2);
		
		dado3 = new JLabel("");
		dado3.setBounds(206, 36, 50, 50);
		panelJuego.add(dado3);
		
		dado4 = new JLabel("");
		dado4.setBounds(81, 119, 50, 50);
		panelJuego.add(dado4);

		dado5 = new JLabel("");
		dado5.setBounds(169, 119, 50, 50);
		panelJuego.add(dado5);

		dado6 = new JLabel("");
		dado6.setBounds(294, 36, 50, 50);
		panelJuego.add(dado6);
		
		dado7 = new JLabel("");
		dado7.setName("6");
		dado7.setBounds(294, 119, 50, 50);
		panelJuego.add(dado7);
		
		//Cajas de texto
		cajaOperaciones = new JTextField();
		cajaOperaciones.setEditable(false);
		cajaOperaciones.setHorizontalAlignment(SwingConstants.CENTER);
		cajaOperaciones.setBounds(350, 281, 136, 29);
		panelJuego.add(cajaOperaciones);
		cajaOperaciones.setColumns(10);
		
		cajaInfo = new JTextField();
		cajaInfo.setHorizontalAlignment(SwingConstants.CENTER);
		cajaInfo.setEditable(false);
		cajaInfo.setBounds(365, 321, 108, 23);
		panelJuego.add(cajaInfo);
		cajaInfo.setColumns(10);
		
		
		//JSeparators (líneas para organizar la ventana)
		separador1 = new JSeparator();
		separador1.setBounds(10, 217, 357, 2);
		panelJuego.add(separador1);
		
		separador2 = new JSeparator();
		separador2.setOrientation(SwingConstants.VERTICAL);
		separador2.setBounds(365, 0, 2, 219);
		panelJuego.add(separador2);
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Boton de comprobación
		botonComprobar = new JButton("Comprobar");
		botonComprobar.setEnabled(false);
		botonComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Accion a realizar al presionar el boton
				tocaNumero=true;											//Al volver a jugar, tocará número y no operación
				tocaSumar=true;												//Al volver a jugar, tocará que el primer número se sume a la variable
				tocaOperacion=false;
				if(resultadoOperaciones==valorDado_12*dadoValor_3){
					puntuacion=puntuacion+5;
					etiqueta2.setText("Puntuacion: " + String.valueOf(puntuacion));				//Aumentamos la puntuación del jugador		
					cajaOperaciones.setText("");
					cajaInfo.setText("Correcto");
				}
				else{
					cajaOperaciones.setText("");
					cajaInfo.setText("Incorrecto");
				}
				tirarDados();												//Cada vez que comprobamos, volveremos a tirar los dados
				
				//Añadimos los MouseListeners, ya que al presionar en un Dado lo habremos eliminado y si no los añadimos no los podremos utilizar de nuevo
				dado1.addMouseListener(new ListenerDados());
				dado2.addMouseListener(new ListenerDados());
				dado3.addMouseListener(new ListenerDados());
				dado4.addMouseListener(new ListenerDados());
				dado5.addMouseListener(new ListenerDados());
			}
		});
		botonComprobar.setBounds(206, 333, 107, 23);
		panelJuego.add(botonComprobar);
		
		//Boton de Empezar 
		botonEmpezar = new JButton("Empezar");
		botonEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Accion a realizar al presionar el boton
				min=0;
				seg=60;
				botonEmpezar.setEnabled(false); 												//Para que no se pueda presionar más veces y aumente la puntuación
				timer.start();
				botonComprobar.setEnabled(true);
				tocaNumero=true;
			}
		});
		botonEmpezar.setBounds(206, 264, 107, 23);
		panelJuego.add(botonEmpezar);
		
		//*************************************************************************************************************************
		//COMPONENTES DEL PANEL DE AYUDA 
		//*************************************************************************************************************************
		
		textoAyuda = new JTextArea();
		textoAyuda.setBackground(SystemColor.menu);
		textoAyuda.setLineWrap(true);
		textoAyuda.setFont(new Font("Consolas", Font.PLAIN, 14));
		textoAyuda.setForeground(new Color(0, 128, 128));
		textoAyuda.setText("Esta versi\u00F3n de MathDice consiste en intentar conseguir el \r\nn\u00FAmero objetivo (Multiplicando el valor de los 2 dados de 12 caras), combinando operaciones con los otros 5 dados \r\n(multiplicar, dividir, sumar o restar). \r\n\r\nPara jugar, tenemos que hacer click en el bot\u00F3n comenzar. A \r\npartir de ese momento, tendremos 1 minuto para conseguir la \r\nm\u00E1xima puntuaci\u00F3n posible.\r\n\r\nPuede ser posible que no se pueda llegar al n\u00FAmero objetivo, en ese caso se har\u00E1 click en comprobar para que se vuelvan a tirar los dados y seguir con la siguienta jugada.");
		textoAyuda.setBounds(10, 22, 492, 227);
		panelAyuda.add(textoAyuda);
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//Llamamos al método 
		tirarDados();
		
		//Añadimos los MouseListeners a los distintos componentes
		dado1.addMouseListener(new ListenerDados());
		dado2.addMouseListener(new ListenerDados());
		dado3.addMouseListener(new ListenerDados());
		dado4.addMouseListener(new ListenerDados());
		dado5.addMouseListener(new ListenerDados());
		etiquetaSuma.addMouseListener(new ListenerOperaciones());
		etiquetaResta.addMouseListener(new ListenerOperaciones());
		etiquetaMultiplicacion.addMouseListener(new ListenerOperaciones());
		etiquetaDivision.addMouseListener(new ListenerOperaciones());
		
		//------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//TEMPORIZADOR
		
		formatoNumero=new DecimalFormat("00");
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Acción a realizar que ocurrirá cada vez que pase el intervalo de tiempo introducido en el parámetro (1000 milisegundos=1 segundo)
                if (seg > 0) {
                    seg--;
                } 
                else {
                    if (seg == 0 && min == 0) {								
                    	// Acción a realizar al acabarse el tiempo
                    	//Ponemos un control de excepciones porque puede pasar que se realice esta acción y se presione el botón comprobar (si pasa esto se colgaba el programa)
                        try{
                        	timer.stop();
                        	msgFinal();													//Llamamos al método cuando se acabe
                        	tirarDados();
                        	botonEmpezar.setEnabled(true);
                        	//Reiniciamos variablles
                        	min=0;
                        	seg=60;
                        }
                        catch(Exception e2){
                        	msgFinal();
                        	cajaInfo.setText("Ha acabado el tiempo");
                        	botonEmpezar.setEnabled(true);
                        	timer.stop();
                        }
                    } 
                    else if (min > 0) {											
                        min--;
                        seg = 60;
                    } 
                }
                //Mostramos en la etiqueta correspondiente 
                etiquetaTemp.setText(String.valueOf(formatoNumero.format(min))+":"+String.valueOf(formatoNumero.format(seg)));
            }
        });
	}
	
	
	
	/**
	 *  Métodos
	 */

	//Método para importar el objeto jug1 creado previamente desde v2 (editar apodo)
	public void setJugador_v4(Jugador jug1){											
		this.jug1 = jug1;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Método para inicializar las imágenes de los dados (se utilizará al lanzar esta ventana o al presionar el botón de reiniciar.
	public void tirarDados(){
		
		
		//Rellenamos el array dados3 con las 3 imágenes del dado con valores 1, 2 y 3; para utilizarlo posteriormente
		for(int i=0; i<dados3.length; i++){
			dados3[i] = new ImageIcon(getClass().getResource("/imagenes/dado"+String.valueOf(i+1)+"_3.png"));
		}
		
		
		//Rellenamos el array dados6 con las imágenes del dado con 6 caras
		for(int i=0; i<dados6.length; i++){
			dados6[i] = new ImageIcon(getClass().getResource("/imagenes/dado"+String.valueOf(i+1)+"_6.png"));
		}
		
		
		//Rellenamos el array dados12 con las imágenes del dado con 12 caras
		for(int i=0; i<dado12.length; i++){
			dado12[i] = new ImageIcon(getClass().getResource("/imagenes/dado"+String.valueOf(i+1)+"_12.png"));
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
		dado7.setIcon(dadoValor3);
	
		
		//Ponemos nombres a los JLabels para poder identificarlos después
		dado1.setName("1");
		dado2.setName("2");
		dado3.setName("3");
		dado4.setName("4");
		dado5.setName("5");
		dado6.setName("6");
		dado7.setName("7");
	
		//Almacenamos los valores de los dados de 12
		int dado12_1=Integer.valueOf(dado6.getName());									
		int valorDado12_1 = numAleatorio[dado12_1-1] + 1;								//Obtenemos el valor 
		valorDado_12 = valorDado12_1;													//Lo asignamos a sus variables para utilizarlas cuando se necesite
		dadoValor_3 = 3;
		
		//Reseteamos la variable del resultado
		resultadoOperaciones=0;
		
		//Llamamos al método para asegurar de que no sea imposible llegar al resultado
		sePuedeJugar();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Método para realizar las operaciones
	public void operacion(int num){
		if(tocaSumar){							
			resultadoOperaciones = resultadoOperaciones+num;
		}
		else if(tocaRestar){					
			resultadoOperaciones = resultadoOperaciones-num;
		}
		else if(tocaMultiplicar){
			resultadoOperaciones=resultadoOperaciones*num;
		}
		else if(tocaDividir){
			resultadoOperaciones=resultadoOperaciones/num;
		}
	}
	

	//Método para llamar cuando finalice el tiempo del temporizador
	public void msgFinal(){
		botonEmpezar.setEnabled(true);
		//Mensaje emergente para mostrar los puntos que ha conseguido
		JOptionPane.showMessageDialog(this, "Enhorabuena " + jug1.getNombre() + ", has obtenido " + puntuacion + " puntos." );
		//Reseteamos puntuación y la variable de resultado actual
		puntuacion=0;
		resultadoOperaciones=0;
		//Limpiamos las cajas
		cajaInfo.setText("");
		cajaOperaciones.setText("");
		botonEmpezar.setEnabled(true);
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Método para comprobar que no sea imposible llegar al número que se busca
	public void sePuedeJugar(){
		//Obtenemos el valor de todos los dados
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
		
		//Comprobamos que al menos multiplicándolos pasará el valor que se busca, si no, volveremos a lanzar los dados
		while((valorDado1_3*valorDado2_3*valorDado3_3*valorDado1_6*valorDado2_6)<valorDado_12*dadoValor_3){
			tirarDados();
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//Creamos la 'Inner class' ListenerDados
	class ListenerDados implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// Acción a realizar al hacer click
			cajaInfo.setText(""); 													//Limpiamos la caja de información
			JLabel dado = (JLabel) e.getSource(); 									//Esto comprueba sobre que dado se ha dado el evento
			int numeroDado = Integer.valueOf(dado.getName());
			int valorDado = numAleatorio[numeroDado-1] + 1;							//Esta variable almacenará el valor del dado que hemos presionado
			if(tocaNumero){
				operacion(valorDado);												//Llamamos al método para realizar la operación necesaria
				dado.setIcon(dadogris); 											//Ponemos el dado gris
				tocaNumero=false;													
				tocaOperacion=true;
				cajaOperaciones.setText(cajaOperaciones.getText() + String.valueOf(valorDado));
				dado.removeMouseListener(this);
				
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
				cajaInfo.setText(""); 													//Limpiamos la caja de información
				JLabel operacion = (JLabel) e.getSource(); 								//Esto comprueba sobre que operacion se ha dado el evento
				if(tocaOperacion){
					if(operacion.getName().equals("Resta")){							
						tocaSumar=false;
						tocaMultiplicar=false;
						tocaDividir=false;
						tocaRestar=true;
						cajaOperaciones.setText(cajaOperaciones.getText() + "-"); 			
					}
					else if(operacion.getName().equals("Suma")){
						tocaMultiplicar=false;
						tocaDividir=false;
						tocaRestar=false;
						tocaSumar=true;
						cajaOperaciones.setText(cajaOperaciones.getText() + "+"); 			
					}
					else if(operacion.getName().equals("Multiplicacion")){
						tocaSumar=false;
						tocaRestar=false;
						tocaDividir=false;
						tocaMultiplicar=true;
						cajaOperaciones.setText(cajaOperaciones.getText() + "x");
					}
					else if(operacion.getName().equals("Division")){
						tocaSumar=false;
						tocaRestar=false;
						tocaDividir=true;
						tocaMultiplicar=false;
						cajaOperaciones.setText(cajaOperaciones.getText() + ":");
					}
					tocaNumero=true;											//Una vez presionada una operación, volverá a tocar un número
					tocaOperacion=false;	
					}
		}
		
		//Esto tiene que estar en la clase aunque no lo utilicemos
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
}

