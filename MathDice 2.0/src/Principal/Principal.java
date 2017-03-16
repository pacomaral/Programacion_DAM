package Principal;
import Visual.EditarApodo;
import Visual.VentanaLogin;
import Visual.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		//Generamos ventanas necesarias
		//VentanaJuego_dificil v4 = new VentanaJuego_dificil();
		//VentanaJuego v3 = new VentanaJuego();
		//EditarApodo v2 = new EditarApodo(v3, v4);
		//VentanaLogin v1 = new VentanaLogin(v2);
		
		
		//Hacemos visible la primera ventana
		//v1.setVisible(true);
		
		//Método para importar el objeto v1 en v2 y poder utilizar sus métodos
		//v2.setV1(v1);
		
		
		
		//EditarApodo vApodo = new EditarApodo(vJuegoFacil, vJuegoDificil);
		VentanaPrincipal vPrincipal = new VentanaPrincipal();
		EditarApodo vApodo = new EditarApodo(vPrincipal);
		VentanaLogin vLogin = new VentanaLogin(vApodo);									//vApodo de parámetro para poder mostrarla desde vLogin
		vLogin.setVisible(true);
	}	

}
