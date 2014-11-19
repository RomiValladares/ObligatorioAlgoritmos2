package estructuras.grafo;

public class ArcoGrafo {

	public boolean existe;
	private int peso;
	IArco dato;

	public ArcoGrafo() {
		this.existe = false;
		this.peso = 0;
	}
	
	public ArcoGrafo(int peso) {
		this.existe = true;
		this.peso = peso;
	}
	
	public int getPeso(){
		return dato == null ? peso: dato.getPeso();
	}
}