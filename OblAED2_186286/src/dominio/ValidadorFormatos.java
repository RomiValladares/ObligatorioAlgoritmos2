package dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorFormatos {
	// patterns
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String CI_PATTERN = "^[0-9]{1}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{1}$";
	private static final String CELULAR_PATTERN = "^09[0-9]{7}$";

	/**
	 * @param email
	 *            string a validar
	 * @return true si es un email valido
	 */
	public static boolean ValidarEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * @param ci
	 *            string a validar
	 * @return true si cumple con el formato N.NNN.NNN-N
	 */
	public static boolean ValidarCI(String ci) {
		Pattern pattern = Pattern.compile(CI_PATTERN);
		Matcher matcher = pattern.matcher(ci);
		return matcher.matches();
		// if (ci.length() != 11)
		// return false;
		// String[] splitByDot = ci.split("\\.");
		// if (splitByDot.length != 3)
		// return false;
		// if (!splitByDot[2].contains("-"))
		// return false;
		// return true;
	}

	/**
	 * @param numeroCelular
	 *            string a validar
	 * @return true si es un numero de celular valido
	 */
	public static boolean ValidarNumeroCelular(String numeroCelular) {
		Pattern pattern = Pattern.compile(CELULAR_PATTERN);
		Matcher matcher = pattern.matcher(numeroCelular);
		return matcher.matches();
//		if (numeroCelular.length() != 9)
//			return false;
//		if (!numeroCelular.matches("[0-9]+"))
//			return false;
//		if (numeroCelular.charAt(0) != '0' || numeroCelular.charAt(1) != '9')
//			return false;
//		return true;
	}
}
