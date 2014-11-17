package dominio.clases;

import dominio.Sistema;

public class PuntoMapa implements IKey {
	private final static String urlGoogleMapsStatic = "https://maps.googleapis.com/maps/api/staticmap?size=1200x600&maptype=roadmap&markers=";// +parametros

	private Double coordX, coordY;
	private Sistema.TipoPunto tipoPunto;
	private static String separadorCampos = ";";
	private ColorPuntoMapa colorPunto;

	public PuntoMapa(Double coordX, Double coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public PuntoMapa(Double coordX, Double coordY, Sistema.TipoPunto tipoPunto) {
		this(coordX, coordY);
		this.tipoPunto = tipoPunto;
		switch (tipoPunto) {
		case APIARIO:
			this.colorPunto = ColorPuntoMapa.AMARILLO;
			break;
		case CIUDAD:
			this.colorPunto = ColorPuntoMapa.ROJO;
			break;
		case CENTRO_EXTRACCION:
			this.colorPunto = ColorPuntoMapa.VERDE;
			break;
		}
	}

	public Double getCoordX() {
		return coordX;
	}

	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}

	public Double getCoordY() {
		return coordY;
	}

	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}

	@Override
	public int compareTo(IKey obj) {
		if (obj.getClass() != PuntoMapa.class) {
			return 0;
		}
		PuntoMapa otroObj = (PuntoMapa) obj;
		int retorno = otroObj.getCoordX().compareTo(getCoordX());
		if (retorno != 0)
			return retorno;
		return otroObj.getCoordY().compareTo(getCoordY());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordX == null) ? 0 : coordX.hashCode());
		result = prime * result + ((coordY == null) ? 0 : coordY.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass() && !(obj instanceof PuntoMapa))
			return false;
		PuntoMapa other = (PuntoMapa) obj;
		if (coordX == null) {
			if (other.coordX != null)
				return false;
		} else if (!coordX.equals(other.coordX))
			return false;
		if (coordY == null) {
			if (other.coordY != null)
				return false;
		} else if (!coordY.equals(other.coordY))
			return false;
		return true;
	}

	@Override
	public Object getKey() {
		return getCoordX() + getCoordY();
	}

	@Override
	public String toString() {
		return coordX + separadorCampos + coordY;
	}

	public static String getSeparadorCampos() {
		return separadorCampos;
	}

	public final String toGoogleMapMarker() {
		return "markers=color:" + colorPunto + "%7C" + coordX + "," + coordY;
	}

	public enum ColorPuntoMapa {
		ROJO("red"), VERDE("green"), AMARILLO("yellow");

		private final String text;

		/**
		 * @param text
		 */
		private ColorPuntoMapa(final String text) {
			this.text = text;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return text;
		}
	}

}
