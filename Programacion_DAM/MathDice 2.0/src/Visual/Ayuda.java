package Visual;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Insets;
import java.awt.ScrollPane;

public class Ayuda extends JPanel {
	
	private JTextArea areaTexto;
	private JScrollPane scrollPane;
	
	
	/**
	 * Constructor
	 */
	public Ayuda() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		areaTexto = new JTextArea();
		areaTexto.setWrapStyleWord(true);
		areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));
		areaTexto.setLineWrap(true);
		areaTexto.setForeground(Color.WHITE);
		areaTexto.setText("MODO F\u00C1CIL:\r\n\r\nDeberemos ir sumando o restando los dados hasta que obtengamos el valor del dado de 12 caras.\r\n\r\n\r\nMODO DIF\u00CDCIL:\r\n\r\nPara empezar a jugar deberemos presionar el bot\u00F3n de empezar, de esta manera se activar\u00E1 el temporizador y tendremos un minuto para obtener la puntuaci\u00F3n m\u00E1s alta que podamos.\r\n\r\nEl funcionamiento es similar; tenemos que ir combinando operaciones (suma, resta, multiplicaci\u00F3n, divisi\u00F3n), de manera que obtengamos el resultado de multiplicar el valor del dado de 12 caras, y el otro dado (que tiene valor 3). \r\n\r\n- Hay que tener en cuenta que no se tienen en cuenta los criterios de prioridad aritm\u00E9ticos; es decir, las operaciones se realizan conforme se van haciendo.\r\n\r\nUn ejemplo:\r\n2+3x2 -> En lugar de hacer 3x2 primero, se hacen las operaciones de izquierda a derecha, de manera que el resultado ser\u00EDa 10 y no 8");
		areaTexto.setBackground(Color.DARK_GRAY);
		
		scrollPane = new JScrollPane(areaTexto);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		add(scrollPane, gbc_scrollPane);
		
	}

}
