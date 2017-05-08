package Modelo;

public class Jugador {

	//PROPIEDADES
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String usuario;
	private String contrasenya;
	private int edad;
	private int identificador;
	private int puntuacionMax=0;
	
	//MÉTODOS (getters y setters de cada propiedad de momento)
	
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
	
	public int getIdentificador(){
		return identificador;
	}
	
	public void setIdentificador(int id){
		this.identificador = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String pass) {
		this.contrasenya = pass;
	}
	
	
	
	//CONSTRUCTOR (código que se ejecuta cada vez que creemos un objeto de la clase Jugador
	
	public Jugador() {																		
	}
	
	/**
	 *  MÉTODOS
	 */
	
	//NOS MUESTRA INFORMACION DEL JUGADOR CREADO
	public String infoJugador(){			
		return "Acceso correcto. \n" + this.getNombre() + " " + this.getApellido1() + " | ID: " + this.getIdentificador() + " | Puntuación máxima: " + this.getPuntuacionMax();
	}
	
	
}
	

