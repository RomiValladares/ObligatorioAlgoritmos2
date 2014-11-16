package dominio.clases;

import dominio.Sistema.TipoPunto;

public class Centro extends PuntoMapa {
	private int capacidadOriginal, capacidadRemanente;
	private String nombre;

	public Centro(Double coordX, Double coordY, String nombre, int capacidad) {
		super(coordX, coordY, TipoPunto.CENTRO_EXTRACCION);
		this.capacidadOriginal = capacidad;
		this.capacidadRemanente = capacidad;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		String sepCampos = getSeparadorCampos();
		return super.toString() + sepCampos + capacidadOriginal + sepCampos + capacidadRemanente;
	}
}
