package Principal;
import Vista.Acceso;
import Vista.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		VentanaPrincipal vPrincipal = new VentanaPrincipal();
		Acceso vAcceso = new Acceso(vPrincipal);									//vPrincipal de parámetro para poder mostrarla desde vLogin
		vAcceso.setVisible(true);
		
		vPrincipal.setAcceso_ventanaPrincipal(vAcceso);								//Para poder volver a mostrar la ventana de acceso desde la principal
	}	

}
