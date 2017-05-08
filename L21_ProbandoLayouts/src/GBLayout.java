import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class GBLayout extends JPanel {
	
	//Panelemos que incluiremos dentro de columnas/filas del gridbaglayout
	private JPanel panelDados=new JPanel();
	private JPanel panelOperaciones=new JPanel();
	
	
	private ImageIcon dadogris = new ImageIcon(getClass().getResource("/img/dado_gris.png"));
	

	/**
	 * Create the panel.
	 */
	public GBLayout() {
		
		
		//---------------------------------------------------------------------------
		//PANEL DADOS
		//---------------------------------------------------------------------------
		panelDados.setLayout(new GridLayout(2, 4, 0, 0));										//Le ponemos al layout que queremos
		
		JLabel dado1 = new JLabel();
		dado1.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado1);
		
		JLabel dado2 = new JLabel();
		dado2.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado2);
		
		JLabel dado3 = new JLabel();
		dado3.setHorizontalAlignment(SwingConstants.CENTER);
		dado3.setIcon(dadogris);
		panelDados.add(dado3);
		
		JLabel dado4 = new JLabel();
		dado4.setHorizontalAlignment(SwingConstants.CENTER);
		panelDados.add(dado4);
		
		JLabel dado6 = new JLabel();
		dado6.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDados.add(dado6);
		
		JLabel dado7 = new JLabel();
		dado7.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDados.add(dado7);
		
		JLabel dado5 = new JLabel();
		panelDados.add(dado5);
		
		JLabel dado8 = new JLabel();
		dado8.setHorizontalAlignment(SwingConstants.CENTER);
	
		panelDados.add(dado8);
		
		//---------------------------------------------------------------------------
		//PANEL OPERACIONES 
		//---------------------------------------------------------------------------
		panelOperaciones.setLayout(new GridLayout(2,2,0,0));	 								//Ponemos el layout
		
		JLabel op1 = new JLabel();
		op1.setHorizontalAlignment(SwingConstants.CENTER);
		op1.setIcon(dadogris);
		panelOperaciones.add(op1);
		
		JLabel op2 = new JLabel();
		op2.setHorizontalAlignment(SwingConstants.CENTER);
		op2.setIcon(dadogris);
		panelOperaciones.add(op2);
		
		JLabel op3 = new JLabel();
		op3.setHorizontalAlignment(SwingConstants.CENTER);
		op3.setIcon(dadogris);
		panelOperaciones.add(op3);
		
		JLabel op4 = new JLabel();
		op4.setHorizontalAlignment(SwingConstants.CENTER);
		op4.setIcon(dadogris);
		panelOperaciones.add(op4);
		
		
		//----------------------------------------------------------------------------
		//GridBagLayout
		//----------------------------------------------------------------------------
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Añadimos los paneles de los dados y operaciones 
		GridBagConstraints gbc_dados=new GridBagConstraints();
		gbc_dados.weighty = 0.7;
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
		JSeparator separadorVertical = new JSeparator(JSeparator.VERTICAL);
		GridBagConstraints gbc_separadorVertical = new GridBagConstraints();
		gbc_separadorVertical.weightx = 0.01;
		gbc_separadorVertical.gridx=2;
		gbc_separadorVertical.gridy=0;
		gbc_separadorVertical.gridheight=2;
		gbc_separadorVertical.gridwidth=1;
		gbc_separadorVertical.fill=GridBagConstraints.VERTICAL;
		separadorVertical.setPreferredSize(new Dimension(5,1));
		add(separadorVertical, gbc_separadorVertical);
		
		JSeparator separadorHorizontal = new JSeparator(JSeparator.HORIZONTAL);
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
		JLabel etiquetaTiempo = new JLabel();
		etiquetaTiempo.setText("00:60");
		GridBagConstraints gbc_etiquetaTiempo = new GridBagConstraints();
		gbc_etiquetaTiempo.weighty = 0.25;
		gbc_etiquetaTiempo.weightx = 0.39;
		gbc_etiquetaTiempo.gridx=3;
		gbc_etiquetaTiempo.gridy=0;
		gbc_etiquetaTiempo.gridheight=1;
		gbc_etiquetaTiempo.gridwidth=1;
		add(etiquetaTiempo, gbc_etiquetaTiempo);
		
		JLabel etiquetaPuntuacion = new JLabel();
		etiquetaPuntuacion.setText("Puntuación: 0");
		GridBagConstraints gbc_etiquetaPuntuacion = new GridBagConstraints();
		gbc_etiquetaPuntuacion.weighty = 0.25;
		gbc_etiquetaPuntuacion.gridx=3;
		gbc_etiquetaPuntuacion.gridy=1;
		gbc_etiquetaPuntuacion.gridheight=1;
		gbc_etiquetaPuntuacion.gridwidth=1;
		add(etiquetaPuntuacion, gbc_etiquetaPuntuacion);
		
		//Cajas de texto
		JTextField cajaOperaciones = new JTextField();
		cajaOperaciones.setEditable(false);
		GridBagConstraints gbc_cajaOperaciones = new GridBagConstraints();
		gbc_cajaOperaciones.weightx = 0.4;
		gbc_cajaOperaciones.weighty = 0.25;
		gbc_cajaOperaciones.insets = new Insets(0, 15, 0, 20);								//Con esto conseguimos que se separe 20 px del borde derecho
		gbc_cajaOperaciones.gridx=3;
		gbc_cajaOperaciones.gridy=3;
		gbc_cajaOperaciones.gridheight=1;
		gbc_cajaOperaciones.gridwidth=1;
		gbc_cajaOperaciones.fill=GridBagConstraints.HORIZONTAL;
		add(cajaOperaciones, gbc_cajaOperaciones);
		
		JTextField cajaResultado = new JTextField();
		cajaResultado.setEditable(false);
		GridBagConstraints gbc_cajaResultado = new GridBagConstraints();
		gbc_cajaResultado.weightx = 0.4;
		gbc_cajaResultado.weighty = 0.25;
		gbc_cajaResultado.insets = new Insets(0, 15, 0, 20);								//Con esto conseguimos que se separe 20 px del borde derecho
		gbc_cajaResultado.gridx=3;
		gbc_cajaResultado.gridy=4;
		gbc_cajaResultado.gridheight=1;
		gbc_cajaResultado.gridwidth=1;
		gbc_cajaResultado.fill=GridBagConstraints.HORIZONTAL;
		add(cajaResultado, gbc_cajaResultado);
		
		//Botones 
		JButton botonEmpezar = new JButton();
		botonEmpezar.setText("Empezar");
		GridBagConstraints gbc_botonEmpezar = new GridBagConstraints();
		gbc_botonEmpezar.weightx = 0.1;
		gbc_botonEmpezar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonEmpezar.gridx=1;
		gbc_botonEmpezar.gridy=3;
		gbc_botonEmpezar.gridheight=1;
		gbc_botonEmpezar.gridwidth=1;
		add(botonEmpezar, gbc_botonEmpezar);
		
		JButton botonComprobar = new JButton();
		botonComprobar.setText("Comprobar");
		GridBagConstraints gbc_botonComprobar = new GridBagConstraints();
		gbc_botonComprobar.weightx = 0.1;
		gbc_botonComprobar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonComprobar.gridx=1;
		gbc_botonComprobar.gridy=4;
		gbc_botonComprobar.gridheight=1;
		gbc_botonComprobar.gridwidth=1;
		add(botonComprobar, gbc_botonComprobar);
		
		
		
	}

}
