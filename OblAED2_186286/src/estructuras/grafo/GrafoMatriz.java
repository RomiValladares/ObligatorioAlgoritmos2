package estructuras.grafo;

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

	// Crea el grafo vacio (sin nodos ni aristas) con capacidad de
	// almacenamiento de n vértices
	public GrafoMatriz(int cantNodos) {
		this.size = 0;
		this.cantNodos = cantNodos;

		this.matrizAdyacencia = new ArcoGrafo[cantNodos + 1][cantNodos + 1];
		for (int i = 1; i <= cantNodos; i++)
			for (int j = 1; j <= cantNodos; j++)
				this.matrizAdyacencia[i][j] = new ArcoGrafo();

		this.nodosUsados = new boolean[cantNodos + 1];
	}

	public void agregarArista(int origen, int destino, int peso) {
		ArcoGrafo nuevo = new ArcoGrafo(peso);
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	public void agregarVertice(int v) {
		this.nodosUsados[v] = true;
		this.size++;
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
			this.matrizAdyacencia[i][v] = new ArcoGrafo();
			this.matrizAdyacencia[v][i] = new ArcoGrafo();
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
