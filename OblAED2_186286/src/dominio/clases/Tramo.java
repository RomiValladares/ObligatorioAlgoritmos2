package dominio.clases;

import estructuras.grafo.*;
import estructuras.hash.HashTablaAbierta;

public class Tramo implements IArco{ // no exstiende punto mapa

	private int kilometros;
	
	private Object inicio;
	
	private Object fin;
	
	public Tramo(Object datoInicio, Object datoFinal, int kilometros){
		
		this.inicio = datoInicio; // PUNTERO al inicio
		this.fin = datoFinal; // PUNTERO al final
		
		this.kilometros = kilometros;
	}
	
	@Override
	public int getPeso(){
		return kilometros;
	}
}