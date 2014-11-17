package estructuras.arbol;

import dominio.clases.Apicultor;
import dominio.clases.IKey;

public interface IABB {
	//Post.: Inserta el objeto "o" pasado como parámetro en el árbol, manteniendo el orden de árbol binario de búsqueda.
	//Si el objeto "o“ ya pertenece al árbol, la operación no tiene efecto.
	public void insertar(IKey o);

	//Post.: Borra el objeto "o“ pasado como parámetro del árbol.
	public void eliminar(IKey o);
	
	//Post.: Retorna el nodo raíz del árbol.
	public NodoABB getRaiz();

	//Post.: Imprime los elementos del árbol en PostOrder.
	public void imprimirPostOrder();

	//Post.: Imprime los elementos del árbol en InOrder.
	public void imprimirInOrder();

	//Post.: Imprime los elementos del árbol en PreOrder.
	public void imprimirPreOrder();

	//Post.: Retorna true si y solo si el árbol es vacío.
	public boolean isEmpty();

	public boolean existeElemento(IKey objeto);

	public String getInOrder();

	public Object buscar(IKey objeto);

	public void vaciar();
}
