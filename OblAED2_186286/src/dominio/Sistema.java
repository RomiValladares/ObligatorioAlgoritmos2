package dominio;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;

import dominio.TipoRetorno.TipoError;
import dominio.clases.Apiario;
import dominio.clases.Apicultor;
import dominio.clases.Centro;
import dominio.clases.Ciudad;
import dominio.clases.PuntoMapa;
import dominio.clases.Tramo;
import estructuras.arbol.ArbolBinarioBusqueda;
import estructuras.arbol.IABB;
import estructuras.grafo.GrafoMatriz;
import estructuras.grafo.IGrafo;
import estructuras.hash.HashTablaAbierta;
import estructuras.hash.HashTablaAbierta.IteradorHash;
import estructuras.hash.IDiccionario;

public class Sistema {

	public enum TipoPunto {
		CIUDAD, APIARIO, CENTRO_EXTRACCION
	};

	private IABB apicultores;
	private HashTablaAbierta coordenadasMapa;
	private IGrafo puntosMapa;

	public TipoRetorno inicializarSistema(int cantPuntos) {
		/*
		 * Errores posibles:
		 * 1. Si cantPuntos es menor o igual a 0
		 */
		if (cantPuntos <= 0)
			return new TipoRetorno(TipoError.ERROR_1);

		apicultores = new ArbolBinarioBusqueda();
		coordenadasMapa = new HashTablaAbierta(cantPuntos);
		puntosMapa = new GrafoMatriz(cantPuntos);

		return new TipoRetorno(TipoError.OK);
	}

	public TipoRetorno destruirSistema() {
		return new TipoRetorno(TipoError.NO_IMPLEMENTADA);
	}

	public TipoRetorno registrarApicultor(String cedula, String nombre, String dirección, String email, String celular) {
		/*
		 * Errores posibles:
		 * 1. Si la cedula cedula no cumple el formato N.NNN.NNN-N
		 * 2. Si el numero de celular celular no cumple el formato 09NNNNNNN
		 * 3. Si el email email no cumple el formato de direcciones de e-mail
		 * 4. Si el apicultor de cedula cedula ya esta registrado en el sistema.
		 */

		if (!ValidadorFormatos.ValidarCI(cedula))
			return new TipoRetorno(TipoError.ERROR_1);
		if (!ValidadorFormatos.ValidarNumeroCelular(celular))
			return new TipoRetorno(TipoError.ERROR_2);
		if (!ValidadorFormatos.ValidarEmail(email))
			return new TipoRetorno(TipoError.ERROR_3);
		Apicultor nuevoApicultor = new Apicultor(cedula, nombre, dirección, email, celular);
		if (apicultores.existeElemento(nuevoApicultor))
			return new TipoRetorno(TipoError.ERROR_4);
		apicultores.insertar(nuevoApicultor);
		return new TipoRetorno(TipoError.OK);
	}

	public TipoRetorno registrarCiudad(String nombre, Double coordX, Double coordY) {
		/*
		 * Errores posibles:
		 * 1. Si en el sistema ya hay registrados cantPuntos puntos.
		 * 2. Si ya existe un punto en las coordenadas coordX, coordY del
		 * sistema.
		 */
		if (coordenadasMapa.isFull())
			return new TipoRetorno(TipoError.ERROR_1);
		if (coordenadasMapa.pertenece(new PuntoMapa(coordX, coordY)))
			return new TipoRetorno(TipoError.ERROR_2);

		Ciudad nuevaCiudad = new Ciudad(coordX, coordY, nombre);
		coordenadasMapa.insertar(nuevaCiudad);

		Object dato = coordenadasMapa.encontrar(new PuntoMapa(coordX, coordY));
		puntosMapa.agregarVertice(dato);
		
		return new TipoRetorno(TipoError.OK);
	}

	public TipoRetorno registrarApiario(String nombre, Double coordX, Double coordY, String cedula_apicultor, int capacidad) {
		/*
		 * Errores posibles:
		 * 1. Si en el sistema ya hay registrados cantPuntos puntos.
		 * 2. Si capacidad es menor o igual a 0.
		 * 3. Si el punto de coordenadas coordX, coordY ya esta registrado en el
		 * sistema.
		 * 4. Si el apicultor de cedula cedula_apicultor no existe en el
		 * sistema.
		 */

		if (coordenadasMapa.isFull())
			return new TipoRetorno(TipoError.ERROR_1);
		if (capacidad <= 0)
			return new TipoRetorno(TipoError.ERROR_2);
		if (coordenadasMapa.pertenece(new PuntoMapa(coordX, coordY)))
			return new TipoRetorno(TipoError.ERROR_3);
		if (apicultores.existeElemento(new Apicultor(cedula_apicultor)))
			return new TipoRetorno(TipoError.ERROR_4);

		Apiario nuevoApiario = new Apiario(coordX, coordY, nombre, capacidad);
		Apicultor responsableNuevoApiario = (Apicultor) apicultores.buscar(new Apicultor(cedula_apicultor));
		responsableNuevoApiario.setApiario(nuevoApiario);
		coordenadasMapa.insertar(nuevoApiario);

		Object dato = coordenadasMapa.encontrar(new PuntoMapa(coordX, coordY));
		puntosMapa.agregarVertice(dato);
		
		return new TipoRetorno(TipoError.OK);
	}

