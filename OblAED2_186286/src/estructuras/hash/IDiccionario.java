package estructuras.hash;

import dominio.clases.IKey;

public interface IDiccionario {

	// PRE:
	// POST: Retorna TRUE si n es un elemento de diccionario
	public boolean pertenece(IKey n);

	// PRE:
	// POST: borra el elemento del diccionario
	public void borrar(IKey n);

	public void insertar(IKey n);

	public boolean isFull();

	String listarElementos(Class<?> claseElemento);

}
