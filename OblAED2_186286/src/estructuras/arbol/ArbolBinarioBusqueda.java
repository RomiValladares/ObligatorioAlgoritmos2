package estructuras.arbol;

import dominio.clases.IKey;

public class ArbolBinarioBusqueda implements IABB {
	private NodoABB raiz;

	public ArbolBinarioBusqueda() {
		setRoot(null);
	}

	public void setRoot(NodoABB root) {
		this.raiz = root;
	}

	@Override
	public boolean isEmpty() {
		return getRaiz() == null;
	}

	/* Make the tree logically empty */
	public void clear() {
		setRoot(null);
	}

	public void insertar(IKey data) {
		setRoot(insertar(data, getRaiz()));
	}

	/* Function to get height of node */
	private int altura(NodoABB t) {
		return t == null ? -1 : t.altura;
	}

	/* Function to max of left/right node */
	private int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;
	}

	/* Function to insert data recursively */
	private NodoABB insertar(IKey x, NodoABB t) {
		if (t == null) {
			t = new NodoABB(x);
		} // else if (x < t.data) {
		else if (x.compareTo(t.dato) < 0) {
			t.izq = insertar(x, t.izq);
			if (altura(t.izq) - altura(t.der) == 2)
				// if (x < t.left.data)
				if (x.compareTo(t.izq.dato) < 0)
					t = rotarConHijoIzquierdo(t);
				else
					t = rotarDobleConHijoIzquierdo(t);
		} else if (x.compareTo(t.dato) > 0) {
			t.der = insertar(x, t.der);
			if (altura(t.der) - altura(t.izq) == 2)
				if (x.compareTo(t.der.dato) > 0)
					t = rotarConHijoDerecho(t);
				else
					t = rotarDobleConHijoDerecho(t);
		} else
			; // Duplicate; do nothing
		t.altura = max(altura(t.izq), altura(t.der)) + 1;
		return t;
	}

	/* Rotate binary tree node with left child */
	private NodoABB rotarConHijoIzquierdo(NodoABB k2) {
		NodoABB k1 = k2.izq;
		k2.izq = k1.der;
		k1.der = k2;
		k2.altura = max(altura(k2.izq), altura(k2.der)) + 1;
		k1.altura = max(altura(k1.izq), k2.altura) + 1;
		return k1;
	}

	/* Rotate binary tree node with right child */
	private NodoABB rotarConHijoDerecho(NodoABB k1) {
		NodoABB k2 = k1.der;
		k1.der = k2.izq;
		k2.izq = k1;
		k1.altura = max(altura(k1.izq), altura(k1.der)) + 1;
		k2.altura = max(altura(k2.der), k1.altura) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child
	 */
	private NodoABB rotarDobleConHijoIzquierdo(NodoABB k3) {
		k3.izq = rotarConHijoDerecho(k3.izq);
		return rotarConHijoIzquierdo(k3);
	}

	/**
	 * Double rotate binary tree node: first right child with its left child;
	 * then node k1 with new right child
	 */
	private NodoABB rotarDobleConHijoDerecho(NodoABB k1) {
		k1.der = rotarConHijoIzquierdo(k1.der);
		return rotarConHijoDerecho(k1);
	}

	/* Functions to count number of nodes */
	public int contarNodos() {
		return contarNodos(getRaiz());
	}

	private int contarNodos(NodoABB r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += contarNodos(r.izq);
			l += contarNodos(r.der);
			return l;
		}
	}

	/* Functions to search for an element */
	public boolean existeElemento(IKey val) {
		return existeElemento(getRaiz(), val) != null;
	}

	// private boolean existeElemento(NodoSBBST<E> nodoAux, E val) {
	// boolean found = false;
	// while ((nodoAux != null) && !found) {
	// E rval = nodoAux.data;
	// if (val.compareTo(rval) < 0)
	// nodoAux = nodoAux.left;
	// else if (val.compareTo(rval) > 0)
	// nodoAux = nodoAux.right;
	// else {
	// found = true;
	// break;
	// }
	// found = existeElemento(nodoAux, val);
	// }
	// return found;
	// }

	@Override
	public IKey buscar(IKey val) {
		return existeElemento(getRaiz(), val);
	}

	private IKey existeElemento(NodoABB nodoAux, IKey val) {
		while (nodoAux != null) {
			IKey rval = nodoAux.getDato();
			if (val.compareTo(rval) < 0)
				nodoAux = nodoAux.izq;
			else if (val.compareTo(rval) > 0)
				nodoAux = nodoAux.der;
			else {
				return rval;
			}
			rval = existeElemento(nodoAux, val);
		}
		return null;
	}

	private void imprimirInorder(NodoABB r) {
		if (r != null) {
			imprimirInorder(r.izq);
			System.out.println(r.dato);
			imprimirInorder(r.der);
		}
	}

	private void preorder(NodoABB r) {
		if (r != null) {
			System.out.print(r.dato + " ");
			preorder(r.izq);
			preorder(r.der);
		}
	}

	private void postorder(NodoABB r) {
		if (r != null) {
			postorder(r.izq);
			postorder(r.der);
			System.out.print(r.dato + " ");
		}
	}

	// no imprimen nada
	private String inOrder(NodoABB r, String separadorDatos) {
		String retorno = "";
		if (r != null) {
			retorno += inOrder(r.izq, separadorDatos);
			retorno += r.dato != null ? r.dato.toString() + separadorDatos : "";
			retorno += inOrder(r.der, separadorDatos);
		}
		return retorno;
	}

	@Override
	public NodoABB getRaiz() {
		return raiz;
	}

	@Override
	public void imprimirPostOrder() {
		postorder(getRaiz());
	}

	@Override
	public void imprimirInOrder() {
		imprimirInorder(getRaiz());
	}

	public String getInOrder() {
		return inOrder(getRaiz(), "|");
	}

	public String getInOrder(String separadorDatos) {
		return inOrder(getRaiz(), separadorDatos);
	}

	@Override
	public void imprimirPreOrder() {
		preorder(getRaiz());
	}

	@Override
	public void eliminar(IKey o) {
		// TODO Auto-generated method stub

	}
}
