package Controlador;
import org.apache.commons.codec.digest.DigestUtils;

public class Encriptacion {
	
	//Atributos
	private String resultado;
	
	//Constructor
	public void Encriptacion(){
		
	}
	
	//Métodos
	public String encriptarString(String cadena){
		resultado = DigestUtils.md5Hex(cadena);											//Encriptamos la cadena que queramos
		return resultado;																//Devolvemos el resultado
	}
}
