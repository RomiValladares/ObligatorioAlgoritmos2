package dominio.clases;

import dominio.Sistema.TipoPunto;

public class Apiario extends PuntoMapa {
	private String nombre;
	private int capacidad;

	public Apiario(Double coordX, Double coordY, String nombre, int capacidad) {
		super(coordX, coordY, TipoPunto.APIARIO);
		this.nombre = nombre;
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return super.toString() + getSeparadorCampos() + nombre + getSeparadorCampos() + capacidad;
	}
}
