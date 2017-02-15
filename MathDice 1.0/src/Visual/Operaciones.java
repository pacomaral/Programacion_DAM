package Visual;

public class Operaciones {
	
	private int resultado=0;
	
	//Constructor
	public Operaciones() {
		
	}
	
	//Métodos para las operaciones
	public void suma(int num){
		resultado=resultado+num;
	}
	public void resta(int num){
		resultado=resultado-num;
	}
	public void multiplicacion(int num){
		resultado=resultado*num;
	}
	public void division(int num){
		resultado=resultado/num;
	}
	
	//Método para acceder al resultado cuando lo necesitemos en la ventana juego
	public int getResultado(){
		return resultado;
	}
	
	//Método para resetear al resultado cuando lo necesitemos en la ventana juego
	public void resetResultado(){
		resultado=0;
	}

}
