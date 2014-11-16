package estructuras.lista;

public interface ILista {

	public void insertarInicio(Object n);

	public boolean esVacia();

	public void borrarInicio();

	public void vaciarLista();

	public boolean borrarElemento(Object n);

	public Object obtenerElemento(Object n);
}