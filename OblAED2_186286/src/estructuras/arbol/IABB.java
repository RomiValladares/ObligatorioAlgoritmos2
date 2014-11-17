package estructuras.arbol;

import dominio.clases.Apicultor;
import dominio.clases.IKey;

public interface IABB {
	//Post.: Inserta el objeto "o" pasado como par�metro en el �rbol, manteniendo el orden de �rbol binario de b�squeda.
	//Si el objeto "o� ya pertenece al �rbol, la operaci�n no tiene efecto.
	public void insertar(IKey o);

	//Post.: Borra el objeto "o� pasado como par�metro del �rbol.
	public void eliminar(IKey o);
	
	//Post.: Retorna el nodo ra�z del �rbol.
	public NodoABB getRaiz();

	//Post.: Imprime los elementos del �rbol en PostOrder.
	public void imprimirPostOrder();

	//Post.: Imprime los elementos del �rbol en InOrder.
	public void imprimirInOrder();

	//Post.: Imprime los elementos del �rbol en PreOrder.
	public void imprimirPreOrder();

	//Post.: Retorna true si y solo si el �rbol es vac�o.
	public boolean isEmpty();

	public boolean existeElemento(IKey objeto);

	public String getInOrder();

	public Object buscar(IKey objeto);

	public void vaciar();
}
