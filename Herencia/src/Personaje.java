
public class Personaje {
	
	//PROPIEDADES
	private int vida=100;
	private int nivel=1;
	private String arma_actual="";
	

	public Personaje() {
		
	}


	public int getVida() {
		return vida;
	}


	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public void quitarVida(){
		this.vida = this.vida - 1;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public String getArma_actual() {
		return arma_actual;
	}


	public void setArma_actual(String arma_actual) {
		this.arma_actual = arma_actual;
	}

}
