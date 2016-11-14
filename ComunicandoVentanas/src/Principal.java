import Visual.Login;
import Visual.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		VentanaPrincipal v1 = new VentanaPrincipal();	//*Se podría crear en el constructor 
														//*del JFrame Login también (otro método)
		
		Login login = new Login(v1);
		login.setVisible(true);

		
	}

}
