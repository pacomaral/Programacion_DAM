
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class Conectar_AD {
	
	private static Scanner teclado = new Scanner(System.in);
	private static String usuario;
	private static String contrasenya;
	
	public static void main(String[] args) {

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, "ldap://10.2.72.112");		//IP de nuestro controlador de dominio
		
		//Pedimos usuario y contraseña
		System.out.println("Introduce tu usuario y el dominio (usuario@dominio.com");
		usuario = teclado.nextLine();
		System.out.println("Introduce tu contraseña");
		contrasenya = teclado.nextLine();
		
		
		//Rellenamos con el usuario/dominio y password
		env.put(Context.SECURITY_PRINCIPAL, usuario);
		env.put(Context.SECURITY_CREDENTIALS, contrasenya);

		DirContext ctx;

		try {
			//Conectamos
			ctx = new InitialDirContext(env);
			System.out.println("Autenticado con éxito");			
			ctx.close();

		} catch (NamingException ex) {
			//Si no se ha podido conectar
			System.out.println("Error de autenticación");
		}
			

	}

}