package estructuras.lista;

import java.util.ArrayList;

public class Lista implements ILista {
	private NodoLista inicio;

	public Lista() {
		this.inicio = null;
	}

	@Override
	public void insertarInicio(Object n) {
		NodoLista nuevo = new NodoLista(n);
		nuevo.setSig(inicio);
		this.inicio = nuevo;
	}

	@Override
	public boolean esVacia() {
		return this.inicio == null;
	}

	@Override
	public void borrarInicio() {
		if (!this.esVacia()) {
			this.inicio = this.inicio.getSig();
		}
	}

	// PRE:
	// POS: elimina todos los nodos de una lista dada
	@Override
	public void vaciarLista() {
		while (inicio != null) {
			borrarInicio();
		}
	}

	// PRE: lista ordenada
	// POS: Borra la primer ocurrencia de un elemento dado
	@Override
	public boolean borrarElemento(Object n) {
		if (this.esVacia())
			return false;
		if (this.inicio.getObj().equals(n)) {
			this.borrarInicio();
			return true;
		} else {
			NodoLista aux = this.inicio;
			while (aux.getSig() != null && !aux.getSig().getObj().equals(n))
				aux = aux.getSig();
			// lo encontro o lleguo al final de la lista
			if (aux.getSig() != null) {
				NodoLista borrar = aux.getSig();
				aux.setSig(borrar.getSig());
				borrar.setSig(null);
				return true;
			}
			return false;
		}
	}

	@Override
	public Object obtenerElemento(Object n) {
		Object retorno = null;
		NodoLista aux = inicio;
		while (aux != null && !aux.getObj().equals(n)) {
			aux = aux.getSig();
		}
		if (aux != null && aux.getObj().equals(n))
			retorno = aux.getObj();
		return retorno;
	}

	public IteradorLista getIteradorLista() {
		return new IteradorLista(inicio);
	}

	public class IteradorLista {
		private NodoLista nodoActual;

		public IteradorLista(NodoLista inicio) {
			this.nodoActual = inicio;
		}

		public NodoLista proximoNodo() {
			if (nodoActual != null) {
				nodoActual = nodoActual.getSig();
				return nodoActual;
			}
			return null;
		}

		public NodoLista getNodoActual() {
			return nodoActual;
		}
	}

}