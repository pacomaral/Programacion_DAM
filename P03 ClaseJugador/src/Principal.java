import Jugador.Jugador;
import Visual.Ventana;

public class Principal {

	public static void main(String[] args) {
		
		//Generamos la ventana que se utilizará en el próximo proyecto04
		Ventana v1 = new Ventana();
		v1.setVisible(true);

		//Generamos un Jugador con sus respectivas propiedades
		Jugador jug1 = new Jugador();
		jug1.setNombre("Paco");
		jug1.setApellidos("Maravilla Aliaga");
		jug1.setApodo("pacomaral");
		jug1.setEdad(19);
			//Imprimimos mensaje con algunos datos del nuevo objeto para comprobar que se ha creado correctamente (además habrá otro mensaje dentro del constructor de la clase)
		System.out.println("Su apodo en el juego es " + jug1.getApodo() + " y tiene " + jug1.getEdad() + " años");  	
	}																														

}
