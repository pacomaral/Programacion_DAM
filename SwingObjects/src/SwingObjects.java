
public class SwingObjects{


	/**
	 * Punto de entrada a nuestra aplicaci�n
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenidos a mi programa de ventanas");
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.cambiarMsg("Paco probando 123 123");
		ventana.setVisible(true);
	}
		
	/**
	 * Creaci�n y configuraci�n de nuestra ventana
	 */
	public SwingObjects() {
	
	}

}
