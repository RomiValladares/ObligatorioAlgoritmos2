package estructuras.grafo;

public class ArcoGrafo implements IArco {

	public boolean existe;
	private int peso;

	public ArcoGrafo() {
		this.existe = false;
		this.peso = 0;
	}

	public ArcoGrafo(int peso) {
		this.existe = true;
		this.peso = peso;
	}
	
	public int getPeso(){
		return peso;
	}
}