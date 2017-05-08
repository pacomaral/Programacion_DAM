
public class UsandoString {

	public static void main(String[] args) {
		
		//Generamos dos variables String
		String s1 = "";
		String s2 = "Paco";
		
		//Igualar
		s1 = s2;
		
		//Modificar;
		s1 = s1 + " es un crack";
		
		//Longitud de un string + ejemplo de uso
		System.out.println(s1);
		System.out.println(s2);
		System.out.println("Longitud de s1: " + s1.length());					//con nombreString.length() obtenemos en un int la longitud de la cadena de texto
		System.out.println("Longitud de s2: " + s2.length());
		
		if (s1.length() == 0){
			System.out.println("La variable s1 no contiene ningún carácter."); 	//Útil para comprobar si la cadena s1 está vacía
		}
		
		//Diferencia entre String literal [String s2 = "Paco";] y String objeto:
		s1=s2;
		String s3 = new String("Nombre");										//Son objetos diferentes aunque las cadenas sean iguales
		String s4 = new String("Nombre");										//Se utilizará cuando necesitemos crear Strings "durante" un programa
		System.out.println("--------------");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		if (s1 == s2){															//
			System.out.println("s1 = s2");										//
		}																		//Principal diferencia entre los 2 tipos (ver resultado por consola)
		if (s3 == s4){															//
			System.out.println("s3 = s4");										//
		}
		
		if (s3.compareTo(s4) == 0){												//string1.compareTo(string2) nos devuelve un int con el numero de carácteres diferentes
			System.out.println("El contenido de s3 y s4 es el mismo");	 		//Se haría así para comprobar si el texto es igual
		}
		
		/** 
		 * 		Para ver otros muchos métodos muy útiles de la clase String, visitar la documentación de Oracle:
		 * 
		 *			http://docs.oracle.com/javase/8/docs/api/java/lang/String.html
		 *	
		 **/

	}

}
