
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private Connection conexion;
	
	private String host;
	private String dbName;
	private String user;
	private String pass;
	private String url;
	
	//Constructor
	public ConexionBD(String host, String dbName, String user, String pass){
		this.host=host;
		this.dbName=dbName;
		this.user=user;
		this.pass=pass;
		this.url="jdbc:mysql://"+this.host+"/"+this.dbName;
	}
	
	//Método para conectar
	public boolean conectarBD(){									
		try{
			Class.forName(DRIVER);																//Cargamos el driver
			conexion = DriverManager.getConnection(this.url, this.user, this.pass);				//Nos conectamos
			System.out.println("Conectados a la BD");
		}
		catch(ClassNotFoundException noEncuentraClase){											//No ha encontrado el driver para la conexión
			noEncuentraClase.printStackTrace();
			return false;
		}
		catch(SQLException excepcionSQL){														//No ha encontrado la BBDD
			excepcionSQL.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void finalizarConexion(){
		try{
			conexion.close();
			System.out.println("Conexión finalizada");
		}
		catch (SQLException excepcion){															//Si no ha podido cerrar la conexión, es que no estábamos conectados
			System.out.println("No se ha iniciado ninguna conexión");
			excepcion.printStackTrace();
		}
	}

}
