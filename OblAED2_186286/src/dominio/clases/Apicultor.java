package dominio.clases;

public class Apicultor implements IKey {
	private String cedula;
	private String nombre;
	private String dirección;
	private String email;
	private String celular;

	private Apiario apiario;

	private static String separadorCamposToString = ";";

	public Apicultor(String cedula, String nombre, String dirección, String email, String celular) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.dirección = dirección;
		this.email = email;
		this.celular = celular;
	}

	public Apicultor(String cedula) {
		super();
		this.cedula = cedula;
	}

	@Override
	public Object getKey() {
		return cedula;
	}

	@Override
	public int compareTo(IKey otro) {
		return cedula.compareTo(otro.getKey().toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Apicultor otro = (Apicultor) obj;
			return otro.getKey() == this.getKey();
		}
		return false;
	}

	@Override
	public String toString() {
		return cedula + separadorCamposToString + nombre + separadorCamposToString + celular;
	}

	public void setApiario(Apiario nuevoApiario) {
		this.apiario = nuevoApiario;
	}
}
