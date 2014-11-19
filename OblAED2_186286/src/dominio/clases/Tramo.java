package dominio.clases;

import estructuras.grafo.*;
import estructuras.hash.HashTablaAbierta;

public class Tramo implements IArco{ // no exstiende punto mapa

	private int kilometros;
	
	private Object inicio;
	
	private Object fin;
	
	public Tramo(Double pCoordXi, Double pCoordYi, Double pCoordXf, Double pCoordYf, int kilometros, HashTablaAbierta pHashTabla){
		
		this.inicio = pHashTabla.encontrar(new PuntoMapa(pCoordXi, pCoordYi)); // PUNTERO al inicio
		this.fin = pHashTabla.encontrar(new PuntoMapa(pCoordXf, pCoordYf)); // PUNTERO al final
		
		this.kilometros = kilometros;
	}
	
	@Override
	public int getPeso(){
		return kilometros;
	}
}