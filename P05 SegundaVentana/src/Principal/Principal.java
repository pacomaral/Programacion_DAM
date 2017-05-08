package Principal;
import Juego.Jugador;
import Visual.EditarApodo;
import Visual.VentanaJuego;
import Visual.VentanaLogin;

public class Principal {

	public static void main(String[] args) {
		
		//Generamos ventanas necesarias
		VentanaJuego v3 = new VentanaJuego();
		EditarApodo v2 = new EditarApodo(v3);
		VentanaLogin v1 = new VentanaLogin(v2);
		
		
		//Hacemos visible la primera ventana
		v1.setVisible(true);
		
		//Método para importar el objeto v1 en v2 y poder utilizar sus métodos
		v2.setV1(v1);
		
	}	

}
