
public class Principal {

	public static void main(String[] args) {
		
		//Generamos un nuevo jugador
		Jugador player1 = new Jugador();
		
		player1.setPuntos(10);
		player1.setArma_actual("Espada");
		
		//Generamos un nuevo monstruo
		Monstruo ogro1 = new Monstruo();
		
		ogro1.setTipoAtaque("Morder");
		ogro1.setArma_actual("Dientes podridos");
		ogro1.setVida(5);
		
		//Definimos un personaje de forma general
		Personaje personaje1 = new Personaje();
		
		personaje1.setNivel(3);
		personaje1.setVida(100);
		// personaje1.setTipoAtaque("Patada");   NO SE PUEDE PORQUE ES UN METODO DE LA CLASE HIJO MONSTRUO, DE ESTAS CLASES HIJAS SI QUE PODEMOS UTILIZAR LOS DE PERSONAJE
		// personaje1.setPuntos(20);			 NO SE PUEDE PORQUE ES UN METODO DE LA CLASE HIJO JUGADOR, DE ESTAS CLASES HIJAS SI QUE PODEMOS UTILIZAR LOS DE PERSONAJE

		/**
		 *  Creamos una ventana
		 */
		Ventana1 v1 = new Ventana1();
		v1.setVisible(true);
	}

}
