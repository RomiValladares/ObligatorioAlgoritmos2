package dominio.clases;

import estructuras.grafo.*;
import estructuras.hash.HashTablaAbierta;

public class Tramo implements IArco{ // no exstiende punto mapa

	private int kilometros;
	
	private Object dato1;
	
	private Object dato2;
	
	public Tramo(Double pCoordXi, Double pCoordYi, Double pCoordXf, Double pCoordYf, int kilometros, HashTablaAbierta pHashTabla){
		
		this.dato1 = pHashTabla.encontrar(new PuntoMapa(pCoordXi, pCoordYi));
		this.dato2 = pHashTabla.encontrar(new PuntoMapa(pCoordXf, pCoordYf));
		
		// con x e y obtengo un puntero y xf e yf obtengo otro puntero
		
		this.kilometros = kilometros;
	}
	
	@Override
	public int getPeso(){
		return kilometros;
	}
}