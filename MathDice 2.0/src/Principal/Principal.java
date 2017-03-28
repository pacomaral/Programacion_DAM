package Principal;
import Visual.EditarApodo;
import Visual.VentanaLogin;
import Visual.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		VentanaPrincipal vPrincipal = new VentanaPrincipal();
		EditarApodo vApodo = new EditarApodo(vPrincipal);
		VentanaLogin vLogin = new VentanaLogin(vApodo);									//vApodo de parámetro para poder mostrarla desde vLogin
		vLogin.setVisible(true);
	}	

}
