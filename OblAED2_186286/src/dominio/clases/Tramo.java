package dominio.clases;

import estructuras.grafo.*;

public class Tramo implements IArco{ // no exstiende punto mapa

	private int kilometros;
	
	
	public Tramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int kilometros){
		this.kilometros = kilometros;
	}
	
	@Override
	public int getPeso(){
		return kilometros;
	}
}
