package estructuras.hash;

import java.math.BigInteger;

import dominio.clases.IKey;
import estructuras.lista.Lista;
import estructuras.lista.Lista.IteradorLista;

public class HashTablaAbierta implements IDiccionario {
	private int TAMANO_T;// tamano de la tabla
	private Lista[] tabla;
	private int cantMaxRegistros;
	private int contadorRegistros;
	private static String separadorCampos = "|";

	public HashTablaAbierta(int cantMaxRegistros) {
		this.cantMaxRegistros = cantMaxRegistros;
		contadorRegistros = 0;
		TAMANO_T = getTamanoTabla(cantMaxRegistros);
		tabla = new Lista[TAMANO_T];
	}

	@Override
	public boolean pertenece(IKey n) {
		int hash = getHash(n);
		if (tabla[hash] != null) {
			return tabla[hash].obtenerElemento(n) != null;
		}
		return false;
	}

	@Override
	public void borrar(IKey n) {
		int hash = getHash(n);
		if (tabla[hash] != null) {
			if (tabla[hash].borrarElemento(n))
				contadorRegistros--;
		}
	}

	@Override
	public void insertar(IKey n) {
		if (!isFull()) {
			int hash = getHash(n);
			if (tabla[hash] == null) {
				tabla[hash] = new Lista();
			}
			tabla[hash].insertarInicio(n);
			contadorRegistros++;
		}
	}

	public boolean isFull() {
		return contadorRegistros == cantMaxRegistros;
	}

	private int getHash(IKey n) {
		String key = n.getKey().toString();
		// System.out.println("IKey key=" + key);
		int keyLength = key.length();
		int hash = 0;

		for (int i = 0; i < keyLength; i++) {
			int c = key.charAt(i);
			hash += c;
		}
		// System.out.println("hash=" + hash);
		// System.out.println("final hash=" + hash % TAMANO_T + " t-size="
		// + TAMANO_T);
		return hash % TAMANO_T;
	}

	private int getTamanoTabla(int tamanoRecibido) {
		while (tamanoRecibido < 3)
			tamanoRecibido++;
		tamanoRecibido = getNextPrime(tamanoRecibido);
		return tamanoRecibido;
	}

	/* devuelve el siguiente numero primo */
	private int getNextPrime(int n) {
		if (esPrimo(n))
			return n;
		BigInteger bN = new BigInteger(n + "");

		while (!esPrimo(bN.intValue())) {
			bN = bN.nextProbablePrime();
		}

		return bN.intValue();
	}

	// chequea que n sea primo
	private boolean esPrimo(int n) {
		// chequea si n es multiplo de 2
		if (n % 2 == 0)
			return false;
		// si no, chequea impares
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String listarElementos(Class<?> claseElemento) {
		int contadorAux = 0, i = 0;
		String retorno = "";
		while (i < TAMANO_T && contadorAux < contadorRegistros) {
			if (tabla[i] != null) {
				IteradorLista iterador = tabla[i].getIteradorLista();
				while (iterador.getNodoActual() != null) {
					IKey dato = (IKey) iterador.getNodoActual().getObj();
					if (claseElemento == null || claseElemento.isAssignableFrom(dato.getClass())) {
						retorno += (dato != null ? dato.toString() + separadorCampos : "");
						contadorAux++;
					}
					iterador.proximoNodo();
				}
			}
			i++;
		}
		return retorno;
	}
}
