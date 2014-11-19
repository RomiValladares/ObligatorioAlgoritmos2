package estructuras.grafo;

public class VerticeGrafo {
	//
	private int peso;
	//
	private int origen;
	private int destino;
	
	//
	private Object dato;	
	//
	
	public VerticeGrafo(int peso, int origen, int destino, Object dato) {
		super();
		this.peso = peso;
		this.origen = origen;
		this.destino = destino;
		this.dato = dato;
	}
	/**/
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	/**/
	
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public Object getDato() {
		return dato;
	}
	public void setDato(Object dato) {
		this.dato = dato;
	}
}
