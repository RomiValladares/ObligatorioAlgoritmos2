package dominio.clases;

import dominio.Sistema.TipoPunto;

public class Ciudad extends PuntoMapa {
	private String nombre;

	public Ciudad(Double coordX, Double coordY, String nombre) {
		super(coordX, coordY, TipoPunto.CIUDAD);
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int compareTo(IKey obj) {
		int retorno = super.compareTo(obj);
		if (retorno != 0)
			return retorno;
		if (obj.getClass() != this.getClass())
			return obj.getKey().toString().compareTo(this.getKey().toString());
		Ciudad otroObj = (Ciudad) obj;
		return otroObj.getNombre().compareTo(getNombre());
	}

	@Override
	public String toString() {
		return super.toString() + getSeparadorCampos() + nombre;
	}

}
