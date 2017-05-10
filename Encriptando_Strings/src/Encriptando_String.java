/**
 *  Necesario importar librería commons-codec-1.10.jar
 */

import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

public class Encriptando_String {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introduce una cadena:");
		String cadena1 = teclado.nextLine();
		String cadena1_cifrada = DigestUtils.md5Hex(cadena1);
		System.out.println("Cadena cifrada :" + cadena1_cifrada);
		
		System.out.println("Introduce otra cadena:");
		String cadena2 = teclado.nextLine();
		String cadena2_cifrada = DigestUtils.md5Hex(cadena2);
		System.out.println("Cadena cifrada :" + cadena2_cifrada);
		
	}

}
