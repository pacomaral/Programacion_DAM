
public class UsandoArrays_1 {

	public static void main(String[] args) {
		
		/**
		 * Creando todas las calificaciones = MÉTODO MÁS LARGO SI HAY MUCHAS NOTAS
		 */
		
		int nota1=0, nota2=4, nota3=6, nota4=9;
		float media;
		
		media = (nota1 + nota2 + nota3 + nota4) / 4;
		
		/**
		 * Creando un array para las calificaciones (más cómodo para muchas notas) = MÉTODO MÁS RÁPIDO Y UTIL
		 */
		
		//Creamos el array (supongamos que tenemos 10 notas)
		int[] calificaciones = new int[10];
		
		//Rellenándolo manualmente (más adelante veremos como automatizarlo)
		calificaciones[0]=0;
		calificaciones[1]=4;
		calificaciones[2]=6;
		calificaciones[3]=7;
		calificaciones[4]=8;
		calificaciones[5]=4;
		calificaciones[6]=5;
		calificaciones[7]=7;
		calificaciones[8]=6;
		calificaciones[9]=9;
		
		//Calculando la media del array de calificaciones						(calificaciones.length nos devuelve el numero de elementos que tiene el array
		float media_array=0;
		
		for(int i=0; i<calificaciones.length; i++){								//Sumamos todas las notas del array con este FOR (NUNCA hacer i<=array.lenght)
			media_array = media_array + calificaciones[i];
			System.out.println("Suma de notas: " + media_array);
		}
		
		media_array = media_array / calificaciones.length;						//Después de haber sumado todas las notas del array con el FOR anterior, sacamos la media
		System.out.println("Nota media: " + media_array);
		
	}

}
