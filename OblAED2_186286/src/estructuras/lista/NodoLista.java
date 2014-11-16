package estructuras.lista;

public class NodoLista {
	private int dato;
	private NodoLista sig;
	private Object obj;

	// Constructor
	public NodoLista(int n) {
		this.dato = n;
		this.sig = null;
	}

	// Constructor
	public NodoLista(Object obj) {
		this.obj = obj;
		this.sig = null;
	}

	// Dato
	public void setDato(int d) {
		this.dato = d;
	}

	public int getDato() {
		return this.dato;
	}

	// Siguiente
	public void setSig(NodoLista s) {
		this.sig = s;
	}

	public NodoLista getSig() {
		return this.sig;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
