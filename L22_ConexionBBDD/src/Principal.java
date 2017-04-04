
public class Principal {

	private static boolean connected = false;
	
	public static void main(String[] args) {
		
		//Creamos el objeto
		ConexionBD conexiondb = new ConexionBD("localhost:3306", "jugadores", "root", "asdasd");
		
		//Nos conectamos y lo asignamos a la variable booleana connected (ya que nos devolver� true o false)
		connected = conexiondb.conectarBD();
		
		//Comprobamos si estamos conectados
		if(connected){
			System.out.println("Conectados a la BBDD con �xito");
		}
		else{
			System.out.println("No ha sido posible la conexi�n");
		}
		
		//Nos desconectamos de la BBDD
		conexiondb.finalizarConexion();
	}

}
