package Visual;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Logica.Jugador;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.Color;

public class Juego_Dificil extends JPanel {
	
	private Jugador jug1;
	private Random random = new Random();
	
	//Paneles que incluiremos dentro de columnas/filas del gridbaglayout
	private JPanel panelDados=new JPanel();
	private JPanel panelOperaciones=new JPanel();
	
	//Componentes
	private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, espacioEnBlanco;
	private JLabel etiquetaSuma, etiquetaResta, etiquetaMultiplicacion, etiquetaDivision;
	private JLabel etiquetaTemp;
	private JLabel etiquetaPuntuacion;
	private JTextField cajaOperaciones;
	private JTextField cajaInfo;
	private JButton botonEmpezar;
	private JButton botonComprobar;
	private JSeparator separadorVertical;
	private JSeparator separadorHorizontal;
	
	//ImageIcons
	private ImageIcon suma = new ImageIcon(getClass().getResource("/Imagenes/suma.png"));								//Suma
	private ImageIcon resta = new ImageIcon(getClass().getResource("/Imagenes/resta.png"));								//Resta
	private ImageIcon multiplicacion = new ImageIcon(getClass().getResource("/Imagenes/multiplicacion.png"));			//Multiplicacion
	private ImageIcon division = new ImageIcon(getClass().getResource("/Imagenes/division.png"));						//Division
	private ImageIcon dadogris = new ImageIcon(getClass().getResource("/Imagenes/dado_gris.png"));						//Dado gris
	private ImageIcon dadoValor3 = new ImageIcon(getClass().getResource("/Imagenes/dado3_12.png"));						//Dado 12 caras parate 3
	
	//Arrays para almacenar las imágenes de los dados
	private ImageIcon[] dados3 = new ImageIcon[3];
	private ImageIcon[] dados6 = new ImageIcon[6];
	private ImageIcon[] dado12 = new ImageIcon[12];
	
	//Array para guardar los números aleatorios
	private int[] numAleatorio = new int [6];               //Array que almacenará los números aleatorios a utlizar
	
	//Variables para almacenar valores que necesitamos
	private int valorDado_12=0;
	private int dadoValor_3=0;
	private int puntuacion=0;
	private int resultadoOperaciones=0;
	
	//VARIABLES PARA TEMPORIZADOR
	private int min=0,seg=60;
	private DecimalFormat formatoNumero;
	private Timer timer;
	
	//Semáforos
	private boolean tocaNumero=false;									//true = toca numero
	private boolean tocaOperacion=false;								//true = toca operacion
	private boolean tocaSumar = true;									//true = tocará la operación correspondiente
	private boolean tocaRestar = false;									
	private boolean tocaMultiplicar=false;
	private boolean tocaDividir=false;

	
	/**
	 * Constructor
	 */
	public Juego_Dificil() {
		setBackground(new Color(153, 153, 102));
		panelDados.setBackground(new Color(153, 153, 102));
		
		
		//---------------------------------------------------------------------------
		//PANEL DADOS
		//---------------------------------------------------------------------------
		panelDados.setLayout(new GridLayout(2, 4, 0, 0));										//Le ponemos al layout que queremos
		
		dado1 = new JLabel();
		dado1.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado1);
		
