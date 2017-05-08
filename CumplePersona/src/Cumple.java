
public class Cumple {
	
	private String nombre;	
	private int edad;
	private int dia;		//entre 1 y 31
	private int mes;		//entre 1 y 12
	private int año;		//entre 1900 y 2016
	
	public Cumple() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		//RESTRINGIMOS LA ENTRADA DE DIA DE 1 A 31
		if (dia < 0) {
			this.dia = 1;
		}
		else if (dia > 31) {
			this.dia = 31;
		}
		else {
			this.dia = dia;
		}
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		if (mes < 1) {
			this.mes = 1;
		}
		else if (mes > 12) {
			this.mes = 12;
		}
		else {
		this.mes = mes;
		}
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		if (año < 1900) {
			this.año = 1900;
		}
		else if(año > 2016) {
			this.año = 2016;
		}
		else {
		this.año = año;
		}
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		if (edad < 0) {
			this.edad = 1;
		}
		else {
		this.edad = edad;
		}
	}
	

}
