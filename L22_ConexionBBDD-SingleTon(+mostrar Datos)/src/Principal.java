import java.sql.Connection;

public class Principal {
	
	private static ConexionBD conexionbd1, conexionbd2;
	private static boolean connected1, connected2;
	private static Connection conexion1, conexion2;
	private static Control_BD controlBD;

	public static void main(String[] args) {
		
		//Generamos el objeto usuariosDB
		conexionbd1 = ConexionBD.getInstance("84.126.92.105:3306", "usuarios", "root", "asdasd");					//Esto creará el objeto y la instancia de ConexionBD
		conexionbd2 = ConexionBD.getInstance("84.126.92.105:3306", "usuarios", "root", "asdasd");					//En esta ocasión, al ya no ser la instancia 'null', 
																													//el método getInstance devolverá la misma instancia creada anteriormente, 
																													//por lo que nunca tendremos 2 conexiones.
		
		//Establecemos la conexión (es indiferente que lo hagamos con las "2 conexiones", ya que las 2 utilizan la misma instancia)
		connected1 = conexionbd1.conectarBD();
		connected2 = conexionbd2.conectarBD();
		
		//Obtenemos las 2 conexiones y comprobamos que en realidad ES LA MISMA
		conexion1 = conexionbd1.getConexion();
		conexion2 = conexionbd2.getConexion();
		if(conexion1 == conexion2){
			System.out.println("Sólo tenemos una conexión. \n");
		}
		
		//Si nos hemos conectado, mostraremos lo que queramos 
		if(connected2){
			System.out.println("Conectados a la BBDD. \n");
			System.out.println("LISTADO DE JUGADORES: \n");
			controlBD = new Control_BD (conexion1);											//Da igual si pasamos conexion1 o 2, ya que son la misma
			controlBD.mostrarDatosJugadores();												//Mostramos la información de todos los jugadores	
		}
		else{
			System.out.println("Error en la conexión");
		}

	}

}
