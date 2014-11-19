package test;

import static org.junit.Assert.*;
import dominio.TipoRetorno;
import dominio.TipoRetorno.TipoError;
import dominio.Sistema;

import org.junit.Test;

public class TestObligatorio {

	@Test
	public void testInicializarDestruirSistema() {
		Sistema s = new Sistema();
		assertEquals(TipoError.ERROR_1, s.inicializarSistema(0).retorno);
		s.destruirSistema();
		assertEquals(TipoError.OK, s.inicializarSistema(3).retorno);
		s.destruirSistema();
		assertEquals(TipoError.OK, s.inicializarSistema(2).retorno);
	}

	// Tests Apicultores

	@Test
	public void testRegistrarApicultor() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);
		String ciApicultor = "3.702.515-7";

		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor, "Omar Alcides", "60 metros", "o@gmail.com", "098206900").retorno);
	}

	@Test
	public void testRegistrarApicultorDuplicado() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);
		String ciApicultor = "3.702.515-7";

		TipoRetorno res = s.registrarApicultor(ciApicultor, "Omar Alcides", "60 metros", "o@gmail.com", "098206900");
		assertEquals(TipoError.OK, res.retorno);

		res = s.registrarApicultor(ciApicultor, "Omar Alcides", "60 metros", "o@gmail.com", "098206900");
		assertEquals(TipoError.ERROR_4, res.retorno);
	}

	@Test
	public void testRegistrarApicultorChequeoFormatos() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);

		// Formatos de Cedula incorrectos
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("702.515-7", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("1702515-7", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("1.702.51-70", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("1.702.51--0", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("..702.510-0", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("1.zzz.051-7", "", "", "o@g.com", "098206900").retorno);
		assertEquals(TipoError.ERROR_1, s.registrarApicultor("           ", "", "", "o@g.com", "098206900").retorno);
		// Formatos de mail incorrectos
		assertEquals(TipoError.ERROR_3, s.registrarApicultor("1.702.517-0", "", "", "", "098206900").retorno);
		assertEquals(TipoError.ERROR_3, s.registrarApicultor("1.702.517-0", "", "", "estonoesunmail.com", "098206900").retorno);
		// Formatos de celular incorrectos
		assertEquals(TipoError.ERROR_2, s.registrarApicultor("1.702.517-0", "", "", "a@mail.com", "123206900").retorno);
		assertEquals(TipoError.ERROR_2, s.registrarApicultor("1.702.517-0", "", "", "a@mail.com", "000692000").retorno);
		assertEquals(TipoError.ERROR_2, s.registrarApicultor("1.702.517-0", "", "", "a@mail.com", "099692abc").retorno);
	}

	@Test
	public void testListadoApicultorOK() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);
		String ciApicultor1 = "3.702.515-7";
		String ciApicultor2 = "3.702.515-9";
		String ciApicultor3 = "3.702.515-8";
		String ciApicultor1FormatoNum = "37025157";
		String ciApicultor2FormatoNum = "37025159";
		String ciApicultor3FormatoNum = "37025158";
		String nom1 = "Omar";
		String nom2 = "Annabella";
		String nom3 = "Neca";

		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor1, nom1, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor2, nom2, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor3, nom3, "60 metros", "o@gmail.com", "098206900").retorno);

		TipoRetorno res = s.listadoApicultores();
		assertEquals(TipoError.OK, res.retorno);
		// System.out.println(res.valorString);
		assertTrue(res.valorString.contains(ciApicultor1) | res.valorString.contains(ciApicultor1FormatoNum));
		assertTrue(res.valorString.contains(nom1));
		assertTrue(res.valorString.contains(ciApicultor2) | res.valorString.contains(ciApicultor2FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor3) | res.valorString.contains(ciApicultor3FormatoNum));
	}

	@Test
	public void testListadoApicultoresOrdenados() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);
		String ciApicultor2 = "3.702.515-7";
		String ciApicultor4 = "3.702.515-9";
		String ciApicultor3 = "3.702.515-8";
		String ciApicultor1 = "1.111.111-1";
		String ciApicultor6 = "9.999.999-9";
		String ciApicultor5 = "5.555.555-5";
		String ciApicultor1FormatoNum = "11111111";
		String ciApicultor2FormatoNum = "37025157";
		String ciApicultor3FormatoNum = "37025158";
		String ciApicultor4FormatoNum = "37025159";
		String ciApicultor5FormatoNum = "55555555";
		String ciApicultor6FormatoNum = "99999999";
		String nom1 = "Omar";
		String nom2 = "Annabella";
		String nom3 = "Neca";

		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor4, nom1, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor5, nom2, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor1, nom3, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor3, nom1, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor6, nom1, "60 metros", "o@gmail.com", "098206900").retorno);
		assertEquals(TipoError.OK, s.registrarApicultor(ciApicultor2, nom1, "60 metros", "o@gmail.com", "098206900").retorno);

		TipoRetorno res = s.listadoApicultores();
		// System.out.println(res.valorString);
		assertEquals(TipoError.OK, res.retorno);
		// Valido que sean exactamente 6 resultados
		assertEquals(res.valorString.split("\\|").length, 6);
		// Valido que esten los 6 valores que puse
		assertTrue(res.valorString.contains(ciApicultor1) | res.valorString.contains(ciApicultor1FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor2) | res.valorString.contains(ciApicultor2FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor3) | res.valorString.contains(ciApicultor3FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor4) | res.valorString.contains(ciApicultor4FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor5) | res.valorString.contains(ciApicultor5FormatoNum));
		assertTrue(res.valorString.contains(ciApicultor6) | res.valorString.contains(ciApicultor6FormatoNum));
		// Valido que esten todos en orden
		assertTrue((res.valorString.indexOf(ciApicultor1) < res.valorString.indexOf(ciApicultor2)
				&& res.valorString.indexOf(ciApicultor2) < res.valorString.indexOf(ciApicultor3)
				&& res.valorString.indexOf(ciApicultor3) < res.valorString.indexOf(ciApicultor4)
				&& res.valorString.indexOf(ciApicultor4) < res.valorString.indexOf(ciApicultor5) && res.valorString.indexOf(ciApicultor5) < res.valorString
				.indexOf(ciApicultor6))
				|| (res.valorString.indexOf(ciApicultor1FormatoNum) < res.valorString.indexOf(ciApicultor2FormatoNum)
						&& res.valorString.indexOf(ciApicultor2FormatoNum) < res.valorString.indexOf(ciApicultor3FormatoNum)
						&& res.valorString.indexOf(ciApicultor3FormatoNum) < res.valorString.indexOf(ciApicultor4FormatoNum)
						&& res.valorString.indexOf(ciApicultor4FormatoNum) < res.valorString.indexOf(ciApicultor5FormatoNum) && res.valorString
						.indexOf(ciApicultor5FormatoNum) < res.valorString.indexOf(ciApicultor6FormatoNum)));
	}

	// TEST CIUDADES
	@Test
	public void testRegistrarCiudad() {

		Sistema s = new Sistema();
		s.inicializarSistema(1);
		assertEquals(TipoError.OK, s.registrarCiudad("Paysandu", -32.3105104, -58.0759192).retorno);

	}

	@Test
	public void testRegistrarCiudadDuplicada() {
		Sistema s = new Sistema();
		s.inicializarSistema(2);
		assertEquals(TipoError.OK, s.registrarCiudad("Paysandu", -32.3105104, -58.0759192).retorno);
		assertEquals(TipoError.ERROR_2, s.registrarCiudad("Salto", -32.3105104, -58.0759192).retorno);
	}

	@Test
	public void testRegistrarCiudadErrorGrafoCompleto() {
		Sistema s = new Sistema();
		s.inicializarSistema(1);
		assertEquals(TipoError.OK, s.registrarCiudad("Paysandu", -32.3105104, -58.0759192).retorno);
		assertEquals(TipoError.ERROR_1, s.registrarCiudad("Salto", -32.00, -58.00).retorno);
	}

	@Test
	public void testMapaEstadoSoloCiudades() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		// Cargo el mapa de ejemplo
		cargarMapa(s);
		assertEquals(TipoError.OK, s.mapaEstado().retorno);
		System.out.println(s.mapaEstado().valorString);
		// assertEquals(TipoError.OK, s.mapaEstado().valorString);
	}

	// // TESTS SIN IMPLEMENTAR
	// @Test
	// public void testRegistrarApiario() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testRegistrarCentro() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testRegistrarTramo() {
	// fail("Not yet implemented");
	// }
	//
	//
	// @Test
	// public void testRutaACentroMasCercano() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testListadoDeApiariosEnCiudad() {
	// fail("Not yet implemented");
	// }
	//
	@Test
	public void testListadoDeCentros() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);

		assertEquals(TipoError.OK, s.registrarCentro("C1", -32.3105104, -58.0759192, 1).retorno);
		assertEquals(TipoError.OK, s.registrarCentro("C2", -98.314, -58.0759192, 2).retorno);
		assertEquals(TipoError.OK, s.registrarCentro("C3", -34.3104, -58.0759192, 3).retorno);
		assertEquals(TipoError.OK, s.registrarCentro("C4", -12.3105104, -58.0759192, 4).retorno);
		assertEquals(TipoError.OK, s.registrarCentro("C5", -3.31, -58.0759192, 5).retorno);

		String retorno = s.listadoDeCentros().valorString;
		// String retorno2 = s.listadoDeCentrosIt().valorString;
		// System.out.println(retorno);
		// System.out.println(retorno2);
		// assertEquals(retorno, retorno2);
	}
	
	// Tramo:
	
	@Test
	public void registrarTramoPeso0() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		
		s.registrarCiudad("Paysandu", -32.3105104, -58.0759192);
		s.registrarCiudad("Salto", -31.3689985, -57.9119238);
		
		
		assertEquals(TipoError.ERROR_1, s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, 0).retorno);
	}
	
	@Test
	public void registrarTramoPesoMenorQue0() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		
		s.registrarCiudad("Paysandu", -32.3105104, -58.0759192);
		s.registrarCiudad("Salto", -31.3689985, -57.9119238);
		
		
		assertEquals(TipoError.ERROR_1, s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, -3).retorno);
	}
	
	@Test
	public void registrarTramoPosInicialNoExiste() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		
		s.registrarCiudad("Salto", -31.3689985, -57.9119238);
		
		
		assertEquals(TipoError.ERROR_2, s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, 2).retorno);
	}
	
	@Test
	public void registrarTramoPosFinalNoExiste() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		
		s.registrarCiudad("Paysandu", -32.3105104, -58.0759192);
		
		
		assertEquals(TipoError.ERROR_2, s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, 2).retorno);
	}
	
	@Test
	public void registrarTramoNingunaPosicionExiste() {
		Sistema s = new Sistema();
		s.inicializarSistema(10);
		
		assertEquals(TipoError.ERROR_2, s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, 2).retorno);
	}
	
	// TODO: falta intentar registrar un tramo existente
	
	@Test
	public void registrarTramoOk() {
		Sistema s = new Sistema();
		s.inicializarSistema(2);
		
		s.registrarCiudad("Paysandu", -32.3105104, -58.0759192);
		s.registrarCiudad("Salto", -31.3689985, -57.9119238);
		
		s.registrarTramo(-32.3105104, -58.0759192, -31.3689985, -57.9119238, 5);
		
		assertEquals("noesta", "implementado");
	}
	
	// EO: tramo

	//
	//
	// Helper para cargar el mapa de ejemplo y no duplicar el codigo en cada
	// test
	private void cargarMapa(Sistema s) {
		s.registrarCiudad("Paysandu", -32.3105104, -58.0759192);
		s.registrarCiudad("Salto", -31.3689985, -57.9119238);
		s.registrarCiudad("Young", -32.698367, -57.6356507);
		s.registrarCiudad("Fray Bentos", -33.1291165, -58.2985057);
		s.registrarCiudad("Mercedes", -33.2563781, -58.0360079);
		s.registrarCiudad("Trinidad", -33.5198572, -56.8987083);
		s.registrarCiudad("Durazno", -33.3851666, -56.5568255);
		s.registrarCiudad("Dolores", -33.5351509, -58.2167245);
	}

}