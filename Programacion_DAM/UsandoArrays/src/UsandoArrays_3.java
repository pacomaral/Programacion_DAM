
public class UsandoArrays_3 {

	public static void main(String[] args) {
		
		/**
		 * Creación de un array de objetos (Podremos crear arrays de distintos objetos, pero DE LA MISMA CLASE)
		 */
		
		// Se crearía así: Objeto[] nombre = new Objeto[n]   (n = numero de objetos que tendrá el array
		Figura[] arrayfiguras = new Figura[10];
		
		/*inicializamos manualmente (cada elemento del array será un objeto de la clase figura)
		
		Figura f;
		arrayfiguras[0] = new Figura();
		f = arrayfiguras[0];													//ESTO ES PARA INICIALIZAR MANUALMENTE
		f.setAltura(5);
		System.out.println("El alto de la figura es:" + f.getAltura());
		
		*/
		
		//Figura temporal (se utilizará para rellenar el array con el for)
		Figura f;
		
		//Inicializando con for
		for(int i=0; i<arrayfiguras.length; i++){		//CON ESTO RELLENAMOS LOS ELEMENTOS DEL ARRAY CON OBJETOS DE LA CLASE FIGURA
			arrayfiguras[i] = new Figura();
			f = arrayfiguras[i];
		}
		
	}

}
