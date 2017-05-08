package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";											//Driver necesario
	
	//Conexi�n	
	private Connection conexion;																			//Con esto manejaremos nuestra conexi�n
	
	//Instancia �nica (al ser static) de esta clase (NECESARIO PARA UTILIZAR EL PATRON SINGLETON)
	private static ConexionBD instance = null;																//La iniciamos en null						
	
	//Datos necesarios para la conexi�n
	private String host;
	private String dbName;
	private String user;
	private String pass;
	private String url;
	
	//Constructor, ser� privado para poder acceder a �l s�lo desde un m�todo de esta clase (NECESARIO PARA UTILIZAR EL PATRON SINGLETON)
	private ConexionBD(String host, String dbName, String user, String pass){
		this.host=host;
		this.dbName=dbName;
		this.user=user;
		this.pass=pass;
		this.url="jdbc:mysql://"+this.host+"/"+this.dbName;
	}
	
	//M�todo para implementar el patr�n SINGLETON
	public static ConexionBD getInstance(String host, String bbdd, String user, String pass){
		if(instance == null){
			instance = new ConexionBD(host, bbdd, user, pass);									//S�lo crear� el nuevo objeto en caso de que no haya sido creado previamente (= null)
		}
		return instance;
	}
	
	//M�todo para conectar
	public boolean conectarBD(){									
		try{
			Class.forName(DRIVER);																//Cargamos el driver
			conexion = DriverManager.getConnection(this.url, this.user, this.pass);				//Nos conectamos
		}
		catch(ClassNotFoundException noEncuentraClase){											//No ha encontrado el driver para la conexi�n
			noEncuentraClase.printStackTrace();
			return false;
		}
		catch(SQLException excepcionSQL){														//No ha encontrado la BBDD
			excepcionSQL.printStackTrace();
			return false;
		}
		return true;
	}
	
	//M�todo para obtener la conexi�n establecida
	public Connection getConexion(){
		return this.conexion;
	}

}