	public TipoRetorno registrarCentro(String nombre, Double coordX, Double coordY, int capacidad) {
		/*
		 * Errores posibles:
		 * 1. Si en el sistema ya hay registrados cantPuntos puntos.
		 * 2. Si capacidad es menor o igual a 0.
		 * 3. Si el punto de coordenadas coordX, coordY ya está registrado en el
		 * sistema.
		 */
		if (coordenadasMapa.isFull())
			return new TipoRetorno(TipoError.ERROR_1);
		if (capacidad <= 0)
			return new TipoRetorno(TipoError.ERROR_2);
		if (coordenadasMapa.pertenece(new PuntoMapa(coordX, coordY)))
			return new TipoRetorno(TipoError.ERROR_3);

		Centro nuevoCentro = new Centro(coordX, coordY, nombre, capacidad);
		coordenadasMapa.insertar(nuevoCentro);
		
		Object dato = coordenadasMapa.encontrar(new PuntoMapa(coordX, coordY));
		puntosMapa.agregarVertice(dato);
		
		return new TipoRetorno(TipoError.OK);
	}

	/* TODO michael */

	public TipoRetorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
		/*
		 * Errores posibles:
		 * 1. Si peso es menor o igual a 0.
		 * 2. Si no existe coordi o coordf.
		 * 3. Si ya existe un tramo registrado desde coordi a coordf.
		 */
		if (peso <= 0)
			return new TipoRetorno(TipoError.ERROR_1);
		if (!coordenadasMapa.pertenece(new PuntoMapa(coordXi, coordYi)) || !coordenadasMapa.pertenece(new PuntoMapa(coordXf, coordYf)))
			return new TipoRetorno(TipoError.ERROR_2);

		/*
		 * if(existeTramo(coordXi, coordYi, coordXf, coordYf)) // TODO michael
		 * return new TipoRetorno(TipoError.ERROR_3);
		 */
		
		Object datoInicio = coordenadasMapa.encontrar(new PuntoMapa(coordXi, coordYi)); // PUNTERO al inicio
		Object datoFinal = coordenadasMapa.encontrar(new PuntoMapa(coordXf, coordYf)); // PUNTERO al final
		
		puntosMapa.agregarArista(puntosMapa.obtenerPosVertice(datoInicio), puntosMapa.obtenerPosVertice(datoFinal), peso);
		
		Tramo tramo = new Tramo(datoInicio, datoFinal, peso);

		return new TipoRetorno(TipoError.NO_IMPLEMENTADA);
	}

	public TipoRetorno mapaEstado() {
		TipoRetorno retorno = new TipoRetorno(TipoError.OK);
		String urlMaps = getUrlGoogleMaps();
		retorno.valorString = urlMaps;
		openWebpage(urlMaps);
		return retorno;
	}

	public TipoRetorno rutaACentroMasCercano(Double coordX, Double coordY) {
		/*
		 * Errores posibles:
		 * 1. Si el apiario de coordenadas coordX, coordY no existe en el
		 * sistema.
		 * 2. Si no encuentra ningún centro de extracción que pueda satisfacer
		 * la necesidad de extracción de los colmenares.
		 */
		if (coordenadasMapa.pertenece(new PuntoMapa(coordX, coordY)))
			return new TipoRetorno(TipoError.ERROR_1);
		
		// TODO michael error 2
		
		// TODO michael OK:

		return new TipoRetorno(TipoError.NO_IMPLEMENTADA);
	}

	public TipoRetorno listadoDeApiariosEnCiudad(Double coordX, Double coordY) {
		return new TipoRetorno(TipoError.NO_IMPLEMENTADA);
	}

	/* EO michael */

	public TipoRetorno listadoDeCentros() {
		TipoRetorno retorno = new TipoRetorno(TipoError.OK);
		retorno.valorString = coordenadasMapa.listarElementos(null);
		return retorno;
	}

	public TipoRetorno listadoApicultores() {
		TipoRetorno retorno = new TipoRetorno(TipoError.OK);
		retorno.valorString = apicultores.getInOrder();
		return retorno;
	}

	private String getUrlGoogleMaps() {
		return PuntoMapa.getGoogleMapUrl(coordenadasMapa.getHashTablaAbierta());
	}

	private void openWebpage(String uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(new URI(uri));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
