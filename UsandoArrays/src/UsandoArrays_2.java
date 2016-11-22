
public class UsandoArrays_2 {

	public static void main(String[] args) {
		//definimos array1
		int[] array1 = new int[5];
		//rellenamos manualmente
		array1[0]=3;
		array1[1]=3;
		array1[2]=4;
		array1[3]=9;
		array1[4]=6;
		
		/**
		 * Otra forma de definir y rellenar array (sigue siendo manualmente)
		 */
		
		//definimos y rellenamos
		int[] array2 = {3,3,4,9,6};
		
		//recorriendo array con bucle for
		for (int i=0; i<array2.length; i++){
			System.out.println(array2[i]);
		}

	}

}
