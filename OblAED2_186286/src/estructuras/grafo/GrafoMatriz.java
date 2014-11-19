package estructuras.grafo;

import dominio.clases.PuntoMapa;
import estructuras.hash.HashTablaAbierta;
import estructuras.lista.ILista;
import estructuras.lista.Lista;

/*
 * Ventajas
 ** Simplicidad.
 ** Chequear si una arista está presente es inmediato.
 *Desventajas
 **Consume VxV de espacio de memoria.
 **Si el grafo no es denso la representación no parece tan adecuada.
 * */
public class GrafoMatriz implements IGrafo {

	int size;
	int cantNodos;
	ArcoGrafo[][] matrizAdyacencia;
	boolean[] nodosUsados;
	Object[] listaVertices;

	// Crea el grafo vacio (sin nodos ni aristas) con capacidad de
	// almacenamiento de n vértices
	public GrafoMatriz(int cantNodos) {
		this.size = 0;
		this.cantNodos = cantNodos;

		this.matrizAdyacencia = new ArcoGrafo[cantNodos][cantNodos];
		for (int i = 1; i < cantNodos; i++)
			for (int j = 1; j < cantNodos; j++)
				this.matrizAdyacencia[i][j] = null;

		this.nodosUsados = new boolean[cantNodos];
		this.listaVertices = new Object[cantNodos];
	}

	public void agregarArista(int origen, int destino, int peso) {
		ArcoGrafo nuevo = new ArcoGrafo(peso);
		this.matrizAdyacencia[origen][destino] = nuevo;
	}
	
	public boolean existeArista(int origen, int destino) {
		if(this.matrizAdyacencia[origen][destino] != null)
			return true;
		else
			return false;
	}

	public void agregarVertice(Object dato) {
		listaVertices[size] = dato;
		this.nodosUsados[size] = true;
		this.size++;
	}
	
	public int obtenerPosVertice(Object dato) {
		int pos = -1; // careful
		
		for(int i = 0; i<this.listaVertices.length; i++){
			if(dato == listaVertices[i]){
				pos = i;
				return pos;
			}
		}
		
		return pos;
	}

	public void eliminarArista(int origen, int destino) {
		ArcoGrafo nuevo = new ArcoGrafo();
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	public void eliminarVertice(int v) {
		this.nodosUsados[v] = false;
		this.size--;

		// Elimino las aristas donde v es miembro
		for (int i = 1; i <= this.cantNodos; i++) {
			this.matrizAdyacencia[i][v] = null;
			this.matrizAdyacencia[v][i] = null;
		}
	}

	public boolean esVacio() {
		return this.size == 0;
	}

	public boolean sonAdyacentes(int a, int b) {
		return this.matrizAdyacencia[a][b].existe;
	}

	public ILista verticesAdyacentes(int v) {
		ILista l = new Lista();
		for (int i = 1; i <= this.cantNodos; i++) {
			if (this.sonAdyacentes(v, i)) {
				l.insertarInicio(i);
			}
		}
		return l;
	}

	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

}
