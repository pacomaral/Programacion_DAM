package Jugador;

public class Jugador {

	//PROPIEDADES
	
	private String nombre;
	private String apellidos;
	private String apodo;
	private int edad;
	
	//MÉTODOS (getters y setters de cada propiedad de momento)
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	
	//CONSTRUCTOR (código que se ejecuta cada vez que creemos un objeto de la clase Jugador
	
	public Jugador() {
		System.out.println("Creado un jugador");																		
	}
	
}
