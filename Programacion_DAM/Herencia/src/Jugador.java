
public class Jugador extends Personaje {		//Es una clase hijo de Personaje

	//PROPIEDADES NUEVAS
	private int puntos = 0;
	
	public Jugador() {
		
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public void addPuntos(){
		this.puntos = this.puntos + 1;
	}

}
