import Visual.Login;
import Visual.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		VentanaPrincipal v1 = new VentanaPrincipal();	//*Se podr�a crear en el constructor 
														//*del JFrame Login tambi�n (otro m�todo)
		
		Login login = new Login(v1);
		login.setVisible(true);

		
	}

}
