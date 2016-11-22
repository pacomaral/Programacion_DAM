import java.util.Random;

public class UsandoArrays_4 {

	public static void main(String[] args) {
		//Creamos un generador de numeros pseudoaleatorios
		Random rand = new Random(); 
		
		//Creamos el array de figuras
		Figura[] arrayfiguras2 = new Figura[10];
		
		//Elemento figura temporal (?¿)
		Figura f;
				
		//Inicializamos/rellenamos el array
		for(int i=0; i<arrayfiguras2.length; i++){
			arrayfiguras2[i] = new Figura();
			f = arrayfiguras2[i];
			f.setAltura(rand.nextInt(100));			//Asignamos a cada figura del array una altura aleatoria entre 0 y 100
			f.setAnchura(rand.nextInt(100));		//Asignamos a cada figura del array una anchura aleatoria entre 0 y 100
		}
		
		//Imprimimos anchura y altura de cada figura
		for(int i=0; i<arrayfiguras2.length; i++){
			f = arrayfiguras2[i];
			System.out.println("La figura " + i + " tiene una altura de " + f.getAltura() + " y una anchura de " + f.getAnchura());
		}
	}

}
