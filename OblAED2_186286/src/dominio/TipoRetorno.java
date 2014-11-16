package dominio;

public class TipoRetorno {

	public enum TipoError {
		OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, ERROR_6, NO_IMPLEMENTADA
	};

	public int valorEntero;
	public String valorString;
	public TipoError retorno;

	public TipoRetorno() {
		this.retorno = TipoError.NO_IMPLEMENTADA;
	}

	public TipoRetorno(TipoError retorno) {
		this.retorno = retorno;
	}

}
