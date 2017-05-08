package Logica;

public class Jugador {

	//PROPIEDADES
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String apodo;
	private int edad;
	private int puntuacionMax=0;
	
	//M�TODOS (getters y setters de cada propiedad de momento)
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getApodo() {
		return apodo;
	}
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getPuntuacionMax(){
		return puntuacionMax;
	}
	
	public void setPuntuacionMax(int num){
		this.puntuacionMax=num;
	}
	
	//CONSTRUCTOR (c�digo que se ejecuta cada vez que creemos un objeto de la clase Jugador
	
	public Jugador() {
		System.out.println("Creado un jugador");																		
	}
	
	/**
	 *  M�TODOS
	 */
	
	//NOS MUESTRA INFORMACION DEL JUGADOR CREADO
	public String infoJugador(){			
		return "NUEVO JUGADOR.        Nombre: " + this.getNombre() +  "  ||  Edad: " + this.getEdad() + " a�os.";
	}
	
	//CREA UN APODO PARA EL JUGADOR
	public void crearApodo(){
		
		try {
			
		String apodoCreado;
				
		//Creamos los arrays con los car�cteres de las propiedades en min�scula
		char[] array_nombre = nombre.toLowerCase().toCharArray();
		char[] array_apellido1 = apellido1.toLowerCase().toCharArray();
		char[] array_apellido2 = apellido2.toLowerCase().toCharArray();
				
		//Los mostramos por pantalla
		//for(int i=0; i<array_nombre.length; i++){
		//			System.out.println("[" + i + "]" + " = " + array_nombre[i]);
		//}
		//System.out.println("---------");
		//for(int i=0; i<array_apellidos.length; i++){
		//	System.out.println("[" + i + "]" + " = " + array_apellidos[i]);
		//}
				
		//Creamos nuevo array con los 2 primeros valores de cada array (2 primeras letras de nombre, 2 primeras de apellido1, y 2 primeras de apellido2)
		char[] array_apodo = new char[6];
				
		//Rellenamos manualmente con los car�cteres que queremos
		array_apodo[0] = array_nombre[0];
		array_apodo[1] = array_nombre[1];
		array_apodo[2] = array_apellido1[0];
		array_apodo[3] = array_apellido1[1];
		array_apodo[4] = array_apellido2[0];
		array_apodo[5] = array_apellido2[1];
				
		//Imprimimos el tercer array
		//System.out.println("-----------");
		//for(int i=0; i<array_apodo.length; i++){
		//	System.out.println("[" + i + "]" + " = " + array_apodo[i]);
		//}
				
		//Asignamos el tercer array al String apodoCreado (Ser�n los 2 primeros car�cteres del nombre, los 2 del primer apellido, los 2 del segundo, mas la edad.
		apodoCreado = String.valueOf(array_apodo) + String.valueOf(this.getEdad());
		//System.out.println("El apodo es: " + apodo);	
		
		//Asignamos el nuevo apodo a la propiedad Apodo del jugador
		this.setApodo(apodoCreado);
		
		}
		catch(Exception e){						//En caso de que ocurra algo creando el apodo, asignaremos el nombre como apodo. (Puede ocurrir un error cuando								
			this.setApodo(nombre);				//se introduc�a una cadena de un car�cter s�lo, ya que el array no podr�a coger dos car�cteres)
		}
	}
	

	

	
}
	

