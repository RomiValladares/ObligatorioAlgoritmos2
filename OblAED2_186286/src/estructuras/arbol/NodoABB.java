package estructuras.arbol;

import dominio.clases.IKey;

/* Nodo para el self-balanced binary search tree */
class NodoABB {
	NodoABB izq, der;
	IKey dato;
	int altura;

	public NodoABB() {
		izq = null;
		der = null;
		dato = null;
		altura = 0;
	}

	public NodoABB(IKey x) {
		izq = null;
		der = null;
		dato = x;
		altura = 0;
	}

	public NodoABB getIzq() {
		return this.izq;
	}

	public NodoABB getDer() {
		return this.der;
	}

	public IKey getDato() {
		return this.dato;
	}

}