package estructuras.grafo;

import estructuras.hash.HashTablaAbierta;
import estructuras.lista.ILista;

public interface IGrafo {
	// Pre: se agreg� un elemento
	// Post: Agrega un vertice al grafo
	public void agregarVertice(Object dato);
	
	// pre: el dato existe en la matriz
	// post: retorna la posicion (int) del dato en la matriz
	public int obtenerPosVertice(Object dato);
	
	// pre: origen y destino son posiciones validas
	// post: retorna true si la arista de origen origen y destino destino existe, 
	//en caso contraro retorna false
	public boolean existeArista(int origen, int destino);
	// Pre: origen y destino son los �ndices de v�rtices ya ingresados en el
	// grafo
	// Post: Agrega la arista origen-destino de peso "peso" en el grafo
	public void agregarArista(int origen, int destino, int peso);

	// Pre: El v�rtice v existe en el grafo
	// Post: Elimina el v�rtice y todas las aristas a las que pertenezca
	public void eliminarVertice(int v);

	// Pre: La arista origen - destino existe en el grafo
	// Post: Elimina la arista origen - destino
	public void eliminarArista(int origen, int destino);

	// Pre: El v�rtice v existe en el grafo
	// Post: Retorna una lista con los v�rtices adyacentes de v.
	// Si v no tiene adyacentes retorna la lista vac�a
	public ILista verticesAdyacentes(int v);

	// Pre: a y b son v�rtices del grafo
	// Post: Retorna true si los v�rtices a y b son adyacentes.
	public boolean sonAdyacentes(int a, int b);

	// Post: Retorna true si el v�rtice fue ingresado al grafo
	public boolean estaVertice(int v);

	// Post: Retorna true si el grafo esta vac�o
	public boolean esVacio();

}