		dado2 = new JLabel();
		dado2.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado2);
		
		dado3 = new JLabel();
		dado3.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado3);
		
		dado6 = new JLabel();
		dado6.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado6);
		
		dado4 = new JLabel();
		dado4.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDados.add(dado4);
		
		dado5 = new JLabel();
		dado5.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDados.add(dado5);
		
		espacioEnBlanco = new JLabel();
		espacioEnBlanco.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(espacioEnBlanco);
		
		dado7 = new JLabel();
		dado7.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado7);
		panelOperaciones.setBackground(new Color(153, 153, 102));
		
		//---------------------------------------------------------------------------
		//PANEL OPERACIONES 
		//---------------------------------------------------------------------------
		panelOperaciones.setLayout(new GridLayout(2,2,0,0));	 								//Ponemos el layout
		
		etiquetaSuma = new JLabel();
		etiquetaSuma.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaSuma.setIcon(suma);
		etiquetaSuma.setName("Suma");
		panelOperaciones.add(etiquetaSuma);
		
		etiquetaResta = new JLabel();
		etiquetaResta.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaResta.setName("Resta");
		etiquetaResta.setIcon(resta);
		panelOperaciones.add(etiquetaResta);
		
		etiquetaMultiplicacion = new JLabel();
		etiquetaMultiplicacion.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaMultiplicacion.setName("Multiplicacion");
		etiquetaMultiplicacion.setIcon(multiplicacion);
		panelOperaciones.add(etiquetaMultiplicacion);
		
		etiquetaDivision = new JLabel();
		etiquetaDivision.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaDivision.setName("Division");
		etiquetaDivision.setIcon(division);
		panelOperaciones.add(etiquetaDivision);
		
		
		//----------------------------------------------------------------------------
		//GridBagLayout
		//----------------------------------------------------------------------------
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
		
		//Añadimos los paneles de los dados y operaciones 
		GridBagConstraints gbc_dados=new GridBagConstraints();
		gbc_dados.weighty = 1.7;
		gbc_dados.weightx = 0.6;
		gbc_dados.gridx=0;															//Comenzará desde fila 0 y columna 0
		gbc_dados.gridy=0;
		gbc_dados.gridwidth=2;														//Ocupará 2 filas y 2 columnas
		gbc_dados.gridheight=2;
		gbc_dados.fill=GridBagConstraints.BOTH;										//Hacemos que el componente se estire hasta ocupar tanto toda la fila como toda la columna
		add(panelDados, gbc_dados);
		
		
		GridBagConstraints gbc_operaciones=new GridBagConstraints();
		gbc_operaciones.weightx = 0.5;
		gbc_operaciones.weighty = 0.3;
		gbc_operaciones.gridx=0;															//Comenzará desde fila 3 y columna 0
		gbc_operaciones.gridy=3;
		gbc_operaciones.gridheight=2;														//Height = altura 		!!! Ocupará 2 filas y 1 columna
		gbc_operaciones.gridwidth=1;														//Width = anchura
		gbc_operaciones.fill=GridBagConstraints.BOTH;										//Hacemos que el componente se estire hasta ocupar tanto toda la fila como toda la columna
		add(panelOperaciones, gbc_operaciones);
		
		//Separadores
		separadorVertical = new JSeparator(JSeparator.VERTICAL);
		separadorVertical.setBackground(new Color(153, 153, 102));
		GridBagConstraints gbc_separadorVertical = new GridBagConstraints();
		gbc_separadorVertical.weightx = 0.01;
		gbc_separadorVertical.gridx=2;
		gbc_separadorVertical.gridy=0;
		gbc_separadorVertical.gridheight=2;
		gbc_separadorVertical.gridwidth=1;
		gbc_separadorVertical.fill=GridBagConstraints.VERTICAL;
		separadorVertical.setPreferredSize(new Dimension(5,1));
		add(separadorVertical, gbc_separadorVertical);
		
		separadorHorizontal = new JSeparator(JSeparator.HORIZONTAL);
		separadorHorizontal.setBackground(new Color(153, 153, 102));
		GridBagConstraints gbc_separadorHorizontal = new GridBagConstraints();
		gbc_separadorHorizontal.weighty = 0.01;
		gbc_separadorHorizontal.gridx=0;
		gbc_separadorHorizontal.gridy=2;
		gbc_separadorHorizontal.gridheight=1;
		gbc_separadorHorizontal.gridwidth=2;
		gbc_separadorHorizontal.fill=GridBagConstraints.HORIZONTAL;
		separadorHorizontal.setPreferredSize(new Dimension(5,1));
		add(separadorHorizontal, gbc_separadorHorizontal);
		
		//Etiquetas
		etiquetaTemp = new JLabel();
		etiquetaTemp.setBackground(new Color(153, 153, 102));
		etiquetaTemp.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaTemp.setText("00:60");
		GridBagConstraints gbc_etiquetaTemp = new GridBagConstraints();
		gbc_etiquetaTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_etiquetaTemp.weighty = 0.25;
		gbc_etiquetaTemp.weightx = 0.39;
		gbc_etiquetaTemp.gridx=3;
		gbc_etiquetaTemp.gridy=0;
		gbc_etiquetaTemp.gridheight=1;
		gbc_etiquetaTemp.gridwidth=1;
		add(etiquetaTemp, gbc_etiquetaTemp);
		
		etiquetaPuntuacion = new JLabel();
		etiquetaPuntuacion.setBackground(new Color(153, 153, 102));
		etiquetaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaPuntuacion.setText("Puntuación: 0");
		GridBagConstraints gbc_etiquetaPuntuacion = new GridBagConstraints();
		gbc_etiquetaPuntuacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_etiquetaPuntuacion.weighty = 0.25;
		gbc_etiquetaPuntuacion.gridx=3;
		gbc_etiquetaPuntuacion.gridy=1;
		gbc_etiquetaPuntuacion.gridheight=1;
		gbc_etiquetaPuntuacion.gridwidth=1;
		add(etiquetaPuntuacion, gbc_etiquetaPuntuacion);
		
		//Cajas de texto
		cajaOperaciones = new JTextField();
		cajaOperaciones.setEditable(false);
		GridBagConstraints gbc_cajaOperaciones = new GridBagConstraints();
		gbc_cajaOperaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaOperaciones.insets = new Insets(0, 0, 0, 20);								//Con esto conseguimos que se separe 20 px del borde derecho
		gbc_cajaOperaciones.weightx = 0.4;
		gbc_cajaOperaciones.weighty = 0.15;
		gbc_cajaOperaciones.gridx=3;
		gbc_cajaOperaciones.gridy=3;
		gbc_cajaOperaciones.gridheight=1;
		gbc_cajaOperaciones.gridwidth=1;
		add(cajaOperaciones, gbc_cajaOperaciones);
		
		cajaInfo = new JTextField();
		cajaInfo.setEditable(false);
		GridBagConstraints gbc_cajaInfo = new GridBagConstraints();
		gbc_cajaInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cajaInfo.insets = new Insets(0, 0, 0, 20);								//Con esto conseguimos que se separe 20 px del borde derecho
		gbc_cajaInfo.weightx = 0.4;
		gbc_cajaInfo.weighty = 0.15;
		gbc_cajaInfo.gridx=3;
		gbc_cajaInfo.gridy=4;
		gbc_cajaInfo.gridheight=1;
		gbc_cajaInfo.gridwidth=1;
		add(cajaInfo, gbc_cajaInfo);
		
		//Botones 
		botonEmpezar = new JButton();
		botonEmpezar.setBackground(new Color(204, 255, 255));
		botonEmpezar.setText("Empezar");
		GridBagConstraints gbc_botonEmpezar = new GridBagConstraints();
		gbc_botonEmpezar.weightx = 0.1;
		gbc_botonEmpezar.gridx=1;
		gbc_botonEmpezar.gridy=3;
		gbc_botonEmpezar.gridheight=1;
		gbc_botonEmpezar.gridwidth=1;
		add(botonEmpezar, gbc_botonEmpezar);
		
		botonComprobar = new JButton();
		botonComprobar.setBackground(new Color(204, 255, 255));
		botonComprobar.setText("Comprobar");
		botonComprobar.setEnabled(false);
		GridBagConstraints gbc_botonComprobar = new GridBagConstraints();
		gbc_botonComprobar.weightx = 0.1;
		gbc_botonComprobar.gridx=1;
		gbc_botonComprobar.gridy=4;
		gbc_botonComprobar.gridheight=1;
		gbc_botonComprobar.gridwidth=1;
		add(botonComprobar, gbc_botonComprobar);
		
		//---------------------------------------------------------------------------
		//TEMPORIZADOR
		//---------------------------------------------------------------------------
		formatoNumero=new DecimalFormat("00");
        timer = new Timer(1000, new ListenerTemporizador());
        
        //Añadimos los listeners
        dado1.addMouseListener(new ListenerDados());
		dado2.addMouseListener(new ListenerDados());
		dado3.addMouseListener(new ListenerDados());
		dado4.addMouseListener(new ListenerDados());
		dado5.addMouseListener(new ListenerDados());
		
		etiquetaSuma.addMouseListener(new ListenerOperaciones());
		etiquetaResta.addMouseListener(new ListenerOperaciones());
		etiquetaMultiplicacion.addMouseListener(new ListenerOperaciones());
		etiquetaDivision.addMouseListener(new ListenerOperaciones());
		
		botonEmpezar.addActionListener(new ListenerEmpezar());
		botonComprobar.addActionListener(new ListenerComprobar());
        
        //Llamamos al método para que se muestren los dados de manera aleatoria
        tirarDados();
	}

	
	
	/**
	 * 	MÉTODOS 
	 */
	
	//Método para importar el objeto jug1 creado previamente desde v2 (editar apodo)
	public void setJugador_vJuegoDificil(Jugador jug1){											
		this.jug1 = jug1;
	}
	
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
		if(puntuacion > jug1.getPuntuacionMax()){									//Si la puntuación es mayor que la máxima del jugador, la asignamos
    		jug1.setPuntuacionMax(puntuacion);
    	}
		botonEmpezar.setEnabled(true);
		//Mensaje emergente para mostrar los puntos que ha conseguido
		JOptionPane.showMessageDialog(null, "Enhorabuena " + jug1.getNombre() + ", has obtenido " + puntuacion + " puntos." );
		//Reseteamos puntuación y la variable de resultado actual
		puntuacion=0;
		resultadoOperaciones=0;
		//Limpiamos las cajas
		cajaInfo.setText("");
		cajaOperaciones.setText("");
		botonEmpezar.setEnabled(true);
	}
	
	
	
	/**
	 * 	INNER CLASSES 
	 */
	
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
	
	class ListenerComprobar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Accion a realizar al presionar el boton
			tocaNumero=true;											//Al volver a jugar, tocará número y no operación
			tocaSumar=true;												//Al volver a jugar, tocará que el primer número se sume a la variable
			tocaOperacion=false;
			if(resultadoOperaciones==valorDado_12*dadoValor_3){
				puntuacion=puntuacion+5;
				etiquetaPuntuacion.setText("Puntuacion: " + String.valueOf(puntuacion));				//Aumentamos la puntuación del jugador		
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
	}
	
	class ListenerEmpezar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Accion a realizar al presionar el boton
			min=0;
			seg=60;
			botonEmpezar.setEnabled(false); 												//Para que no se pueda presionar más veces y aumente la puntuación
			timer.start();
			botonComprobar.setEnabled(true);
			tocaNumero=true;
		}
	}
	
	
	
	class ListenerTemporizador implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// Acción a realizar
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
                    	msgFinal();		//FALTA PROGRAMAR											//Llamamos al método cuando se acabe
                    	tirarDados();
                    	botonEmpezar.setEnabled(true);
                    	//Reiniciamos variablles
                    	min=0;
                    	seg=60;
                    	if(puntuacion > jug1.getPuntuacionMax()){									//Si la puntuación es mayor que la máxima del jugador, la asignamos
                    		jug1.setPuntuacionMax(puntuacion);
                    	}
                    }
                    catch(Exception e2){
                    	msgFinal();				//FALTA PROGRAMAR
                    	cajaInfo.setText("Ha acabado el tiempo");
                    	botonEmpezar.setEnabled(true);
                    	timer.stop();
                    	if(puntuacion > jug1.getPuntuacionMax()){									//Si la puntuación es mayor que la máxima del jugador, la asignamos
                    		jug1.setPuntuacionMax(puntuacion);
                    	}
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
	}
	
}
